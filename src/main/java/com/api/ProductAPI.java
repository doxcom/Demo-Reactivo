package com.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductAPI {
    /*
    * En programación reactiva, Flux y Mono son tipos de Publisher que se diferencian en la cantidad de elementos que
    * pueden emitir. Flux puede emitir 0 o más elementos, mientras que Mono solo puede emitir 0 o 1
    *por lo tanto el metodo mapping de la lista:  List<Product> cambiaria a Flux<Product>
    * */

    @GetMapping
    public Flux<Product> findAll() {
        List<Product> list = Arrays.asList(new Product(1,"TV"), new Product(2,"PC"),new Product(3,"Laptop"));
      //necesito devolver un flujo de productos
        //crear un flujo de datos apartir de la lista previa, una secuencia finita de datos
        Flux<Product> fxProducts= Flux.fromIterable(list);

        return fxProducts;
    }
}
