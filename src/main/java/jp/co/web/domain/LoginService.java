package jp.co.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.application.LoginForm;
import jp.co.web.infrastructure.UserMapper;
import jp.co.web.infrastructure.UserModel;
import jp.co.web.session.UserInformation;

@Service
@Transactional
public class LoginService {

	public static final String ACCOUNTERROR = "アカウント情報が存在しません。";
	public static final String ACCOUNTPASSWORDEMPERROR = "アカウント情報とパスワード情報が未入力です";
	public static final String ACCOUNTEMPERROR = "アカウントが未入力です。";
	public static final String PASSWORDEMPERROR = "パスワードが未入力です";
	public static final String ACCOUNTHALFERROR = "アカウント情報は半角英数字で入力してください。";


    @Autowired
    private UserInformation userInformation;

    @Autowired
    private UserMapper userMapper;

    public Boolean findUserInformation(LoginForm loginForm) {

        UserModel usrModel = userMapper.findUserInformationByAuth(
                loginForm.getAccount(),
                loginForm.getPassword());

        String account = loginForm.getAccount();
        String password = loginForm.getPassword();

        // 未入力チェック
        if (account == null || password == null) {
        	if (account == null && password == null) {
        		loginForm.setAccounterror(ACCOUNTPASSWORDEMPERROR);
        		return false;
        		}
        	if (account == null) {
        		loginForm.setAccounterror(ACCOUNTEMPERROR);
        		return false;
        		}
        	if (password == null) {
        		loginForm.setAccounterror(PASSWORDEMPERROR);
        		return false;
        		}
        }

        // 英数字チェック
		int len = account.length();
		byte[] bytes = account.getBytes();
		if (len != bytes.length) {
			loginForm.setAccounterror(ACCOUNTHALFERROR);
			return false;
		}

        // アカウント情報チェック
    	if (usrModel == null) {
    		loginForm.setAccounterror(ACCOUNTERROR);
    		return false;
    	}

    	userInformation.setId(usrModel.getId());
        userInformation.setName(usrModel.getName());

        return true;
    }
}
