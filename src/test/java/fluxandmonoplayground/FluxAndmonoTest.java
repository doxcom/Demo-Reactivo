package fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndmonoTest {

    @Test
    public void fluxTest(){
      //flux pass this elements to the subscribe
      Flux<String> stringFlux =  Flux.just("Spring","Spring Boot", "Reactive Spring");
      //read all the values from the flux and print
     stringFlux.subscribe(System.out::println);

    }
}
