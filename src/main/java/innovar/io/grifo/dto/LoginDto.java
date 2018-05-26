/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The LoginDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/03/2018
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Document
public class LoginDto {
    private String user;
    private String password;

    public LoginDto setUser(String user) {
        this.user = user;
        return this;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
