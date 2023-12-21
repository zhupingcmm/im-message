package com.ocbc.project.cs.servicebus.router.hangzhou;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.servicebus.router.OutsourcingSystemRouter;
import com.ocbc.project.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.hangzhou.HangzhouTransformer;
import com.ocbc.project.infrastructure.utils.DateUtils;
import com.ocbc.project.infrastructure.vo.Result;
import org.apache.groovy.util.Maps;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

public class HangZhouRouter implements OutsourcingSystemRouter {
    @Override
    public List<CustomerStaff> fetchOutsourcingSystemCustomerStaff(int pageIndex, int pageSize, String endpoint) {

        RestTemplate restTemplate = new RestTemplate();


        try {
            String urlWithParams = endpoint + "?updatedTime=" + URLEncoder.encode(DateUtils.getStrDate(new Date()), "UTF-8")
                    + "?page=" + URLEncoder.encode(String.valueOf(pageIndex),"UTF-8")
                    + "?size=" + URLEncoder.encode(String.valueOf(pageSize),"UTF-8");
            ResponseEntity<Result> result = restTemplate.getForEntity(urlWithParams, Result.class);

            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(result.getBody().getData()));
            String contendStr = jsonObject.getString("content");
            List<HangzhouCustomerStaff> hangzhouCustomerStaffs = JSON.parseArray(contendStr, HangzhouCustomerStaff.class);

            HangzhouTransformer hangzhouTransformer = new HangzhouTransformer();
            return hangzhouTransformer.transformCustomerStaffs(hangzhouCustomerStaffs);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public Integer fetchOutsourcingSystemCustomerStaffCount(String endpoint) {
        String urlWithParams = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            urlWithParams = endpoint + "?updatedTime=" + URLEncoder.encode(DateUtils.getStrDate(new Date()), "UTF-8");
            ResponseEntity<Result> result = restTemplate.getForEntity(urlWithParams, Result.class);
            return (Integer) result.getBody().getData();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }



}
