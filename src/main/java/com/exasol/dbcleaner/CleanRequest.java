package com.exasol.dbcleaner;

/**
 * A request enabling the user to enable or disable the drop of certain database objects
 * while using {@link ExasolDatabaseCleaner}.
 */
public final class CleanRequest {
    private final boolean cleanObjects;
    private final boolean cleanConnections;
    private final boolean cleanUsers;
    private final boolean cleanRoles;

    /**
     * Create an instance of {@link CleanRequestBuilder}
     * @return instance of {@link CleanRequestBuilder}
     */
    public static CleanRequestBuilder builder() {
        return new CleanRequestBuilder();
    }

    CleanRequest(final boolean cleanObjects, final boolean cleanConnections, final boolean cleanUsers, final boolean cleanRoles) {
        this.cleanObjects = cleanObjects;
        this.cleanConnections = cleanConnections;
        this.cleanUsers = cleanUsers;
        this.cleanRoles = cleanRoles;
    }

    /**
     * Should all database objects be dropped?
     * @return true if all objects should be dropped
     */
    public boolean isCleanObjects() {
        return cleanObjects;
    }

    /**
     * Should all connections be dropped?
     * @return true if all connections should be dropped
     */
    public boolean isCleanConnections() {
        return cleanConnections;
    }

    /**
     * Should all user be dropped?
     * @return true if users should be dropped
     */
    public boolean isCleanUsers() {
        return cleanUsers;
    }

    /**
     * Should all roles be dropped?
     * @return true if all roles should be dropped
     */
    public boolean isCleanRoles() {
        return cleanRoles;
    }

    /**
     * Should everything be dropped?
     * @return true if everything should be dropped
     */
    public boolean isCleanAll() {
        return cleanObjects && cleanConnections && cleanUsers && cleanRoles;
    }
}
