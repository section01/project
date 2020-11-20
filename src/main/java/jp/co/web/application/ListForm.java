package jp.co.web.application;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 勤怠一覧画面フォーム
 */
@Getter
@Setter
public class ListForm implements Serializable {

    /**
     *
     */

    /**
     * 通知メッセージ
     */
    private String info = null;

    /**
     * 社員ID
     */
    private String id = null;

    /**
     * 社員名
     */
    private String name = null;

    /**
     * 検索期間From
     */
    private String periodFrom = null;

    /**
     * 検索期間To
     */
    private String periodTo = null;

    /**
     * 勤怠情報一覧
     */
    private List<Detail> details = null;

    /**
     * 勤怠情報
     */
    @Getter
    @Setter
    public static class Detail implements Serializable {

        /**
         * 社員ID
         */
        private String id = null;

        /**
         * 社員名
         */
        private String name = null;

        /**
         * 勤怠年月
         */
        private String period = null;

        /**
         * ステータス
         */
        private String status = null;
    }
}
