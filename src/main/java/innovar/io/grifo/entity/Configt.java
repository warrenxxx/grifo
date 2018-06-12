/**
 * The Configt class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :11/06/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Configt {
    @Id
    public Long id = 1l;
    public String type;
}
