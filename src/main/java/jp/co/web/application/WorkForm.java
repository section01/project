package jp.co.web.application;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 勤怠詳細画面フォーム
 */
@Getter
@Setter
public class WorkForm implements Serializable {

    private String info = null;

    /**
     * 表示区分フラグ
     */
    private String display = null;

    /**
     * 勤怠期間
     */
    private String period = null;

    /**
     * 社員ID
     */
    private String id = null;

    /**
     * 社員名
     */
    private String name = null;

    /**
     * 勤怠詳細リスト
     */
    private List<Detail> details = null;

    /**
     * 勤怠詳細
     */
    @Getter
    @Setter
    public static class Detail implements Serializable {

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

}
