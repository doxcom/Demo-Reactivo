package com.services;

import com.model.Product;
import com.repository.ProductRepository;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient webClient;

    public ProductService(ProductRepository productRepository, WebClient.Builder webClientBuilder ) {
        this.productRepository = productRepository;
        this.webClient = webClientBuilder.baseUrl("http://apihere.com").build();

    }

    public Mono<Product> getAndSaveProductFromKafka(String name, Double precio, String traceId){
      //add traceId to MDC, its a diagnostic context mapped
        MDC.setContextMap(Context.of("traceId",traceId).stream().toMap());
        return webClient.get()
    }

}
