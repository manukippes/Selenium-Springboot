package com.kimatesting.qa.selenium_springboot.enums;

public enum MenuSections {
    Blog("/blog"),
    Cursos("/training"),
    Descargas("/downloads"),
    Contacto("/contact"),
    Init("/");

    private final String href;

    MenuSections(String pValue) {
        this.href = pValue;
    }

    public String getHref() {
        return href;
    }
}
