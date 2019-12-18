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
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.web.domain.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String init(Model model, @ModelAttribute LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/auth")
    public String auth(Model model, @ModelAttribute LoginForm loginForm) {

        if (!loginService.findUserInformation(loginForm)) {
            return "login";
        }

        return "forward:/list";
    }

}
