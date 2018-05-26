/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The Address class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :19/05/2018
 */
package innovar.io.grifo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String condicionDomicilio;
    private String ubigeo;
    private String tipoVia;
    private String nombreVoa;
    private String codigoZona;
    private String tipoZona;
    private String numero;
    private String interior;
    private String lote;
    private String departamento;
    private String manzana;
    private String kilometro;


}
