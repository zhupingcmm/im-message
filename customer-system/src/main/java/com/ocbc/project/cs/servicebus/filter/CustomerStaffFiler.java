package com.ocbc.project.cs.servicebus.filter;

import cn.hutool.core.util.ObjectUtil;
import com.ocbc.project.cs.entity.CustomerStaff;

public interface CustomerStaffFiler {

     CustomerStaffFiler getNext();

    void addFilter(CustomerStaffFiler customerFiler);

    CustomerStaff execute(CustomerStaff customerStaff);


}
