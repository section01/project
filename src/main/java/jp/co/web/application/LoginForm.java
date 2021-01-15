package jp.co.web.application;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * ログイン画面フォーム
 */
@Getter
@Setter
public class LoginForm implements Serializable {

	/**
	 * アカウント
	 */
    private String account = null;

    /**
     * パスワード
     */
    private String password = null;

    /**
     * エラーメッセージ
     */
    private String accounterror = null;

    /**
     * スマホフラグ
     */
    private boolean smartflag = false;

}
