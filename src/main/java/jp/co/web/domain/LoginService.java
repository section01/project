package jp.co.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.application.LoginForm;
import jp.co.web.infrastructure.UserMapper;
import jp.co.web.infrastructure.UserModel;
import jp.co.web.session.UserInformation;

/**
 * ログイン画面サービス
 */
@Service
@Transactional
public class LoginService {

    /**
     * エラーメッセージ
     */
    public static final String ACCOUNTERROR = "アカウント情報が存在しません。";
    public static final String ACCOUNTPASSWORDEMPERROR = "アカウント情報とパスワード情報が未入力です";
    public static final String ACCOUNTEMPERROR = "アカウントが未入力です。";
    public static final String PASSWORDEMPERROR = "パスワードが未入力です";
    public static final String ACCOUNTHALFERROR = "アカウント情報は半角英数字で入力してください。";

    /**
     * ユーザ情報
     */
    @Autowired
    private UserInformation userInformation;

    /**
     * ユーザリポジトリ
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * ユーザ有効チェック
     */
    public Boolean findUserInformation(LoginForm loginForm) {

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

        // チェックするユーザを取得
        UserModel usrModel = userMapper.findUserInformationByAuth(
                loginForm.getAccount(),
                loginForm.getPassword());

        // ユーザ有効チェック
        if (usrModel == null) {
            loginForm.setAccounterror(ACCOUNTERROR);
            return false;
        }

        // ユーザ情報をセット
        userInformation.setRoles(usrModel.getRoles());
        userInformation.setId(usrModel.getId());
        userInformation.setName(usrModel.getName());
        userInformation.setSmartflag(loginForm.isSmartflag());

        return true;
    }
}
