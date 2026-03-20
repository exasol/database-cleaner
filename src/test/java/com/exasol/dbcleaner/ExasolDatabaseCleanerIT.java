package com.exasol.dbcleaner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;

@Testcontainers
class ExasolDatabaseCleanerIT {

    @Container
    @SuppressWarnings("resource") // Will be closed by @Container annotation
    private static final ExasolContainer<? extends ExasolContainer<?>> CONTAINER = new ExasolContainer<>()
            .withReuse(true);
    private static Statement statement;
    private static ExasolDatabaseCleaner cleaner;

    @BeforeAll
    static void beforeAll() throws SQLException {
        statement = CONTAINER.createConnectionForUser(CONTAINER.getUsername(), CONTAINER.getPassword())
                .createStatement();
        cleaner = new ExasolDatabaseCleaner(statement);
    }

    @AfterEach
    void after() throws SQLException {
        cleaner.cleanDatabase();
    }

    @Test
    void testPurgeSchema() throws SQLException {
        createSchema();
        cleaner.cleanDatabase();
        assertDoesNotThrow(this::createSchema);
    }

    @Test
    void testPurgeTable() throws SQLException {
        createSchema();
        createTable();
        cleaner.cleanDatabase();
        createSchema();
        assertDoesNotThrow(this::createTable);
    }

    @Test
    void testPurgeConnection() throws SQLException {
        createConnection();
        cleaner.cleanDatabase();
        assertDoesNotThrow(this::createConnection);
    }

    @Test
    void testPurgeUser() throws SQLException {
        createUser();
        cleaner.cleanDatabase();
        assertDoesNotThrow(this::createUser);
    }

    @Test
    void testPurgeRole() throws SQLException {
        createRole();
        cleaner.cleanDatabase();
        assertDoesNotThrow(this::createRole);
    }

    @Test
    void testPurgeFunction() throws SQLException {
        createFunction("S1");
        cleaner.cleanDatabase();
        assertDoesNotThrow(() -> createFunction("S1"));
    }

    @Test
    void testPurgeFunctionWithNonImplicitSchemaName() throws SQLException {
        createFunction("S1");
        createFunction("S2");
        cleaner.cleanDatabase();
        assertDoesNotThrow(() -> createFunction("S1"));
    }

    @Test
    void testPurgeSchemaOwnedByDifferentUser() throws SQLException {
        createDbaUserWithSchemaAndTable();
        cleaner.cleanDatabase();
        assertDoesNotThrow(this::createDbaUserWithSchemaAndTable);
    }

    private void createFunction(final String schemaName) throws SQLException {
        statement.executeUpdate("CREATE SCHEMA " + schemaName + ";");
        statement.executeUpdate("CREATE FUNCTION " + schemaName
                + ".MY_FUNCTION () RETURN VARCHAR(10)\n BEGIN\n RETURN 'test';\n END\n /");
    }

    private void createRole() throws SQLException {
        statement.executeUpdate("CREATE ROLE test_role;");
    }

    private void createUser() throws SQLException {
        statement.executeUpdate("CREATE USER user_1 IDENTIFIED BY \"h12_xhz\"");
    }

    private void createSchema() throws SQLException {
        statement.executeUpdate("CREATE SCHEMA TEST;");
    }

    private void createTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE TEST.TEST_TABLE (ID VARCHAR(10) UTF8);");
    }

    private void createConnection() throws SQLException {
        statement.executeUpdate("CREATE CONNECTION exa_connection TO '192.168.6.11:8563';");
    }

    private void createDbaUserWithSchemaAndTable() throws SQLException {
        statement.executeUpdate("CREATE USER other_user IDENTIFIED BY \"h12_xhz\"");
        statement.executeUpdate("GRANT DBA TO other_user");
        try (Statement otherStatement =
                     CONTAINER.createConnectionForUser("other_user", "h12_xhz")
                             .createStatement()) {
            otherStatement.executeUpdate("CREATE SCHEMA OTHER_SCHEMA;");
            otherStatement.executeUpdate(
                    "CREATE TABLE OTHER_SCHEMA.T1 (ID INT);");
        }
    }

}
