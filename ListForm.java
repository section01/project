package jp.co.web.application;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListForm implements Serializable {

    private String info = null;

    private String id = null;

    private String name = null;

    private String periodFrom = null;

    private String periodTo = null;

    private List<Detail> details = null;

    @Getter
    @Setter
    public static class Detail implements Serializable {

        private String id = null;

        private String name = null;

        private String period = null;

    }

}
