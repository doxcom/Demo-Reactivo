package com.services;

import com.model.Product;
import com.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    //private final WebClient.Builder webClientBuilder = null;
    private final ProductRepository productRepository;
    private final WebClient webClient;

    public ProductService(WebClient webClient, ProductRepository productRepository, WebClient webClient1) {
        this.productRepository = productRepository;
        this.webClient = webClient1;
        this.webClient = webClientBuilder.baseUrl("http://apihere.com").build();

    }

    public Mono<Product> getAndSaveProduct(Long id){
        return webClient.get()
    }

}
