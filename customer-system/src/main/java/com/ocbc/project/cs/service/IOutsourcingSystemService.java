package com.ocbc.project.cs.service;

import com.ocbc.project.cs.entity.tenant.OutsourcingSystem;

public interface IOutsourcingSystemService {
    OutsourcingSystem findBySystemId(String systemId);
}
