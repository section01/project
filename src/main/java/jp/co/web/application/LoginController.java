package jp.co.web.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.web.domain.LoginService;

/**
 * ログイン画面コントローラ
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * ログイン画面サービス
	 */
    @Autowired
    private LoginService loginService;

    /**
     * 初期化バインダ
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * 初期化
     */
    @RequestMapping
    public String init(Model model, @ModelAttribute LoginForm loginForm) {
        return "login";
    }

    /**
     * ログイン
     */
    @PostMapping("/auth")
    public String auth(Model model, @ModelAttribute LoginForm loginForm) {

        // ユーザ有効チェック
        if (!loginService.findUserInformation(loginForm)) {
            return "login";
        }

        return "forward:/list";
    }

}
