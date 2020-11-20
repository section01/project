package jp.co.web.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 勤怠期間リポジトリ
 */
@Mapper
public interface PeriodMapper {

	/**
	 * 取得
	 */
    public PeriodModel findPeriod(
            @Param("id")     String id,
            @Param("period") String period);

	/**
	 * 取得
	 */
    public List<PeriodModel> findPeriodList(
            @Param("id")          String id,
            @Param("periodFrom")  String periodFrom,
            @Param("periodTo")    String periodTo);

    /**
     * 削除
     */
    public void deletePeriod(
            @Param("id")     String id,
            @Param("period") String period);

    /**
     * 登録
     */
    public void insertPeriod(
            @Param("id")     String id,
            @Param("period") String period);

}
