package enums;

public enum CustomDateFormat {
    YYYYMMDD("{0}-{1}-{2}"),
    YYYYMM("{0}-{1}");

    private String format;

    CustomDateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
