# ******Vehicle Manager Application******

The Vehicle Manager Application is a robust system designed to efficiently manage organizations, companies, customers, and their intricate relationships within the domain of vehicle management.


Description
The Vehicle Manager Application is built to streamline the complexities involved in managing entities within a vehicle management ecosystem. It facilitates the seamless handling of organizations, companies, and customers, ensuring an organized and efficient process flow.

## Technologies Used

The application is developed using the following technologies and frameworks:

* Java
* Spring Boot
* Lombok
* Swagger 3
* MySQL
* Other relevant dependencies used in the project
Installation

## To run this application locally, follow these steps:

* Clone this repository to your local machine.
* Ensure you have Java and Maven installed.
* Open the project in your preferred IDE.
* Configure the application properties for the database connection if required.
* Build the project using Maven: mvn clean install.
* Run the application: mvn spring-boot:run.
Usage
Upon launching the application, access the endpoints defined in the API to perform various operations related to organizations, companies, and customers. Please ensure the implementation of proper authentication and authorization mechanisms before deploying this application in a production environment.

## Endpoints

The application provides a set of well-defined endpoints that leverage the facade pattern to manage organization, company, and customer relationships efficiently:


* POST `/api/v1/organization/create` - Create a new organization.

* POST `/api/v1/organization/createOrganizationAndCompany` - Create an organization and its associated company.

* GET `/api/v1/organization/getOrganizationAndCompany/{organizationId}` - Fetch organization details along with associated companies.

* GET `/api/v1/organization/get/{id}` - Retrieve a specific organization by its ID.

* GET `/api/v1/organization/getAll `- Retrieve details of all organizations.

* PUT /`api/v1/organization/update/{id} `- Update an organization.

* DELETE `/api/v1/organization/delete/{id}` - Delete an organization.

* GET `/api/v1/organization/getOrganizationAndCompanyAndCustomer/{organizationId}` - Fetch organization details, associated companies, and customers.

* POST `/api/v1/organization/createOrganizationAndCompanyAndCustomer` - Establish relationships between organization, company, and customers.
  
* etc..............
