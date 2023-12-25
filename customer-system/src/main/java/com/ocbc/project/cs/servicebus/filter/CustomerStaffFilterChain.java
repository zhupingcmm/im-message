package com.ocbc.project.cs.servicebus.filter;

import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.filter.impl.DummyFilter;
import com.ocbc.project.cs.servicebus.filter.impl.PhoneFilter;
import com.ocbc.project.cs.servicebus.filter.impl.StaffNameFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffFilterChain {

    private final CustomerStaffFiler chain;

    public CustomerStaffFilterChain () {
        this.chain = addFilter(new PhoneFilter(), new StaffNameFilter());
    }

    private CustomerStaffFiler addFilter(CustomerStaffFiler ...customerStaffFilers) {

        CustomerStaffFiler dummyFilter = new DummyFilter();
        CustomerStaffFiler next = dummyFilter;

        for (CustomerStaffFiler customerStaffFiler : customerStaffFilers) {
            next = next.addFilter(customerStaffFiler);
        }

        return dummyFilter.getNext();
    }

    public CustomerStaff execute(CustomerStaff customerStaff) {
        if (chain == null) return null;
        return chain.execute(customerStaff);
    }

}
