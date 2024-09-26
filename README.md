# Task Service Application

## Description
The Task Service Application is a Spring Boot application designed for asynchronously
creating and managing treatment tasks based on treatment plans. It utilizes an 
H2 in-memory database for storing task and plan information. The focus in here more on executor service
for task creation rather than business logic. As there are no requirements about scheduling params
the executor service may be configurable from time running perspective.

## Key Components
- **TaskServiceApplication**: The main entry point for the application, responsible for
- launching the Spring Boot application and enabling asynchronous processing.
- **TaskCreator**: A simplified component that handles the asynchronous creation of treatment
- tasks from current treatment plans.
- **Database Configuration**: The application uses an embedded H2 database for data
- storage and Hibernate for object management.
## Prerequisites

- **Java-17**
- **Apache Maven**
    
