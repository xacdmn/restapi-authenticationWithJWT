## Recruitment Application API Documentation

Welcome to the Recruitment Application API documentation. Our service provides authentication functionalities for logging in and registering users as well as managing and fetching job details.

### Prerequisites:

- Ensure you have a running instance of the Recruitment application.
- All endpoints are prefixed with `/api`.

### API Documentation using Swagger:

You can access the detailed API documentation with interactive functionalities using the Swagger UI interface:

[Recruitment Application Swagger UI](http://localhost:8080/swagger-ui/index.html#)

---

### Available API Endpoints:

#### Authentication Endpoints:

1. **Login a User:**
    - **Endpoint:** `/api/auth/login`
    - **Method:** POST
    - **Request Body:**
    ```json
    {
        "username":"<USERNAME>",
        "password":"<PASSWORD>"
    }
    ```
    - **Response:** Authentication token or Error message
    - **Description:** Allows users to login with a username and password.

2. **Register a User:**
    - **Endpoint:** `/api/auth/register`
    - **Method:** POST
    - **Request Body:**
    ```json
    {
        "username":"<USERNAME>",
        "password":"<PASSWORD>"
    }
    ```
    - **Response:** Success message or Error message
    - **Description:** Registers a new user to the system.

#### Job Management Endpoints:

1. **Fetch List of Jobs (with key-value):**
    - **Endpoint:** `/api/job/get-list-tittle`
    - **Method:** GET
    - **Response:** List of jobs with their titles in a key-value format.
    - **Description:** Fetches a list of all available jobs with their titles.

2. **Fetch List of Job Titles (without key-value):**
    - **Endpoint:** `/api/job/get-list`
    - **Method:** GET
    - **Response:** List of job titles.
    - **Description:** Fetches a list of all job titles available.

3. **Fetch Job Description by ID:**
    - **Endpoint:** `/api/job/{id}/description`
    - **Method:** GET
    - **Response:** Job description or Error message
    - **Description:** Fetches the description of a job by its unique identifier.

---

#### Notes:

- Ensure that you provide the necessary headers and authentication tokens as required for accessing secured endpoints.
- In case of errors, kindly refer to the error message provided in the response for troubleshooting.
- Always ensure to keep your authentication tokens secure and do not share them with unauthorized individuals.

For more detailed information, such as request and response schemas, examples, and other intricacies, please refer to the Swagger UI link provided above.
