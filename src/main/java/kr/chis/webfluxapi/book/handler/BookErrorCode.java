package kr.chis.webfluxapi.book.handler;

/**
 * @author InSeok
 * Date : 2020-10-29
 * Remark :
 */
public enum BookErrorCode {
    B400001("B400001", "요청한 값(포맷)이 잘못 되었습니다.","Bad Request"),
    B404001("B404001", "데이터베이스에 존재하지않습니다.","Not Found"),

    ;

    private String code;
    private String desc;
    private String desc_eng;

    BookErrorCode(String code, String desc, String desc_eng) {
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
