## Overview
This is spring boot application for following modules and will add new more in future.
* Invoice
* Payment Term

I have implemented cron job which is in file- ReminderScheduler, is residing in package com.immobel.service.cron.service. As of now, to test it, I have set cron- at every minute. 

This is extensible to add more queries on any Service for REST APIs.

## Getting Started - Prerequisites
* Git Latest
* Java 8
* Maven 3.6.0

## How to run Server Locally
To build, execute:

    ./mvn clean build

To run the application with the local (development) profile, execute:

    ./mvn spring-boot:run

To run the application with another profile, execute:

    ./mvn run -Dspring.profiles.active=<profile_name>

Remember to run tests before pushing your code changes

    ./mvn clean test
    
Service package:
The API implementation will reside here and each resource method will call the respective method from controller.
Invoice APIs- com.immobel.service.controller.InvoiceController
Payment APIs- com.immobel.service.controller.PaymentTermController