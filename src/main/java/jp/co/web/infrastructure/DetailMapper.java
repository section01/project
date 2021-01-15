package jp.co.web.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.web.application.WorkForm;

/**
 * 勤怠詳細リポジトリ
 */
@Mapper
public interface DetailMapper {

	/**
	 * 取得
	 */
    public List<DetailModel> findDetail(
            @Param("id")     String id,
            @Param("period") String period);

    /**
     * 削除
     */
    public void deleteDetail(
            @Param("id")     String id,
            @Param("period") String period);

    /**
     * 登録
     */
    public void insertDetail(
    		@Param("id")      String id,
            @Param("period")  String period,
            @Param("details") List<WorkForm.Detail> details);

}
