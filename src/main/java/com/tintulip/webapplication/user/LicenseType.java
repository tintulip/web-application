package com.tintulip.webapplication.user;

public enum LicenseType {
    ARTISTIC("Artistic"),
    MUSIC("Music"),
    CREATIVE("Creative"),
    OTHER("Other");

    private final String displayLicense;

    LicenseType(String displayLicense) {
        this.displayLicense = displayLicense;
    }

    public String getDisplayLicense() {
        return displayLicense;
    }
}
