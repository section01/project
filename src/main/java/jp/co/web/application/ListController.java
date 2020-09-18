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

import jp.co.web.domain.ListService;
import jp.co.web.session.UserInformation;

@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    private UserInformation userInformation;

    @Autowired
    private ListService listService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping
    public String init(Model model, @ModelAttribute ListForm listForm) {

        listForm.setDetails(null);

        if (!listService.findUserInformation(listForm)) {
            return "forward:/login";
        }

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        listForm.setId(userInformation.getId());
        listForm.setName(userInformation.getName());

        return "list";
    }

    @PostMapping(value="event", params="find")
    public String find(Model model, @ModelAttribute ListForm listFrom) {

        listService.findPeriod(listFrom);

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "list";
    }

    @PostMapping(value="event", params="logout")
    public String logout(Model model, @ModelAttribute ListForm listFrom) {
        return "forward:/login";
    }

}
