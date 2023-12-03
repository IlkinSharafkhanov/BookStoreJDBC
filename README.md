# Bookstore Management System

The Bookstore Management System is a Java application that helps manage information related to authors, books, customers, and orders in a bookstore.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Code Overview](#code-overview)
- [Database Metadata Example](#database-metadata-example)


## Introduction

The Bookstore Management System provides a simple yet effective way to manage and organize information about authors, books, customers, and orders. It facilitates tasks such as inserting new records, updating existing ones, retrieving information, and deleting records. The system is designed to interact with a PostgreSQL database.

## Features

- Insert authors, books, customers, and orders
- Update book details
- Retrieve information about books, including authors and associated orders
- Delete books from the system
- Performing transactions (insertOrderWithBooksUpdate method)

## Getting Started

### Prerequisites

Before running the Bookstore Management System, ensure you have the following prerequisites installed:

- [Java Development Kit (JDK)](link_to_jdk)
- [PostgreSQL Database](link_to_postgresql)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/IlkinSharafkhanov/BookStoreJDBC.git
    ```

2. Change into the project directory:

    ```bash
    cd BookStoreJDBC
    ```

3. Compile and run the Java application:

    ```bash
    javac -cp .:path/to/postgresql.jar com/ilkin/Main.java
    java -cp .:path/to/postgresql.jar com.ilkin.Main
    ```

## Usage

The Bookstore Management System provides a user-friendly terminal interface. Follow the on-screen prompts to perform various tasks such as inserting, updating, retrieving, and deleting records.

```bash

# Example command to run the application
java -cp .:path/to/postgresql.jar com.ilkin.Main

/
|-- src/                # Source code files
|   |-- com/ilkin/      # Java packages for different components
|-- data/               # Data files, if any
|-- doc/                # Documentation files
|-- tests/              # Test files
|-- README.md           # Project README
|-- LICENSE             # License file

```


## Code Overview

The main components of the code are organized into Java packages. Each package corresponds to a specific entity in the bookstore management system, such as authors, books, customers, and orders.

## Database Metadata Example

The `MetaDataExample` class provides an example of how to retrieve metadata information from the PostgreSQL database. It displays information about tables, columns, primary keys, and foreign keys.

