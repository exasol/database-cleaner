# Exasol Database Cleaner

[![Build Status](https://github.com/exasol/database-cleaner/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/database-cleaner/actions/workflows/ci-build.yml)
[![Maven Central – database-cleaner](https://img.shields.io/maven-central/v/com.exasol/database-cleaner)](https://search.maven.org/artifact/com.exasol/database-cleaner)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Adatabase-cleaner&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Adatabase-cleaner)

This Java library allows you to delete all objects from an Exasol database. You can use it for example to isolate your tests.

## Usage

```java
new ExasolDatabaseCleaner(exasolSqlStatement).cleanDatabase();
```

## Additional Information

* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)