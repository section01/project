package jp.co.web.application;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkForm implements Serializable {

    private Boolean flag = false;

    private String info = null;

    private String period = null;

    private String id = null;

    private String name = null;

    private List<Detail> details = null;

    @Getter
    @Setter
    public static class Detail implements Serializable {

        private String date = null;

        private String week = null;

        private String type = null;

        private String openTime = null;

        private String closeTime = null;

        private String breakTime = null;

        private String totalTime = null;

        private String remark = null;

    }

}
