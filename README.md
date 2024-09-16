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

## License

[MIT](https://choosealicense.com/licenses/mit/)
