package jp.co.web.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.web.application.WorkForm;

@Mapper
public interface DetailMapper {

    public List<DetailModel> findDetail(
            @Param("id")     String id,
            @Param("period") String period);

    public void deleteDetail(
            @Param("id")     String id,
            @Param("period") String period);

    public void insertDetail(
    		@Param("id")      String id,
            @Param("period")  String period,
            @Param("details") List<WorkForm.Detail> details);

}
