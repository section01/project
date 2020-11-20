package jp.co.web.domain;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.application.ListForm;
import jp.co.web.infrastructure.PeriodMapper;
import jp.co.web.infrastructure.PeriodModel;
import jp.co.web.infrastructure.UserMapper;
import jp.co.web.infrastructure.UserModel;
import jp.co.web.session.UserInformation;

/**
 * 勤怠一覧画面サービス
 */
@Service
@Transactional
public class ListService {

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
     * モデルマッパー
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * ユーザ有効チェック
     */
    public Boolean findUserInformation(ListForm listForm) {

        UserModel userModel = userMapper.findUserInformationByExist(userInformation.getId(), userInformation.getName());

        if (userModel == null) {
            return false;
        }

        return true;
    }

    /**
     * 検索
     */
    public void findPeriod(ListForm listForm) {

        // 勤怠一覧取得
        List<PeriodModel> periodModel = periodMapper.findPeriodList(listForm.getId(), listForm.getPeriodFrom(), listForm.getPeriodTo());

        // 取得結果無し
        if (periodModel.size() <= 0) {
            listForm.setInfo("検索結果がありません。");
        }

        // 勤怠一覧セット
        List<ListForm.Detail> details = new ArrayList<ListForm.Detail>();
        for (PeriodModel each : periodModel) {
            ListForm.Detail detail = modelMapper.map(each, ListForm.Detail.class);
            details.add(detail);
        }
        listForm.setDetails(details);
    }
}
