package com.ocbc.project.cs.servicebus.endpoint.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;
import com.ocbc.project.cs.mapper.CustomerStaffMapper;
import com.ocbc.project.cs.service.ICustomerStaffService;
import com.ocbc.project.cs.servicebus.endpoint.CustomerStaffEndpoint;
import com.ocbc.project.cs.servicebus.filter.CustomerStaffFilterChain;
import com.ocbc.project.cs.servicebus.router.OutsourcingSystemRouter;
import com.ocbc.project.cs.servicebus.router.RouterContext;
import com.ocbc.project.cs.servicebus.router.RouterFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerStaffEndpointImpl extends ServiceImpl<CustomerStaffMapper, CustomerStaff> implements CustomerStaffEndpoint {

    private final CustomerStaffFilterChain filterChain;

    private static final int pageSize = 10;

    @Override
    @Transactional
    public void fetchCustomerStaffs(OutsourcingSystem outsourcingSystem) {
        OutsourcingSystemRouter router = RouterFactory.getRouter(outsourcingSystem);
        RouterContext routerContext = new RouterContext(router);

        Integer counts = router.fetchOutsourcingSystemCustomerStaffCount(getStaffCountUrl(outsourcingSystem));

        int nums = 0;
        if (counts > pageSize) {
            nums = counts / pageSize;

            int result = counts % pageSize;
            if (result != 0) {
                nums = nums + 1;
            }
        }

        for (int i = 0; i<= nums; i++) {
            List<CustomerStaff> customerStaffs =  routerContext.fetchOutsourcingSystemCustomerStaff(i, pageSize, getStaffUrl(outsourcingSystem));
            List<CustomerStaff> customerStaffList = customerStaffs.stream()
                    .map(filterChain::execute).filter(ObjectUtil::isNotEmpty)
                    .collect(Collectors.toList());
            if (CollectionUtil.isEmpty(customerStaffList)) {
                log.error("No data need to save to DB");
                continue;
            }

            this.saveBatch(customerStaffList);
        }

    }


    private String getStaffCountUrl (OutsourcingSystem outsourcingSystem) {
        return outsourcingSystem.getSystemBaseUrl() + outsourcingSystem.getCountEndpoint();
    }

    private String getStaffUrl (OutsourcingSystem outsourcingSystem) {
        return outsourcingSystem.getSystemBaseUrl() + outsourcingSystem.getSyncEndpoint();
    }
}
