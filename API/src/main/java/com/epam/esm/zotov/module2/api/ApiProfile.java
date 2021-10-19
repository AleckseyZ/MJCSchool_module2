package com.epam.esm.zotov.module2.api;

public enum ApiProfile {
    PROD("prod"), DEV("dev");

    private String profileName;

    private ApiProfile(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public String toString() {
        return profileName;
    }
}