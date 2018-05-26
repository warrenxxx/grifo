package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Person;
import innovar.io.grifo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserDao extends ReactiveMongoRepository<User,String> {
    public Mono<User> getUserByPasswordAndUserName(String email,String userName);
}
