## SPRING BOOT STARTER

This is a simple example of Spring Boot starter, which:
- **Enforces checkstyle for child project.**
It is done by specifying parent POM, where checkstyle is configured
- **Provides TestContainers postgres autoconfiguration.**
It is done by specifying parent as POM dependency, where DataSource is autoconfigured to use TestContainers.

The code consists of 3 parts:
1. **starter** package. 

It holds the parent POM for other projects. Checkstyle is configured here (it should be using publicly accessible rules xml file, e.g. shared via GitHub) 

2. **web-starter** package. 

It uses **starter** POM as parent. This package contains web dependencies, and stores TestContainers DB autoconfiguration which is convenient for development usage. Every project that has **web-starter** as POM dependency will be provided with postgres database out-of-the-box.

3. **service** package.

It uses **starter** POM as parent and **web-starter** as POM dependency. Other services which leverage **web-starter** might be created in the similar manner.

If application property `use-test-containers: true` in **service** specified, then autoconfigured postgres from TestContainers is used. To use other connection string, `spring.datasource` properties should be explicitly overriden in `application.yaml`

To launch this example:
- Use Maven `clean install` for **starter** project
- Use Maven `clean install` for **web-starter** project
- Run **service** app.

You should see during the builds that checkstyle rules are evaluated. The app should run with autoconfigured postgres.