/**
 * The MovementDao class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovementDao extends ReactiveMongoRepository<Movement,String> {
}
