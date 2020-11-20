package jp.co.web.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザ情報セッション
 */
@Getter
@Setter
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInformation {

	/**
	 * 権限
	 */
    private String roles = null;

    /**
     * 社員ID
     */
    private String id = null;

    /**
     * 社員名
     */
    private String name = null;

}
