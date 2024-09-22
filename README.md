# Condo Complaints

This project is built
using [Java 17](https://community.chocolatey.org/packages/openjdk/17.0.2), [Docker](https://www.docker.com/) for
containers, and Chocolatey on Windows for easy package installation.

## Installation

Install [Chocolatey](https://chocolatey.org/install), a package manager for Windows similar to brew (macOS) / apt (
Linux).

Open a terminal or command prompt in administrator mode and run:

```bash
choco install openjdk17
choco install docker-desktop
```

### Lombok Configuration

This project uses **Lombok** for reducing boilerplate code (e.g., getters, setters, constructors, etc.). Lombok is
already configured in the `pom.xml` with the following dependency:

```xml

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```

Lombok Installation in Your IDE
To ensure Lombok works correctly in your local development environment, you need to install and configure Lombok for
your IDE. Follow the steps below:

<u>For IntelliJ IDEA:</u>

1) Go to File -> Settings -> Plugins.
2) Search for Lombok and click Install.
3) After installation, enable annotation processing:
   Navigate to File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors, and check Enable
   annotation processing.

<u>For Eclipse:</u>

1) Download the Lombok JAR from the official site.
2) Run the JAR by executing

```
java -jar lombok.jar
```

3) The Lombok installer will open; select your Eclipse IDE installation folder and proceed with the installation.
4) Restart Eclipse and ensure annotation processing is enabled by going to:
   Preferences -> Maven -> Annotation Processing -> Enable.

### Configuring the Environment

Start the database with Docker:

```bash
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=ROOTfancyPass25!" -p 1433:1433 --name condo_complaints -d mcr.microsoft.com/mssql/server:2022-latest
```

## Database Initialization

### Script Location

The initialization script is located at

```
src/main/resources/initial_data.sql
```

### Executing the Script

1. Open your SQL management tool (e.g., SQL Server Management Studio).
2. Connect to your target database.
3. Open the `initial_data.sql` script from the specified location.
4. Review the script contents to ensure compatibility with your existing database schema.
5. Execute the script to populate the database with the necessary initial data.

## Usage

```bash
mvn install
mvn spring-boot:run 

```

## Postman Collection Import

### Collection Location

The Postman collection is located at

```bash
src/main/resources/condo_complaints.postman_collection.json
```

### Importing the Collection

1. Launch Postman.
2. Navigate to the "Collections" tab.
3. Click on "Import".
4. Select the file

```bash
condo_complaints.postman_collection.json
``` 

from the specified directory.

5. Upon successful import, you will have access to a suite of pre-configured API requests.

## License

[MIT](https://choosealicense.com/licenses/mit/)
