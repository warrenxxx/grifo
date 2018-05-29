package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDao extends ReactiveMongoRepository<Product,String> {
    Flux<Product> findAllByCodeContainingOrDescriptionContaining(String name,String code,String description);
    Mono<Long> removeBy_id(String id);
}
