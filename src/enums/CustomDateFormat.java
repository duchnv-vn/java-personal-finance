package enums;

public enum CustomDateFormat {
    YYYYMMDD("{0}-{1}-{2}"),
    YYYYMM("{0}-{1}");

    private String format;

    CustomDateFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }

    public String getFormat() {
        return format;
    }
}
