Project to have a control of Patients (API)
============================================

We'll find resources to manager patients with histories.

## Architecture by Tiers

````bash
├── api
│   ├── api.iml
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── org
│       │   │       └── encinas
│       │   │           └── api
│       │   │               ├── ApiApplication.java
│       │   │               ├── configuration
│       │   │               │   └── ApiConfiguration.java
│       │   │               └── web
│       │   │                   ├── HistoryController.java
│       │   │                   ├── PatientController.java
│       │   │                   └── error
│       │   │                       └── handler
│       │   │                           └── ErrorHandler.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── org
│                   └── encinas
│                       └── api
├── business
│   ├── business.iml
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── org
│       │           └── encinas
│       │               └── business
│       │                   ├── HistoryService.java
│       │                   ├── PatientService.java
│       │                   ├── configuration
│       │                   │   └── BusinessConfiguration.java
│       │                   ├── dtos
│       │                   │   ├── HistoryDto.java
│       │                   │   └── PatientDto.java
│       │                   ├── parsers
│       │                   │   ├── HistoryParser.java
│       │                   │   ├── Parser.java
│       │                   │   └── PatientParser.java
│       │                   └── responses
│       │                       └── RequestResponse.java
│       └── test
│           └── java
│               └── org
│                   └── encinas
│                       └── business
│                           ├── PatientServiceTests.java
│                           └── dtos
│                               └── PatientDtoTests.java
├── dao
│   ├── dao.iml
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── org
│       │           └── encinas
│       │               └── dao
│       │                   ├── configuration
│       │                   │   └── DaoConfiguration.java
│       │                   ├── entity
│       │                   │   ├── HistoryEntity.java
│       │                   │   └── PatientEntity.java
│       │                   └── repository
│       │                       ├── HistoryDao.java
│       │                       └── PatientDao.java
│       └── test
│           └── java
│               └── org
│                   └── encinas
│                       └── dao
│                           └── DaoApplicationTests.java
├── pom.xml
└── springboot-rec-architecture.iml
````
