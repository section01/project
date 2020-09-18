package jp.co.web.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.web.domain.WorkService;
import jp.co.web.session.UserInformation;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private UserInformation userInformation;

    @Autowired
    private WorkService workService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String init(Model model, @ModelAttribute WorkForm workForm,
        @RequestParam("period") String period,
        @RequestParam("id")     String id,
        @RequestParam("name")   String name) {

        workForm.setPeriod(period);
        workForm.setId(id);
        workForm.setName(name);

        if (!workService.findUserInformation(workForm)) {
            return "forward:/login";
        }

        if (!workService.findPeriod(workForm)) {
            return "forward:/login";
        }

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "work";
    }

    @GetMapping(value="origin")
    public String origin(Model model, @ModelAttribute WorkForm workForm, @RequestParam("flag") Boolean flag) {

        workForm.setFlag(flag);

        if (!workService.findUserInformation(workForm)) {
            return "forward:/login";
        }

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "work";
    }

    @PostMapping(value="event", params="make")
    public String make(Model model, @ModelAttribute WorkForm workForm) {

        if (workForm.getPeriod() == null) {
            workForm.setFlag(true);
            model.addAttribute("roles", userInformation.getRoles());
            model.addAttribute("id",    userInformation.getId());
            model.addAttribute("name",  userInformation.getName());
            return "work";
        }

        workService.makePeriod(workForm);

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "work";
    }

    @PostMapping(value="event", params="save")
    public String save(Model model, @ModelAttribute WorkForm workForm) {

        if (workForm.getPeriod() == null || workForm.getDetails() == null) {
            workForm.setFlag(true);
            model.addAttribute("roles", userInformation.getRoles());
            model.addAttribute("id",    userInformation.getId());
            model.addAttribute("name",  userInformation.getName());
            return "work";
        }

        if (!workService.savePeriod(workForm)) {
            return "forward:/login";
        }


        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "work";
    }

    @PostMapping(value="event", params="delete")
    public String delete(Model model, @ModelAttribute WorkForm workForm) {

        if (workForm.getPeriod() == null || workForm.getDetails() == null) {
            workForm.setFlag(true);
            model.addAttribute("roles", userInformation.getRoles());
            model.addAttribute("id",    userInformation.getId());
            model.addAttribute("name",  userInformation.getName());
            return "work";
        }

        if (!workService.deletePeriod(workForm)) {
            return "forward:/login";
        }

        model.addAttribute("roles", userInformation.getRoles());
        model.addAttribute("id",    userInformation.getId());
        model.addAttribute("name",  userInformation.getName());

        return "forward:/list";

    }

    @PostMapping(value="event", params="back")
    public String back(Model model, @ModelAttribute WorkForm workForm) {
        return "forward:/list";
    }

    @PostMapping(value="event", params="logout")
    public String logout(Model model, @ModelAttribute WorkForm workForm) {
        return "forward:/login";
    }

}
