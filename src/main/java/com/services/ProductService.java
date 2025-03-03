package com.services;

import com.model.Product;
import com.repository.ProductRepository;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WebClient webClient;

    public ProductService(ProductRepository productRepository, WebClient.Builder webClientBuilder ) {
        this.productRepository = productRepository;
        this.webClient = webClientBuilder.baseUrl("http://apihere.com").build();

    }

    public Mono<Product> getAndSaveProductFromKafka(String name, Double price, String traceId){
      //add traceId to MDC, its a diagnostic context mapped
        MDC.setContextMap((Map<String, String>) Context.of("traceId",traceId).stream());


        return webClient.get()
                .uri("/products/1")
                .retrieve()
                .bodyToMono(Product.class)
                .map(apiProduct -> new Product(name, price))
                .flatMap(productRepository::save)
                .contextWrite(Context.of("traceId",traceId)) //add traceId to reactor context
                .doFinally(signalType -> MDC.clear()); //clear mdc after execution
    }

}
