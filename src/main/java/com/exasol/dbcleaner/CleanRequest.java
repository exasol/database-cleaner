package com.exasol.dbcleaner;

public final class CleanRequest {
    private final boolean cleanObjects;
    private final boolean cleanConnections;
    private final boolean cleanUsers;
    private final boolean cleanRoles;

    CleanRequest(final boolean cleanObjects, final boolean cleanConnections, final boolean cleanUsers, final boolean cleanRoles) {
        this.cleanObjects = cleanObjects;
        this.cleanConnections = cleanConnections;
        this.cleanUsers = cleanUsers;
        this.cleanRoles = cleanRoles;
    }

    public boolean isCleanObjects() {
        return cleanObjects;
    }

    public boolean isCleanConnections() {
        return cleanConnections;
    }

    public boolean isCleanUsers() {
        return cleanUsers;
    }

    public boolean isCleanRoles() {
        return cleanRoles;
    }

    public boolean isCleanAll() {
        return cleanObjects && cleanConnections && cleanUsers && cleanRoles;
    }
}
