# Notifier Application (Architecture)

## Overview
The notifier application consists of three main microservices:
- **Template-Manager**: Manages the templates for SMS and email notifications.
- **Processing-Service**: Triggers the use of templates, forms messages from the manager, and sends data to ActiveMQ based on priority.
- **Adapter-Service**: Receives messages from priority queues and routes them to SMS and email vendor services through another queue.

## Architecture Diagram
  
<image src="./images/architecture.png"></image>

## Component Interaction Flow

### a. User Interaction
- Users trigger message creation through an API request to the **Preprocessing** service.

### b. Preprocessing Flow
1. **API Call**: The Preprocessing service receives the request and retrieves the corresponding template from the **Template-Manager**.
2. **Message Formation**: The service populates the template with dynamic data, forming the final message.
3. **Send to ActiveMQ**: The service sends the formed message to **ActiveMQ**, specifying its priority.

### c. Adapter Flow
1. **Message Consumption**: The **Adapter** listens to messages in the priority queue from **ActiveMQ**.
2. **Routing**: Based on message content or type, the Adapter routes the message to either the SMS or email vendor service through the corresponding queue.

## Technology Stack
- **Template-Manager**: 
  - **Framework**: Quarkus
  - **Database**: PostgreSQL
  - **API**: GraphQL

- **Processing-Service**: 
  - **Framework**: Quarkus
  - **Message Broker**: ActiveMQ

- **Adapter-Service**: 
  - **Framework**: Quarkus
  - **Message Broker**: ActiveMQ (for receiving and routing messages)

## ER diagrams

### Template Manager

<image src="./images/manager.png"></image>

### Processing Service

<image src="./images/processing.png"></image>

### Adapter Service

<image src="./images/adapter.png"></image>

## Database Schema

<image src="./images/db.png"></image>

## Deployment Architecture
  To be added

## Security Considerations
  To be added

## Monitoring and Logging
  To be added

## Summary
This document provides a comprehensive overview of the notifier application, focusing on its architecture, component interactions, technology stack, and operational considerations. 
