package com.exasol.dbcleaner;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class purges all objects from an Exasol database.
 */
public class ExasolDatabaseCleaner {
    private static final Logger LOGGER = Logger.getLogger(ExasolDatabaseCleaner.class.getName());
    private final Statement statement;

    /**
     * Create a new instance of {@link ExasolDatabaseCleaner}.
     * 
     * @param statement SQL statement to the Exasol database
     */
    public ExasolDatabaseCleaner(final Statement statement) {
        this.statement = statement;
    }

    /**
     * Drop all existing database objects
     *
     * @throws SQLException if failed to delete an object
     */
    public void cleanDatabase() throws SQLException {
        purgeObjects();
        purgeConnections();
        purgeUsers();
        purgeRoles();
    }

    private void purgeConnections() throws SQLException {
        try (final ResultSet resultSet = this.statement
                .executeQuery("SELECT CONNECTION_NAME FROM EXA_ALL_CONNECTIONS")) {
            while (resultSet.next()) {
                final String connectionName = resultSet.getString("CONNECTION_NAME");
                final String dropCommand = "DROP CONNECTION IF EXISTS \"" + connectionName + "\"";
                LOGGER.fine(dropCommand);
                this.statement.executeUpdate(dropCommand);
            }
        }
    }

    private void purgeObjects() throws SQLException {
        try (final ResultSet resultSet = this.statement.executeQuery(
                "SELECT OBJECT_NAME, OBJECT_TYPE, OBJECT_IS_VIRTUAL FROM SYS.EXA_ALL_OBJECTS WHERE ROOT_NAME IS NULL ORDER BY CREATED DESC")) {
            while (resultSet.next()) {
                final String objectName = resultSet.getString("OBJECT_NAME");
                final String objectType = (resultSet.getBoolean("OBJECT_IS_VIRTUAL") ? "VIRTUAL " : "")
                        + resultSet.getString("OBJECT_TYPE");
                dropObject(objectName, objectType);
            }
        }
    }

    private void dropObject(final String objectName, final String objectType) throws SQLException {
        if (objectType.equals("VIRTUAL TABLE") || objectType.equals("TABLE") || objectType.equals("SCRIPT")) {
            return;
        }
        final StringBuilder dropCommandBuilder = new StringBuilder("DROP ");
        if (objectType.equals("VIRTUAL SCHEMA")) {
            dropCommandBuilder.append("FORCE ");
        }
        dropCommandBuilder.append(objectType).append(" IF EXISTS \"").append(objectName).append("\"");
        if (objectType.equals("SCHEMA") || objectType.equals("VIRTUAL SCHEMA")) {
            dropCommandBuilder.append(" CASCADE");
        }
        final String dropCommand = dropCommandBuilder.toString();
        LOGGER.fine(dropCommand);
        this.statement.executeUpdate(dropCommand);
    }

    private void purgeUsers() throws SQLException {
        try (final ResultSet resultSet = this.statement.executeQuery("SELECT USER_NAME FROM EXA_ALL_USERS")) {
            while (resultSet.next()) {
                final String userName = resultSet.getString("USER_NAME");
                if (!userName.equals("SYS")) {
                    final String dropCommand = "DROP USER \"" + userName + "\"";
                    LOGGER.fine(dropCommand);
                    this.statement.executeUpdate(dropCommand);
                }
            }
        }
    }

    private void purgeRoles() throws SQLException {
        final List<String> builtInRoles = List.of("PUBLIC", "DBA");
        try (final ResultSet resultSet = this.statement.executeQuery("SELECT ROLE_NAME FROM EXA_ALL_ROLES")) {
            while (resultSet.next()) {
                final String roleName = resultSet.getString("ROLE_NAME");
                if (!builtInRoles.contains(roleName)) {
                    final String dropCommand = "DROP ROLE \"" + roleName + "\"";
                    LOGGER.fine(dropCommand);
                    this.statement.executeUpdate(dropCommand);
                }
            }
        }
    }
}
