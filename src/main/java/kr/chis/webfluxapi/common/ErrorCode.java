package kr.chis.webfluxapi.common;

/**
 * @author InSeok
 * Date : 2020-10-29
 * Remark :
 */
public enum  ErrorCode {
    S001("S001", "잘못된 요청입니다.","Bad request"),

    ;

    private String code;
    private String desc;
    private String desc_eng;

    ErrorCode(String code, String desc, String desc_eng) {
        this.code = code;
        this.desc = desc;
        this.desc_eng = desc_eng;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc_eng() {
        return desc_eng;
    }

    public void setDesc_eng(String desc_eng) {
        this.desc_eng = desc_eng;
    }
}
