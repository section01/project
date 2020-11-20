package jp.co.web.infrastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * 勤怠期間エンティティ
 */
@Getter
@Setter
public class PeriodModel {

    /**
     * 社員ID
     */
    private String id = null;

    /**
     * 社員名
     */
    private String name = null;

    /**
     * 勤怠期間
     */
    private String period = null;

    /**
     * ステータス
     */
    private String status = null;
}
