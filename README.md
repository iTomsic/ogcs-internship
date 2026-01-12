# Customer Support Ticket System

Welcome to the OG-CS Internship!

In the following text you will read project requirements, coding and collaboration guidelines.

:warning: Read your task and guidelines carefully!

## Diagram

![Component Diagram](/docs/component-diagram.drawio.png)

## Requirements

### Technical

- You are tasked with designing (database) and creating a backend service in **Spring Boot**
- Make sure your code adheres to the best programming practices
- Make sure you use the newest stable versions (Spring Boot, Java, dependencies, Docker images, etc.)
- You will use **Postgres Database** and run it inside Docker using **Docker Compose** file
- To configure the application, it is recommended to use the **'application.yml'** file instead of 'application.properties'. This YAML-based configuration file allows for a more readable and organized approach to managing application properties.
- As a build tool, use **Gradle**.
- For confidential information inside application.yml (database credentials, ...) use **environment variables**
- Data coming into the API must be [validated](https://www.baeldung.com/spring-boot-bean-validation)
- To test the API use [Bruno](https://www.usebruno.com/) or Swagger

### Organizational

- Divide the project into smaller tasks so each team member can focus on specific modules while contributing equally
- Work together on key tasks like database design and project setup
- Keep in touch with your teammates to share progress and ideas (teams, discord, ...)

## Recommended path

- By reading the section [User stories & tasks](#user-stories--tasks) create a database model that fits all the requirements
- Create simple CRUD APIs for every entity without business logic (Objective: Familiarize with Spring Boot and project structure)
- Start adding business logic to existing CRUD APIs

## User stories & tasks

### Project initialization

- Initialize Spring Boot project using [Spring Initializr](https://start.spring.io/)
- Understand the structure of newly created Spring Boot project
- Create a Docker Compose file that starts up the PostgreSQL database
- Connect the Spring Boot application to the PostgreSQL database (Tip: Use **environment variables**)

### Employee Management

- The system should allow creating employee records with basic information (e.g., name, email, department).
- Enable updating employee information to keep records accurate.
- Provide the ability to deactivate employees so they cannot manage or pick up tickets.
- Employee records must not include authentication credentials; authentication is external.

### Ticket Creation & Management

- Any member of the public should be able to submit a ticket with title, category, description, priority, and their contact information (name, email) without requiring an account.
- Customers/public cannot update ticket information after submission.
- Employees can add internal notes or employee description to a ticket but cannot modify the original customer-provided description.
- Display all tickets in a list view for browsing existing issues and requests.
- Show complete ticket details, including customer description and any employee notes, to provide full context of any issue.

### Ticket Status Management

- Allow changing the status of a ticket (open, in progress, resolved, closed) according to the employee’s assigned department or scope.
- Enable filtering tickets by status to quickly locate open or unresolved tickets.
- Prevent any modifications to tickets that are in a closed state.

### Category Management

- Provide functionality to create support categories (e.g., billing, technical, general inquiry).
- Allow updating category information (name, description) to keep categorization relevant.
- Implement soft delete for categories: mark categories as inactive so they cannot be assigned to new tickets, but existing tickets retain the category.
- Allow assigning a category to a ticket to ensure proper classification.
- Inactive categories should be filtered out from category selection lists for new tickets.

### Ticket Assignment

- Enable employees to pick up tickets only within their assigned department or scope (e.g., finance employees can only pick finance tickets).
- Prevent employees from assigning themselves to tickets outside their scope.
- Allow reassignment of tickets within the same scope to balance workload.
- Display assignment information on tickets to indicate whether they are being handled.

### Ticket Metadata Updates

- Enable employees to update only ticket metadata fields (category, priority, tags, department) through a separate PUT/PATCH call.
- Prevent metadata updates on tickets outside the employee’s assigned scope.
- Maintain a separation between customer content (description) and employee metadata/notes to enforce workflow rules.

### Application Containerization

- Ensure the Spring Boot application is correctly containerized using the existing Dockerfile.
- Integrate the application into the pre-existing docker-compose setup.
- Verify the app runs inside the container and communicates correctly with other services.
- Use environment variables for configuration (e.g., database URL, ports).
- Document steps to build and start the application via docker-compose.

## Definition of Done – Service Layer

This section defines the quality standards and requirements that must be met before a service layer implementation is considered complete.

- All endpoints have input validation (e.g., required fields, correct formats) using DTOs.
- Exceptions are consistently handled with a global exception handler returning meaningful error responses.
- DTOs are used for requests and responses; entities are not exposed directly.
- Business rules are enforced (e.g., scope checks, metadata vs customer content separation, soft-delete for categories).
- Logging is in place for key actions (creation, updates, assignments, errors). Tip: Use `@Slf4j`

### API Design Guidelines

- Use **plural nouns** for resources, e.g., `/api/users`, `/api/tickets`, `/api/categories`.
- Use standard **HTTP methods** to indicate action:
  - `GET /api/tickets` – list tickets
  - `GET /api/tickets/{id}` – get ticket details
  - `POST /api/tickets` – create a new ticket
  - `PUT /api/tickets/{id}` – update full ticket (metadata only for employees)
  - `PATCH /api/tickets/{id}` – partial updates (e.g., metadata fields)
  - `DELETE /api/tickets/{id}` – delete ticket
- Use **query parameters** for filtering, sorting, and pagination, e.g., `/api/tickets?status=open&department=finance`.
- Return **appropriate HTTP status codes** for success and errors (200, 201, 400, 404, 403, 500, ...).
- Keep endpoints **resource-focused**, avoid verbs in URLs (no `/getTickets` or `/createTicket`).
- Nested resources only when logically connected, e.g., `/api/tickets/{id}/comments`.
- Include **meaningful error messages** and consistent response structures in JSON.
