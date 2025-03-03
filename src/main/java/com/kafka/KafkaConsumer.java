package com.kafka;

import com.services.ProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {

    private final ProductService productService;

    public KafkaConsumer(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics="products_topic", groupId = "group_productos")
    public void processMessage(ConsumerRecord<String, String> record) {
        //json received on kafka
        String message = record.value();
        System.out.println("message received: " + message);

        //extract data message
        String name = "ProductX";
        Double price = 100.0;
        String traceId = record.key();//using kafka key as traceId

        //save traceId on MDC
        Map<String,String> contextMap = new HashMap<>();
        contextMap.put("traceId",traceId);
        MDC.setContextMap(contextMap);

        //call service to save product on db

        productService.getAndSaveProductFromKafka(name,price,traceId)
                .subscribe(product -> System.out.println("Product saved: " + product.getName()));

        MDC.clear();


    }

}
