package com.kayseriexpress.model;

public class PackageEntity {
    private String packageID;
    private String targetDestination; // e.g. Talas, Belsin

    public PackageEntity(String packageID, String targetDestination) {
        this.packageID = packageID;
        this.targetDestination = targetDestination;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getTargetDestination() {
        return targetDestination;
    }

    @Override
    public String toString() {
        return "Package[" + packageID + " -> " + targetDestination + "]";
    }
}
