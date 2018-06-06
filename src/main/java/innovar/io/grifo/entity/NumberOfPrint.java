/**
 * The NumberOfPrint class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :5/06/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfPrint{
    private Long number;
    private Boolean active;
}
