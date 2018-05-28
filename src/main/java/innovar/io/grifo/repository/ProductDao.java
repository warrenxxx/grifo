package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductDao extends ReactiveMongoRepository<Product,String> {
    Flux<Product> findAllByNameContainingOrCodeContainingOrDescriptionContaining(String name,String code,String description);
}
