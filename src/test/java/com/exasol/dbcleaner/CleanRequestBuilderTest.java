package com.exasol.dbcleaner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CleanRequestBuilderTest {

    @Test
    void test_build() {
        final CleanRequest underTest = CleanRequest.builder()
                .setCleanConnections(false)
                .setCleanObjects(false)
                .setCleanUsers(false)
                .setCleanRoles(false)
                .build();

        assertFalse(underTest.isCleanConnections());
        assertFalse(underTest.isCleanAll());
        assertFalse(underTest.isCleanObjects());
        assertFalse(underTest.isCleanRoles());
        assertFalse(underTest.isCleanUsers());
    }
}