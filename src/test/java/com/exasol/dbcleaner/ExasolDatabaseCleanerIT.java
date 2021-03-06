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
    private static final ExasolContainer<? extends ExasolContainer<?>> CONTAINER = new ExasolContainer<>()
            .withReuse(true);
    private static Statement STATEMENT;
    private static ExasolDatabaseCleaner CLEANER;

    @BeforeAll
    static void beforeAll() throws SQLException {
        STATEMENT = CONTAINER.createConnectionForUser(CONTAINER.getUsername(), CONTAINER.getPassword())
                .createStatement();
        CLEANER = new ExasolDatabaseCleaner(STATEMENT);
    }

    @AfterEach
    void after() throws SQLException {
        CLEANER.cleanDatabase();
    }

    @Test
    void testPurgeSchema() throws SQLException {
        createSchema();
        CLEANER.cleanDatabase();
        assertDoesNotThrow(this::createSchema);
    }

    @Test
    void testPurgeTable() throws SQLException {
        createSchema();
        createTable();
        CLEANER.cleanDatabase();
        createSchema();
        assertDoesNotThrow(this::createTable);
    }

    @Test
    void testPurgeConnection() throws SQLException {
        createConnection();
        CLEANER.cleanDatabase();
        assertDoesNotThrow(this::createConnection);
    }

    @Test
    void testPurgeUser() throws SQLException {
        createUser();
        CLEANER.cleanDatabase();
        assertDoesNotThrow(this::createUser);
    }

    @Test
    void testPurgeRole() throws SQLException {
        createRole();
        CLEANER.cleanDatabase();
        assertDoesNotThrow(this::createRole);
    }

    @Test
    void testPurgeFunction() throws SQLException {
        createFunction("S1");
        CLEANER.cleanDatabase();
        assertDoesNotThrow(() -> createFunction("S1"));
    }

    @Test
    void testPurgeFunctionWithNonImplicitSchemaName() throws SQLException {
        createFunction("S1");
        createFunction("S2");
        CLEANER.cleanDatabase();
        assertDoesNotThrow(() -> createFunction("S1"));
    }

    private void createFunction(final String schemaName) throws SQLException {
        STATEMENT.executeUpdate("CREATE SCHEMA " + schemaName + ";");
        STATEMENT.executeUpdate("CREATE FUNCTION " + schemaName
                + ".MY_FUNCTION () RETURN VARCHAR(10)\n BEGIN\n RETURN 'test';\n END\n /");
    }

    private void createRole() throws SQLException {
        STATEMENT.executeUpdate("CREATE ROLE test_role;");
    }

    private void createUser() throws SQLException {
        STATEMENT.executeUpdate("CREATE USER user_1 IDENTIFIED BY \"h12_xhz\"");
    }

    private void createSchema() throws SQLException {
        STATEMENT.executeUpdate("CREATE SCHEMA TEST;");
    }

    private void createTable() throws SQLException {
        STATEMENT.executeUpdate("CREATE TABLE TEST.TEST_TABLE (ID VARCHAR(10) UTF8);");
    }

    private void createConnection() throws SQLException {
        STATEMENT.executeUpdate("CREATE CONNECTION exa_connection TO '192.168.6.11:8563';");
    }
}