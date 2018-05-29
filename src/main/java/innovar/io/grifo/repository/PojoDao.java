package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Pojo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PojoDao extends ReactiveMongoRepository<Pojo,String> {
}
