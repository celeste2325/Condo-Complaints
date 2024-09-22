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

## Configuring the Environment

Start the database with Docker:

```
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=ROOTfancyPass25!" -p 1433:1433 --name condo_complaints -d mcr.microsoft.com/mssql/server:2022-latest
```

## Database Initialization

### Script Location

The initialization script is located at

```bash
src/main/resources/initial_data.sql
```

### Executing the Script

1. Open your SQL management tool (e.g., SQL Server Management Studio).
2. Connect to your target database.
3. Open the `initial_data.sql` script from the specified location.
4. Review the script contents to ensure compatibility with your existing database schema.
5. Execute the script to populate the database with the necessary initial data.

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
