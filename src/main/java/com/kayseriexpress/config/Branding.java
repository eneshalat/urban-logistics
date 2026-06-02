package com.kayseriexpress.config;

public class Branding {
    public static final String COMPANY_NAME = "Kayseri Express Logistics";
    public static final String HUB_NAME = "Meydan";
    public static final String MISSION_STATEMENT = "Connecting all of Kayseri together.";

    public static void printBanner() {
        System.out.println("=====================================================");
        System.out.println("        " + COMPANY_NAME + " - " + HUB_NAME + " Hub");
        System.out.println("=====================================================");
        System.out.println(" Mission: " + MISSION_STATEMENT);
        System.out.println("=====================================================\n");
    }
}
