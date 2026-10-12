# Employee Creator – Cucumber Tests

This repository contains the automated end-to-end tests for the **[Employee Creator Project](https://github.com/jodielmclaughlin/EmployeeCreatorProject)**.

The project uses **Selenium WebDriver, Cucumber, and TestNG** to test the Employee Creator application's functionality through browser-based scenarios written in Gherkin.

## Technologies Used

- **Java** – test automation code
- **Maven** – dependency management and test execution
- **Selenium WebDriver** – browser automation
- **Cucumber** – behaviour-driven development (BDD) and Gherkin scenarios
- **TestNG** – test execution and reporting
- **Chrome** – browser used for automated testing

## Test Coverage

The automated tests cover key employee management functionality, including:

- Viewing the employee list
- Creating employees
- Editing employee details
- Deleting employees
- Form validation and error handling
- Authentication and role-based access

## Project Structure

- `src/test/java/` – step definitions, page objects, hooks, configuration, and test utilities
- `src/test/resources/features/` – Cucumber feature files containing Gherkin scenarios
- `pom.xml` – Maven dependencies and project configuration

The project follows the **Page Object Model (POM)** to separate page interactions from test steps, with reusable methods and explicit waits to improve maintainability and test reliability.

## Running the Tests Locally

1. Clone this repository.
2. Ensure Java, Maven, and Google Chrome are installed.
3. Clone the [Employee Creator Project](https://github.com/jodielmclaughlin/EmployeeCreatorProject) repository.
4. Configure the required application settings and test credentials.
5. Start the Employee Creator backend and frontend, ensuring the database is running and populated with the required test data.
6. Run the Cucumber tests from this repository:

   ```bash
   mvn test
   ```

The frontend, backend, and database must be available before running the tests. Check the project configuration for the expected URLs, credentials, and environment variables.

## Continuous Integration

The tests run through **GitHub Actions**, which automates the test execution as part of the CI workflow. The workflow starts the required application services, configures the test environment, and runs the Cucumber test suite.

## Related Repository

**Employee Creator Application:**  
https://github.com/jodielmclaughlin/EmployeeCreatorProject

The Employee Creator Project repository contains the application being tested; this repository contains its automated Cucumber test suite.
