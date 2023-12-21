package com.ocbc.project.cs.controller.webmvc;

import com.ocbc.project.cs.controller.vo.req.AddCustomerStaffReqVO;
import com.ocbc.project.cs.controller.vo.resp.CustomerStaffRespVO;
import com.ocbc.project.cs.converter.CustomerStaffConverter;
import com.ocbc.project.cs.service.ICustomerStaffService;
import com.ocbc.project.infrastructure.vo.Result;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customerstatff")
public class CustomerStaffController {

    private final ICustomerStaffService customerStaffService;


    @PostMapping()
    public Result<Long> addCustomerStaff(@RequestBody AddCustomerStaffReqVO addCustomerStaffReqVO) {
        val customerStaff = CustomerStaffConverter.INSTANCE.convert2CustomerStaff(addCustomerStaffReqVO);

        customerStaffService.createCustomerStaff(customerStaff);

        return Result.success(customerStaff.getId());
    }


    @GetMapping("/{staffid}")
    public Result<CustomerStaffRespVO> getCustomerStaffById(@PathVariable(value = "staffid") Long staffId) {
        val customerStaff = customerStaffService.findCustomerStaffById(staffId);

        val customerStaffRespVO = CustomerStaffConverter.INSTANCE.convert2CustomerStaffRespVO(customerStaff);

        return Result.success(customerStaffRespVO);
    }

    @GetMapping("/sync/{systemid}")
    public Result<String> syncDataFromOutSystem(@PathVariable(value = "systemid") String systemId){

        customerStaffService.syncOutsourcingCustomerStaffsBySystemId(systemId);
        return Result.error("Sync date from out system");
    }



}
