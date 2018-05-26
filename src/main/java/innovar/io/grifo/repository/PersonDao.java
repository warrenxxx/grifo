package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonDao extends ReactiveMongoRepository<Person,String> {
}
