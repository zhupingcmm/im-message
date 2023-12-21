package com.ocbc.project.cs.servicebus.router.shanghai;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.router.OutsourcingSystemRouter;
import com.ocbc.project.cs.servicebus.router.shanghai.dto.ShanghaiCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.shanghai.ShanghaiTransformer;
import com.ocbc.project.infrastructure.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class ShanghaiRouter implements OutsourcingSystemRouter {

    @Override
    public List<CustomerStaff> fetchOutsourcingSystemCustomerStaff(int pageIndex, int pageSize, String endpoint) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Result> result = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                Result.class
        );
        List<ShanghaiCustomerStaff> shanghaiCustomerStaffs = (List<ShanghaiCustomerStaff>)result.getBody().getData();

        ShanghaiTransformer shanghaiTransformer = new ShanghaiTransformer();
        return shanghaiTransformer.transformCustomerStaffs(shanghaiCustomerStaffs);
    }

    @Override
    public Integer fetchOutsourcingSystemCustomerStaffCount(String endpoint) {
        return null;
    }
}
