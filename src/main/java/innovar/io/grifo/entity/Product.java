package innovar.io.grifo.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
@Accessors(chain = true)
public class Product {
    private String _id;
    @TextIndexed
    private String code;
    @TextIndexed
    private String name;
    @TextIndexed
    private String description;
    private UnitCost unitCost;
    private Long stock;
    private String measureUnit;

}

