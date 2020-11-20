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

/**
 * 勤怠詳細画面コントローラ
 */
@Controller
@RequestMapping("/work")
public class WorkController {

    /**
     * ユーザ情報
     */
    @Autowired
    private UserInformation userInformation;

    /**
     * 勤怠詳細画面サービス
     */
    @Autowired
    private WorkService workService;

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
    @GetMapping
    public String init(Model model, @ModelAttribute WorkForm workForm,
        @RequestParam("display") String display,
        @RequestParam("period")  String period,
        @RequestParam("id")      String id,
        @RequestParam("name")    String name) {

        // ユーザが有効化チェック
        if (!workService.findUserInformation(workForm)) {
            return "forward:/login";
        }

        // 勤怠詳細取得
        if ("search".equals(display)) {
            if (!workService.findPeriod(workForm)) {
                return "forward:/login";
            }
        }

        // 表示する勤怠情報をセット
        workForm.setDisplay(display);
        workForm.setId(id);
        workForm.setName(name);
        workForm.setPeriod(period);

        return "work";
    }

    /**
     * 作成
     */
    @PostMapping(value="event", params="create")
    public String create(Model model, @ModelAttribute WorkForm workForm) {

        // バリデーションチェック
        if (workForm.getPeriod() == null) {
            workForm.setInfo("期間を入力して下さい。");
            return "work";
        }

        // 勤怠詳細作成
        workService.makePeriod(workForm);

        return "work";
    }

    /**
     * 登録
     */
    @PostMapping(value="event", params="save")
    public String save(Model model, @ModelAttribute WorkForm workForm) {

        // バリデーションチェック
        if (workForm.getPeriod() == null || workForm.getDetails() == null) {
            workForm.setInfo("期間を入力して下さい。");
            return "work";
        }

        if (workForm.getId() == null || workForm.getName() == null) {
            workForm.setId(userInformation.getId());
            workForm.setName(userInformation.getName());
        }

        // 勤怠登録
        if (!workService.savePeriod(workForm)) {
            return "forward:/login";
        }

        return "work";
    }

    /**
     * 削除
     */
    @PostMapping(value="event", params="delete")
    public String delete(Model model, @ModelAttribute WorkForm workForm) {

        // バリデーションチェック
        if (workForm.getPeriod() == null || workForm.getDetails() == null) {
            workForm.setInfo("期間を入力して下さい。");
            return "work";
        }

        if (workForm.getName() == null) {
            return "work";
        }

        // 勤怠詳細削除
        if (!workService.deletePeriod(workForm)) {
            return "forward:/login";
        }

        return "forward:/list";

    }

    /**
     * 戻る
     */
    @PostMapping(value="event", params="back")
    public String back(Model model) {
        return "forward:/list";
    }

    /**
     * ログアウト
     */
    @PostMapping(value="event", params="logout")
    public String logout(Model model) {
        return "forward:/login";
    }

}
