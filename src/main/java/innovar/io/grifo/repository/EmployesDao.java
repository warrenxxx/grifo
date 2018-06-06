/**
 * The EmplyesDao class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Employe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface EmployesDao extends ReactiveMongoRepository<Employe,String> {
    public Mono<Employe> getUserByPasswordAndUserName(String password, String userName);
    public Mono<Long> countByPasswordAndUserName(String password,String userName);
}
