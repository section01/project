package jp.co.web.infrastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * 勤怠詳細エンティティ
 */
@Getter
@Setter
public class DetailModel {

	/**
	 * 日付
	 */
    private String date = null;

    /**
     * 曜日
     */
    private String week = null;

    /**
     * 勤務種別
     */
    private String type = null;

    /**
     * 始業時間
     */
    private String openTime = null;

    /**
     * 終業時間
     */
    private String closeTime = null;

    /**
     * 休憩時間
     */
    private String breakTime = null;

    /**
     * 稼働時間
     */
    private String totalTime = null;

    /**
     * 備考
     */
    private String remark = null;

}
