# QuizSpark 🚀

QuizSpark is a modern quiz platform designed to facilitate easy creation and solving of quizzes. The application allows for quiz management through an admin panel and ensures security with the implementation of Spring Security.

## Technologies 🛠️

This project was built using the following technologies:

![React](https://img.shields.io/badge/-React-61DAFB?style=flat-square&logo=react&logoColor=white)
![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black)
![Spring](https://img.shields.io/badge/-Spring-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/-Java-007396?style=flat-square&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-336791?style=flat-square&logo=postgresql&logoColor=white)

## Demo 🎥

Explore QuizSpark in action through these snapshots:

[![Demo 1](https://github.com/michalz18/QuizSpark/assets/116461262/07f4966d-ced0-46cc-8217-5a8a1190853b)](https://github.com/michalz18/QuizSpark/assets/116461262/07f4966d-ced0-46cc-8217-5a8a1190853b)

[![Demo 2](https://github.com/michalz18/QuizSpark/assets/116461262/dad288bc-3d65-41dd-a7f4-c1f7b21b4897)](https://github.com/michalz18/QuizSpark/assets/116461262/dad288bc-3d65-41dd-a7f4-c1f7b21b4897)

[![Demo 3](https://github.com/michalz18/QuizSpark/assets/116461262/e0d2eb23-bec2-4ea2-879e-743bc1fd1af8)](https://github.com/michalz18/QuizSpark/assets/116461262/e0d2eb23-bec2-4ea2-879e-743bc1fd1af8)

## Features 🌟

- Creation and solving of quizzes.
- Admin panel for quiz management.
- Security features based on Spring Security.
- API documentation available through Javadoc.

## How to Run 🏃

1. **Clone the repository:**
    ```
    git clone <repository-url>
    ```
2. **Install dependencies:**
    ```
    // For frontend
    cd frontend && npm install
    
    // For backend
    cd QuizSpark && ./mvnw install
    ```
3. **Create PostgreSQL database connection:**
   - Install PostgreSQL and create a new database.
   - Update the `src/main/resources/application.properties` file with your database details:
    ```properties
    # Database Configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. **Generate a 256-bit encryption key:**
   
   - You can do it for example on this website: https://acte.ltd/utils/randomkeygen
   - Update the `src/main/resources/application.properties` file with your generated key:
    ```properties
    # Security
    jwt.secret-key=your_new_secret_key
    ```
    
6. **Run the application:**
    ```
    // Frontend
    npm start
    
    // Backend
    ./mvnw spring-boot:run
    ```

## Generating Documentation 📚

To generate Javadoc documentation for the backend, use the following command in the project root directory: 

`mvn javadoc:javadoc`

Documentation will be generated in `target/site/apidocs`.
