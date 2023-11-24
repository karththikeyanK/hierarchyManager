# hierarchyManager
Vehicle Manager Application
Description
The Vehicle Manager Application is designed to manage organizations, companies, customers, and their relationships within a vehicle management system.

Table of Contents
Technologies Used
Installation
Usage
Endpoints
Contributing
License
Technologies Used
Java
Spring Boot
Lombok
Other relevant dependencies used in the project
Installation
To run this application locally, follow these steps:

Clone this repository to your local machine.
Make sure you have Java and Maven installed.
Open the project in your preferred IDE.
Configure the application properties for database connection if required.
Build the project using Maven: mvn clean install.
Run the application: mvn spring-boot:run.
Usage
Upon running the application, access the endpoints defined in the API to perform various operations related to organizations, companies, and customers.
Ensure proper authentication and authorization mechanisms are in place before deploying this application in a production environment.
Endpoints
The application provides the following endpoints:

POST /api/v1/organization/create - Create a new organization.
POST /api/v1/organization/createOrganizationAndCompany - Create an organization and associated company.
GET /api/v1/organization/getOrganizationAndCompany/{organizationId} - Fetch organization and associated companies.
GET /api/v1/organization/get/{id} - Fetch a specific organization by ID.
GET /api/v1/organization/getAll - Fetch all organizations.
PUT /api/v1/organization/update/{id} - Update an organization.
DELETE /api/v1/organization/delete/{id} - Delete an organization.
GET /api/v1/organization/getOrganizationAndCompanyAndCustomer/{organizationId} - Fetch organization, associated companies, and customers.
POST /api/v1/organization/createOrganizationAndCompanyAndCustomer - Create an organization, company, and customer relationships.
