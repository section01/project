package jp.co.web.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * 勤怠一覧画面コントローラ
 */
@Controller
@RequestMapping("/list")
public class ListController {

    /**
     * ユーザ情報
     */
    @Autowired
    private UserInformation userInformation;

    /**
     * 勤怠一覧画面サービス
     */
    @Autowired
    private ListService listService;

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
    @PostMapping
    public String init(Model model, @ModelAttribute ListForm listForm) {

        // ユーザが有効化チェック
        if (!listService.findUserInformation(listForm)) {
            return "forward:/login";
        }

        // 一覧クリア
        listForm.setDetails(null);

        // 日付データフォーマットをセット
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        String date = format.format(new Date());

        // 検索条件をセット
        listForm.setId(userInformation.getId());
        listForm.setName(userInformation.getName());
        listForm.setPeriodFrom(date);
        listForm.setPeriodTo(date);

        return "list";
    }

    /**
     * 検索
     */
    @PostMapping(value="event", params="find")
    public String find(Model model, @ModelAttribute ListForm listForm) {

        // バリデーションチェック
        if (listForm.getPeriodFrom() == null || listForm.getPeriodTo() == null) {
            return "list";
        }

        // 相関チェック
        if (!CalendarCheck(listForm)) {
            listForm.setInfo("検索期間ToがFromより前です。");
            return "list";
        }

        // 登録されている勤怠を検索
        listService.findPeriod(listForm);

        return "list";
    }

    /**
     * ログアウト
     */
    @PostMapping(value="event", params="logout")
    public String logout(Model model) {
        return "forward:/login";
    }

    /**
     * 検索期間チェック
     */
    private Boolean CalendarCheck(ListForm listForm) {

        // 検索期間Fromを年月に分解してintに変換
        String from = listForm.getPeriodFrom();
        int fromYear = Integer.parseInt(from.substring(0,4));
        int fromMonth = Integer.parseInt(from.substring(5,7));

        // 検索期間Toを年月に分解してintに変換
        String to = listForm.getPeriodTo();
        int toYear = Integer.parseInt(to.substring(0,4));
        int toMonth = Integer.parseInt(to.substring(5,7));

        // 検索期間Toが検索期間From以下でないかチェック
        if (toYear < fromYear) {
            return false;
        }
        if (toYear <= fromYear && toMonth < fromMonth) {
            return false;
        }

        return true;
    }
}
