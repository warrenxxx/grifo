package innovar.io.grifo.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String description;

    private String measureUnit;
    private Double unitaryValue;

    private Boolean isActive;
        private Long stock;
}

