package com.ocbc.project.cs.reuest;

import com.ocbc.project.infrastructure.utils.DateUtils;
import com.ocbc.project.infrastructure.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


public class RequestTest {

    @Test
    public void testRequest() throws UnsupportedEncodingException {

        String endpoint = "http://localhost:10003/customerStaffs/hangzhou/updated/count";
        RestTemplate restTemplate = new RestTemplate();

//        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint).queryParam("updatedTime",  DateUtils.getStrDate(new Date()));
        String urlWithParams = endpoint + "?updatedTime=" + URLEncoder.encode(DateUtils.getStrDate(new Date()), "UTF-8");

        ResponseEntity<Result> result = restTemplate.getForEntity(urlWithParams, Result.class);

        System.out.println(result);

    }
}
