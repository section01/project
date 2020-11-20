package jp.co.web.infrastructure;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ユーザリポジトリ
 */
@Mapper
public interface UserMapper {

	/**
	 * 取得
	 */
    public UserModel findUserInformationByAuth(
            @Param("account")  String account,
            @Param("password") String password);

    /**
	 * 取得
	 */
    public UserModel findUserInformationByExist(
            @Param("id")   String id,
            @Param("name") String name);

}
