package xyz.ivan.vmTranslator;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public enum SegmentType {
    ARGUMENT("ARG"),
    LOCAL("LCL"),
    STATIC,
    CONSTANT,
    THIS("THIS"),
    THAT("THAT"),
    POINTER,
    TEMP;
    public String value;

    SegmentType(String value) {
        this.value = value;
    }
    SegmentType() {
    }
}
