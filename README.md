# Bajaj Finserv Health API Challenge

## Overview

This project is a Spring Boot REST API developed as part of the Bajaj Finserv Health Campus Hiring Challenge.

The API processes a mixed array of inputs containing:

* Numbers
* Alphabets
* Special Characters
* Alphanumeric Strings

It categorizes the data and returns a structured response with statistics and summary information.

---

## Tech Stack

* Java 21
* Spring Boot
* Maven
* REST API
* Jakarta Validation
* JUnit 5
* Mockito

---

## Project Structure

src/main/java

* controller
* service
* dto
* exception

The application follows a layered architecture:

* Controller Layer
* Service Layer
* DTO Layer
* Global Exception Handling

---

## API Endpoint

### POST /bfhl

### Request Header

```http
X-Request-Id: REQ-1001
```

### Sample Request

```json
{
  "data": ["A", "1", "22", "$", "B", "7"]
}
```

### Sample Response

```json
{
  "success": true,
  "requestId": "REQ-1001",
  "oddNumbers": ["1", "7"],
  "evenNumbers": ["22"],
  "alphabets": ["A", "B"],
  "specialCharacters": ["$"],
  "sum": "30"
}
```

---

## Features Implemented

### Data Processing

* Number Identification
* Alphabet Identification
* Special Character Identification
* Alphanumeric String Processing

### Statistics

* Odd Numbers
* Even Numbers
* Sum of Numbers
* Largest Number
* Smallest Number
* Sorted Numbers

### Alphabet Analysis

* Alphabet Count
* Alphabet Frequency
* Vowel Count
* Longest Alphabetic Value
* Shortest Alphabetic Value

### Duplicate Handling

* Duplicate Detection
* Unique Element Count

### Input Validation

* Null Values Ignored
* Empty Strings Ignored
* Whitespace Strings Ignored

### Additional Information

* Processing Time
* Request Id Tracking
* Summary Object

---

## Exception Handling

Global exception handling is implemented using:

```java
@RestControllerAdvice
```

to provide consistent API error responses.

---

## Running the Project

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Project

```bash
cd bajaj-finserv-health-api-rupesh-borse
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Testing

The API can be tested using:

* Postman
* IntelliJ HTTP Client
* cURL

---

## Author

Rupesh Borse

Information Technology Engineering

Dr. D. Y. Patil Institute of Technology, Pune

GitHub:
https://github.com/RupeshBorse17


LinkedIn:
https://linkedin.com/in/rupesh-borse-a52432283
