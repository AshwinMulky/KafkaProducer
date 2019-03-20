package com.ashwinmulky.kafka.producer.resource;

import com.ashwinmulky.kafka.producer.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/bill")
class BillingProducer {

    private static final String TOPIC = "billingTopic";

    @Autowired
    private KafkaTemplate<String, Bill> kafkaTemplate;

    @GetMapping("/publish")
    public ResponseEntity<String> produceBill(@RequestBody Bill bill) {
        kafkaTemplate.send(TOPIC, bill);
        return new ResponseEntity<>("Added successfully", HttpStatus.OK);
    }
}