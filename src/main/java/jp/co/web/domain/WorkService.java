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

@Service
@Transactional
public class WorkService {

    @Autowired
    private UserInformation userInformation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PeriodMapper periodMapper;

    @Autowired
    private DetailMapper detailMapper;

    @Autowired
    private ModelMapper modelMapper;

    public Boolean findUserInformation(WorkForm workForm) {

        UserModel userModel = userMapper.findUserInformationByExist(userInformation.getId(), userInformation.getName());

        if (userModel == null) {
            return false;
        }

        workForm.setId(userModel.getId());
        workForm.setName(userModel.getName());

        return true;
    }

    public Boolean findPeriod(WorkForm workForm) {

    	List<DetailModel> detailModel = detailMapper.findDetail(workForm.getId(), workForm.getPeriod());

        if (detailModel.size() <= 0) {
            return false;
        }

        List<WorkForm.Detail> details = new ArrayList<WorkForm.Detail>();
        for (DetailModel each : detailModel) {
            WorkForm.Detail detail = modelMapper.map(each, WorkForm.Detail.class);
            details.add(detail);
        }

        workForm.setDetails(details);

        return true;
    }

    public Boolean makePeriod(WorkForm workForm) {

        if (workForm.getId() == null || workForm.getPeriod() == null) {
            return false;
        }

        PeriodModel periodModel = periodMapper.findPeriod(workForm.getId(), workForm.getPeriod());

        if (periodModel != null) {
            workForm.setInfo("既に作成のデータが存在します。");
        } else {
            workForm.setInfo("新規データを作成しました。");
        }

        String[] period = workForm.getPeriod().split("-", 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,  Integer.parseInt(period[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(period[1]));

        Integer lastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        List<WorkForm.Detail> details = new ArrayList<WorkForm.Detail>();
        for (Integer i = 1; i <= lastDate; i++) {
            WorkForm.Detail detail = new WorkForm.Detail();
            detail.setDate(i.toString());
            detail.setWeek(getWeek(calendar, i));
            details.add(detail);
        }

        workForm.setDetails(details);

        return true;
    }

    public Boolean savePeriod(WorkForm workForm) {

        if (workForm.getId() == null || workForm.getName() == null) {
            return false;
        }

        periodMapper.deletePeriod(workForm.getId(), workForm.getPeriod());
        periodMapper.insertPeriod(workForm.getId(), workForm.getPeriod());

        detailMapper.deleteDetail(workForm.getId(), workForm.getPeriod());
        detailMapper.insertDetail(workForm.getId(), workForm.getPeriod(), workForm.getDetails());

        workForm.setInfo("データを登録しました。");

        return true;
    }

    public Boolean deletePeriod(WorkForm workForm) {

        if (workForm.getId() == null || workForm.getName() == null) {
            return false;
        }

        periodMapper.deletePeriod(workForm.getId(), workForm.getPeriod());
        detailMapper.deleteDetail(workForm.getId(), workForm.getPeriod());

        workForm.setInfo("データを削除しました。");

        return true;
    }

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
