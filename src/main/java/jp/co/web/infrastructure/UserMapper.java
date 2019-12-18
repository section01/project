package jp.co.web.infrastructure;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public UserModel findUserInformationByAuth(
            @Param("account")  String account,
            @Param("password") String password);

    public UserModel findUserInformationByExist(
            @Param("id")   String id,
            @Param("name") String name);

}
