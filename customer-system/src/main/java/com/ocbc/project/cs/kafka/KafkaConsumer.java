package com.ocbc.project.cs.kafka;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.ocbc.project.cs.entity.CustomerStaff;
import com.ocbc.project.cs.service.ICustomerStaffService;
import com.ocbc.project.cs.servicebus.filter.CustomerStaffFilterChain;
import com.ocbc.project.cs.servicebus.router.hangzhou.dto.HangzhouCustomerStaff;
import com.ocbc.project.cs.servicebus.transformer.CustomerStaffTransformer;
import com.ocbc.project.cs.servicebus.transformer.hangzhou.HangzhouTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final CustomerStaffFilterChain filterChain;

    private final ICustomerStaffService customerStaffService;

    @KafkaListener(topics = "hangzhou", groupId = "1")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {

        try {
            String message = record.value();
            log.info("Received Message from Topic: {}, Partition: {}, Offset: {}", message, record.partition(), record.offset());
            HangzhouCustomerStaff  hangzhouCustomerStaff = JSON.parseObject(message, HangzhouCustomerStaff.class);


            CustomerStaffTransformer<HangzhouCustomerStaff> customerStaffTransformer = new HangzhouTransformer();
            List<CustomerStaff> customerStaffList = customerStaffTransformer.transformCustomerStaffs(Collections.singletonList(hangzhouCustomerStaff));

            List<CustomerStaff> customerStaffs = customerStaffList.stream()
                    .map(filterChain::execute).filter(ObjectUtil::isNotEmpty)
                    .collect(Collectors.toList());

            boolean result = customerStaffService.createCustomerStaff(customerStaffs);
            if (result) acknowledgment.acknowledge();

        } catch (Exception e) {
            log.error("Error process data from kafka, {}", e.getLocalizedMessage());
        }






    }





}
