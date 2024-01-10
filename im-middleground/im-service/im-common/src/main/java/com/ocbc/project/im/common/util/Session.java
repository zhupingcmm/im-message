package com.ocbc.project.im.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;
//    private String password;
}
