package services;

public enum StackExchangeEndPoints {

    VERSION("2.2"),
    ANSWERS("answers");

    private String point;

    StackExchangeEndPoints(String point) {
        this.point = point;
    }

    public String getVersion() {
        return "/".concat(point);
    }

    public String getPoint() {
        return "/".concat(point).concat("?");
    }
}
