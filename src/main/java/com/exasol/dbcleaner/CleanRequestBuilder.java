package com.exasol.dbcleaner;

/**
 * Builder to create instances of {@link CleanRequest}.
 */
public class CleanRequestBuilder {
    private boolean cleanObjects = true;
    private boolean cleanConnections = true;
    private boolean cleanUsers = true;
    private boolean cleanRoles = true;

    CleanRequestBuilder() {
    }

    /**
     * Set whether database objects should be dropped
     * @param cleanObjects true if all database objects should be dropped
     * @return itself to enable chaining
     */
    public CleanRequestBuilder setCleanObjects(final boolean cleanObjects) {
        this.cleanObjects = cleanObjects;
        return this;
    }

    /**
     * Set whether connections should be dropped
     * @param cleanConnections true if all connections should be dropped
     * @return itself to enable chaining
     */
    public CleanRequestBuilder setCleanConnections(final boolean cleanConnections) {
        this.cleanConnections = cleanConnections;
        return this;
    }

    /**
     * Set whether users should be dropped
     * @param cleanUsers true if all users should be dropped
     * @return itself to enable chaining
     */
    public CleanRequestBuilder setCleanUsers(final boolean cleanUsers) {
        this.cleanUsers = cleanUsers;
        return this;
    }

    /**
     * Set whether roles should be dropped
     * @param cleanRoles true if all roles should be dropped
     * @return itself to enable chaining
     */
    public CleanRequestBuilder setCleanRoles(final boolean cleanRoles) {
        this.cleanRoles = cleanRoles;
        return this;
    }

    /**
     * Build the instance of {@link CleanRequest}.
     * @return the instance of {@link CleanRequest}.
     */
    public CleanRequest build() {
        return new CleanRequest(cleanObjects, cleanConnections, cleanUsers, cleanRoles);
    }
}