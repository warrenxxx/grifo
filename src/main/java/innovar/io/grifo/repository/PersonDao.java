package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonDao extends ReactiveMongoRepository<Person,String> {
    Mono<Person> getPersonByDni(String dni);
    Mono<Person> getPersonByRucUser(String rucUser);
}
