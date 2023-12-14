package com.ocbc.project.cs.converter;

import com.ocbc.project.cs.controller.vo.req.AddCustomerStaffReqVO;
import com.ocbc.project.cs.controller.vo.resp.CustomerStaffRespVO;
import com.ocbc.project.cs.entity.CustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerStaffConverter {
    CustomerStaffConverter INSTANCE = Mappers.getMapper(CustomerStaffConverter.class);

    CustomerStaff convert2CustomerStaff(AddCustomerStaffReqVO addCustomerStaffReqVO);

    CustomerStaffRespVO convert2CustomerStaffRespVO(CustomerStaff customerStaff);

}
