# Exasol Database Cleaner

[![Build Status](https://travis-ci.com/exasol/database-cleaner.svg?branch=main)](https://travis-ci.com/exasol/database-cleaner)
[![Maven Central](https://img.shields.io/maven-central/v/com.exasol/database-cleaner)](https://search.maven.org/artifact/com.exasol/database-cleaner)

This Java library allows you to delete all objects from an Exasol database. You can use it for example to isolate your tests.

## Usage

```java
new ExasolDatabaseCleaner(exasolSqlStatement).cleanDatabase();
```

## Additional Information

* [Changelog](doc/changes/changelog.md)

## Dependencies

### Run Time Dependencies

This project requires a Java Runtime version 11 or later.

| Dependency                                                                          | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Exasol JDBC driver](https://docs.exasol.com/connect_exasol/drivers/jdbc.htm)       | Run SQL statements on Exasol via JDBC                  | EXAClient License             |

### Test Dependencies

| Dependency                                                                          | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Java Hamcrest](http://hamcrest.org/JavaHamcrest/)                                  | Checking for conditions in code via matchers           | BSD License                   |
| [JUnit](https://junit.org/junit5)                                                   | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Exasol Testcontainers][exasol-testcontainers]                                      | Exasol extension for the Testcontainers framework      | MIT License                   |

### Build Dependencies

| Plug-in                                                                             | Purpose                                                | License                       |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Apache Maven](https://maven.apache.org/)                                           | Build tool                                             | Apache License 2.0            |
| [Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)    | Setting required Java version                          | Apache License 2.0            |
| [Maven GPG Plugin](https://maven.apache.org/plugins/maven-gpg-plugin/)              | Code signing                                           | Apache License 2.0            |
| [Maven Enforcer Plugin][maven-enforcer-plugin]                                      | Controlling environment constants                      | Apache License 2.0            |
| [Maven Javadoc Plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)      | Creating a Javadoc JAR                                 | Apache License 2.0            |
| [Maven Jacoco Plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html)          | Code coverage metering                                 | Eclipse Public License 2.0    |
| [Maven Source Plugin](https://maven.apache.org/plugins/maven-source-plugin/)        | Creating a source code JAR                             | Apache License 2.0            |
| [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)   | Unit testing                                           | Apache License 2.0            |
| [Project Keeper Maven Plugin][project-keeper-maven-plugin]                          | Checking project structure                             | MIT License                   |
| [Sonatype OSS Index Maven Plugin][sonatype-oss-index-maven-plugin]                  | Checking Dependencies Vulnerability                    | ASL2                          |
| [Versions Maven Plugin][versions-maven-plugin]                                      | Checking if dependencies updates are available         | Apache License 2.0            |

<!--@formatter:off -->
[maven-enforcer-plugin]: http://maven.apache.org/enforcer/maven-enforcer-plugin/
[oft-maven-plugin]: https://github.com/itsallcode/openfasttrace-maven-plugin
[project-keeper-maven-plugin]: https://github.com/exasol/project-keeper-maven-plugin
[sonatype-oss-index-maven-plugin]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[versions-maven-plugin]: https://www.mojohaus.org/versions-maven-plugin/
[exasol-testcontainers]: https://github.com/exasol/exasol-testcontainers
<!-- @formatter:on -->