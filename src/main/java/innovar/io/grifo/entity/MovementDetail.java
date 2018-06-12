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
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
public class MovementDetail {
    private Object _idProduct;
    private Long quantityGal;
    private Long quantitySol;
    private Double discount;
    private Double Price;

    public MovementDetail setIdToString(){
        this._idProduct=this._idProduct.toString();
        return this;
    }
}
