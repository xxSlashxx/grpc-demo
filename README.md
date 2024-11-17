# gRPC-Demo

## Overview
This repository contains a simple gRPC API. The demo showcases how to set up and use gRPC in a real-world microservices scenario with a focus on implementing a gRPC service, client, and communication. It is designed to help developers understand the basics of gRPC and how to integrate it into your own projects.

## What is gRPC?
gRPC is a high-performance, language-agnostic remote procedure call (RPC) framework developed by Google. It uses HTTP/2 for transport, Protocol Buffers (protobuf) as the interface definition language, and it is designed to make it easier to build efficient and robust distributed systems. gRPC is ideal for connecting microservices, enabling communication between client and server applications with low latency and high throughput.

## Services
This demo consists of the following service:
- **Products Service:** Handles product information

## API Endpoints
### Products Service
**CreateProduct:** Creates a new product  
**GetProduct:** Retrieve the product details for the given id  
**DeleteProduct:** Deletes the product with the given id

## Getting Started
### Prerequisites
- Java 21 or higher
- Git
- Docker

### Clone the Repository
    git clone https://github.com/xxSlashxx/grpc-demo
    cd grpc-demo

### Running the Application

    docker compose up

### Accessing the Services
The service's endpoints can be accessed through `grpc://localhost:9090`.

### Configuration
Configuration settings can be found in the application.properties files located in the service's src/main/resources directory. You can customize properties like database connections and port numbers.
