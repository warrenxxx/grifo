package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PersonDao extends ReactiveMongoRepository<Person,String> {
    Mono<Person> getPersonByDni(String dni);
    Mono<Person> getPersonByRucUser(String rucUser);
    Mono<Long> countByRucUser(String rucUser);
    Mono<Long> countByDni(String dni);
    Flux<Person> findPeopleByRucUserContainingOrDniContainingOrAllNameContaining(String rucUser,String dni,String allName);
    Flux<Person> findPeopleByAllNameContainingOrDniContaining(String allName,String dni);
    Flux<Person> findPeopleByAllNameContaining(String allName);
    Flux<Person> findPeopleByRucUserContaining(String rucUser);
    Flux<Person> findPeopleByDniContaining(String dni);

    Flux<Person> findAllBy(TextCriteria criteria);
    Flux<Person> findAllByAllNameContaining(String citeria);
    Flux<Person> findAllByAllNameContaining(String allName, Pageable pageable);


    Flux<Person> findAllByAllNameContainingOrRucUserContaining(String allName,String rucUser,Pageable pageable);
}
