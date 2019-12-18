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

@Service
@Transactional
public class ListService {

    @Autowired
    private UserInformation userInformation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PeriodMapper periodMapper;

    @Autowired
    private ModelMapper modelMapper;

    public Boolean findUserInformation(ListForm listForm) {

        UserModel userModel = userMapper.findUserInformationByExist(userInformation.getId(), userInformation.getName());

        if (userModel == null) {
            return false;
        }

        listForm.setId(userModel.getId());
        listForm.setName(userModel.getName());

        return true;
    }

    public void findPeriod(ListForm listForm) {

    	List<PeriodModel> periodModel = periodMapper.findPeriodList(listForm.getId(), listForm.getPeriodFrom(), listForm.getPeriodTo());

        if (periodModel.size() <= 0) {
        	listForm.setInfo("検索結果がありません。");
        }

        List<ListForm.Detail> details = new ArrayList<ListForm.Detail>();
        for (PeriodModel each : periodModel) {
            ListForm.Detail detail = modelMapper.map(each, ListForm.Detail.class);
            details.add(detail);
        }

        listForm.setDetails(details);
    }

}
