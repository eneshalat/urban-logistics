package com.kayseriexpress.model;

import java.util.Objects;

/**
 * Represents a package in the logistics system.
 * Identifiable by a unique package ID.
 */
public class PackageEntity {
    private String packageID;
    private String targetDestination; // e.g. Talas, Belsin

    /**
     * Creates a new PackageEntity.
     * @param packageID Unique ID.
     * @param targetDestination Target area.
     */
    public PackageEntity(String packageID, String targetDestination) {
        this.packageID = packageID;
        this.targetDestination = targetDestination;
    }

    /**
     * Gets the unique package ID.
     * @return The package ID.
     */
    public String getPackageID() {
        return packageID;
    }

    /**
     * Gets the target destination of the package.
     * @return The target destination.
     */
    public String getTargetDestination() {
        return targetDestination;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PackageEntity that = (PackageEntity) obj;
        return Objects.equals(packageID, that.packageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageID);
    }

    @Override
    public String toString() {
        return "Package[" + packageID + " -> " + targetDestination + "]";
    }
}
