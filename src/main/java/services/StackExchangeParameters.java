package services;

public enum StackExchangeParameters {

    SITE("site="),
    PAGE("page="),
    PAGE_SIZE("pagesize="),
    ORDER("order="),
    SORT("sort="),
    FILTER("filter=");

    private String parameter;

    StackExchangeParameters(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter(String value) {
        return parameter.concat(value).concat("&");
    }

    public String getFilter(String value) {
        return parameter.concat(value);
    }
}
