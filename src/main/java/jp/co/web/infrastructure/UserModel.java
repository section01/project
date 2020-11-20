package jp.co.web.infrastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザエンティティ
 */
@Getter
@Setter
public class UserModel {

	/**
	 * 権限
	 */
    private String roles = null;

    /**
     * 社員番号
     */
    private String id = null;

    /**
     * 社員名
     */
    private String name = null;

}
