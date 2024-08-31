package com.kimatesting.qa.selenium_springboot.enums;

public enum Browser {
    CHROME("chrome"),
    SAFARI("safari"),
    FIREFOX("firefox");

    private final String value;

    Browser(String pValue) {
        this.value = pValue;
    }

    public String get() {
        return value;
    }
}
