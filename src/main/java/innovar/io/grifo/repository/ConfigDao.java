/**
 * The ConfigDao class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :11/06/2018
 */
package innovar.io.grifo.repository;

import innovar.io.grifo.entity.Config;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConfigDao extends ReactiveMongoRepository<Config,String> {
}
