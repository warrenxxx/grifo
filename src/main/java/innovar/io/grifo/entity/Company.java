/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The Company class is implemment to
 *
 * @version :1.0
 * @Author :warre
 * @since :28/04/2018
 */
package innovar.io.grifo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

import java.lang.reflect.Field;

/*

///aumetna en el db common


 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Company {

    private String id;
    @Indexed
    private String name;
    private String addres;
    private String phone[];
    private String contact;
    @Indexed(unique = true)
    private String ruc;
    private String url;
    @Indexed
    private String email;
    private String logo;
    private String messagePublisher;
    private String description;
    private String businessTurn;
    private Boolean isAgentPerception;
    @Indexed
    private String comercialName;
    private String ubigeo;
    @Indexed
    private ObjectId legalRepresentationId;
    private String cuentaCorrienteDetraccion;

}
