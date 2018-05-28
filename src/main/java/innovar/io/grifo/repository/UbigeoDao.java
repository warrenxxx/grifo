package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Ubigeo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UbigeoDao extends ReactiveMongoRepository<Ubigeo,String> {


}
