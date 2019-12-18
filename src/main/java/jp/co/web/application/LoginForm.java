package jp.co.web.application;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm implements Serializable {

    private String account = null;

    private String password = null;

    private String accounterror = null;

}
