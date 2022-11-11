package com.exasol.dbcleaner;

public class CleanRequestBuilder {
    private boolean cleanObjects = true;
    private boolean cleanConnections = true;
    private boolean cleanUsers = true;
    private boolean cleanRoles = true;

    public CleanRequestBuilder setCleanObjects(final boolean cleanObjects) {
        this.cleanObjects = cleanObjects;
        return this;
    }

    public CleanRequestBuilder setCleanConnections(final boolean cleanConnections) {
        this.cleanConnections = cleanConnections;
        return this;
    }

    public CleanRequestBuilder setCleanUsers(final boolean cleanUsers) {
        this.cleanUsers = cleanUsers;
        return this;
    }

    public CleanRequestBuilder setCleanRoles(final boolean cleanRoles) {
        this.cleanRoles = cleanRoles;
        return this;
    }

    public CleanRequest createCleanRequest() {
        return new CleanRequest(cleanObjects, cleanConnections, cleanUsers, cleanRoles);
    }
}