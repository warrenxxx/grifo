/**
 * The BodyReceipt class is implemment to
 *
 * @version :1.0
 * @Author :warre
 * @since :16/04/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDetail {
    private ObjectId _idProduct;
    private Long quantity;
    private Double discount;
    private Double Price;
}
