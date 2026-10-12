# Money Manager API 💰

A backend REST API for a Money Manager application built with **Java and Spring Boot**. The project aims to provide secure user authentication and personal finance management features.

## 🚀 Features

- **User Registration** – Register new users with their details.
- **User Authentication** – Manage user login and authentication.
- **Email Verification** – Activate user accounts through email verification.
- **Profile Management** – Manage user profile information.
- **RESTful APIs** – Provide structured endpoints for frontend integration.
- **Secure Password Handling** – Protect user passwords using encryption.
- **Database Integration** – Store and manage application data.

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Security:** Spring Security
- **Database:** Configure according to the database used in the project
- **Build Tool:** Maven
- **Email Service:** Brevo SMTP
- **API Style:** REST

## 📁 Project Structure

```text
money-manager-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## ⚙️ Prerequisites

Before running the project, make sure you have installed:

- Java JDK (the version configured in the project)
- Maven (optional if using the Maven Wrapper)
- A supported database configured for the application
- A Brevo account if email verification is enabled

## 🔧 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/deeptimahisare1206/money-manager-api.git
```

### 2. Navigate to the Project Directory

```bash
cd money-manager-api
```

### 3. Configure Environment Variables

Configure your database credentials, email settings, and other application secrets using environment variables.

Example:

```text
BREVO_SMTP_KEY=your_smtp_key
```

Do not commit API keys, passwords, or other sensitive credentials to GitHub.

### 4. Build the Project

On Windows:

```bash
mvnw.cmd clean install
```

On macOS or Linux:

```bash
./mvnw clean install
```

### 5. Run the Application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The application will start on the port configured in `application.properties`.

## 🔗 API Endpoints

The following endpoints represent the planned or implemented API structure. Verify each endpoint against the current source code.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1.0/register` | Register a new user |
| POST | `/api/v1.0/login` | Authenticate a user |
| GET | `/api/v1.0/activate` | Activate a user account |
| GET | `/api/v1.0/health` | Health check, if configured |

## 🔐 Security

- Keep sensitive configuration in environment variables.
- Hash passwords before storing them.
- Validate incoming requests.
- Use appropriate authentication and authorization rules.
- Never commit real API keys or database credentials.

## 🧪 Testing

Run the test suite using the Maven Wrapper:

```bash
mvnw.cmd test
```

On macOS or Linux:

```bash
./mvnw test
```

## 🗺️ Future Enhancements

- Income and expense management
- Transaction history
- Budget creation and tracking
- Monthly financial summaries
- Expense categorization
- Financial reports and analytics
- API documentation with Swagger/OpenAPI

## 👩‍💻 Author

**Deepti Mahisare**

GitHub: [@deeptimahisare1206](https://github.com/deeptimahisare1206)

---

⭐ If you find this project useful, consider giving the repository a star.
