package services;

public enum TestDataVariables {

    STACK_EXCHANGE_URI("stackExchangeUri");

    private String variable;

    TestDataVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
