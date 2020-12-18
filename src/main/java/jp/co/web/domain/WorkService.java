package jp.co.web.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.application.WorkForm;
import jp.co.web.infrastructure.DetailMapper;
import jp.co.web.infrastructure.DetailModel;
import jp.co.web.infrastructure.PeriodMapper;
import jp.co.web.infrastructure.PeriodModel;
import jp.co.web.infrastructure.UserMapper;
import jp.co.web.infrastructure.UserModel;
import jp.co.web.session.UserInformation;

/**
 * 勤怠詳細画面サービス
 */
@Service
@Transactional
public class WorkService {

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
     * 勤怠期間リポジトリ
     */
    @Autowired
    private PeriodMapper periodMapper;

    /**
     * 勤怠詳細リポジトリ
     */
    @Autowired
    private DetailMapper detailMapper;

    /**
     * モデルマッパー
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * ユーザ有効チェック
     */
    public Boolean findUserInformation(WorkForm workForm) {

        UserModel userModel = userMapper.findUserInformationByExist(userInformation.getId(), userInformation.getName());

        if (userModel == null) {
            return false;
        }

        return true;
    }

    /**
     * 勤怠詳細取得
     */
    public Boolean findPeriod(WorkForm workForm) {

        // 勤怠詳細取得
        List<DetailModel> detailModel = detailMapper.findDetail(workForm.getId(), workForm.getPeriod());

        // 勤怠詳細取得チェック
        if (detailModel == null || detailModel.size() <= 0) {
            return false;
        }

        // 勤怠詳細セット
        List<WorkForm.Detail> details = new ArrayList<WorkForm.Detail>();
        for (DetailModel each : detailModel) {
            WorkForm.Detail detail = modelMapper.map(each, WorkForm.Detail.class);
            details.add(detail);
        }
        workForm.setDetails(details);

        return true;
    }

    /**
     * 勤怠詳細作成
     */
    public Boolean makePeriod(WorkForm workForm) {

        // 勤怠一覧取得
        PeriodModel periodModel = periodMapper.findPeriod(workForm.getId(), workForm.getPeriod());

        // 作成済みの場合、勤怠詳細取得
        if (periodModel != null) {
            workForm.setInfo("既に作成のデータが存在します。");
            findPeriod(workForm);

        // 未作成の場合、新規作成
        } else {
            // 作成する月から最終日を取得
            String[] period = workForm.getPeriod().split("-", 0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,  Integer.parseInt(period[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(period[1]));
            Integer lastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            // 勤怠詳細フォーマット作成
            List<WorkForm.Detail> details = new ArrayList<WorkForm.Detail>();
            for (Integer i = 1; i <= lastDate; i++) {
                WorkForm.Detail detail = new WorkForm.Detail();
                detail.setDate(i.toString());
                detail.setWeek(getWeek(calendar, i));
                details.add(detail);
            }
            workForm.setDetails(details);
            workForm.setInfo("新規データを作成しました。");
        }

        return true;
    }

    /**
     * 勤怠詳細登録
     */
    public Boolean savePeriod(WorkForm workForm) {

        // 勤怠期間登録
        periodMapper.deletePeriod(workForm.getId(), workForm.getPeriod());
        periodMapper.insertPeriod(workForm.getId(), workForm.getPeriod(), workForm.getStatus());

        // 勤怠詳細登録
        detailMapper.deleteDetail(workForm.getId(), workForm.getPeriod());
        detailMapper.insertDetail(workForm.getId(), workForm.getPeriod(), workForm.getDetails());

        workForm.setInfo("データを登録しました。");

        return true;
    }

    /**
     * 勤怠詳細提出
     */
    public Boolean submit(WorkForm workForm) {

        periodMapper.updateStatus(workForm.getId(), workForm.getPeriod(), "1");

        return true;
    }

    /**
     * 勤怠詳細提出取消
     */
    public Boolean cancel(WorkForm workForm) {

        periodMapper.updateStatus(workForm.getId(), workForm.getPeriod(), "0");

        return true;
    }

    /**
     * 勤怠詳細削除
     */
    public Boolean deletePeriod(WorkForm workForm) {

        // 勤怠期間、勤怠詳細削除
        periodMapper.deletePeriod(workForm.getId(), workForm.getPeriod());
        detailMapper.deleteDetail(workForm.getId(), workForm.getPeriod());

        workForm.setInfo("データを削除しました。");

        return true;
    }

    /**
     * 曜日取得
     */
    private String getWeek(Calendar calendar, Integer date) {

        calendar.set(Calendar.DATE, date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                return "日";
            case Calendar.MONDAY:
                return "月";
            case Calendar.TUESDAY:
                return "火";
            case Calendar.WEDNESDAY:
                return "水";
            case Calendar.THURSDAY:
                return "木";
            case Calendar.FRIDAY:
                return "金";
            case Calendar.SATURDAY:
                return "土";
        }
        return null;
    }

}
