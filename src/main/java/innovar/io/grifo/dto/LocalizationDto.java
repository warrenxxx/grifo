/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The LocalizationDto class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :9/05/2018
 */
package innovar.io.grifo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class LocalizationDto {
    private String _id;
    private String ubigeo;

    public LocalizationDto set_id(String _id) {
        this._id = _id;
        return this;
    }

    public LocalizationDto setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
        return this;
    }
}
