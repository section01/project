package jp.co.web.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PeriodMapper {

    public PeriodModel findPeriod(
            @Param("id")     String id,
            @Param("period") String period);

    public List<PeriodModel> findPeriodList(
            @Param("id")          String id,
            @Param("periodFrom")  String periodFrom,
            @Param("periodTo")    String periodTo);

    public void deletePeriod(
            @Param("id")     String id,
            @Param("period") String period);

    public void insertPeriod(
            @Param("id")     String id,
            @Param("period") String period);

}
