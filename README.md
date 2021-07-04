# Restaurant Management Software - Server Side
I changed my [_Restaurant Management Software_](https://github.com/Sorairono/Restaurant) project from a simple Java desktop application to a project with client-server architecture. This repo is the server-side of such project.

## Table of contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Contact](#contact)

## General Information
- Server-side for [_this repo_](https://github.com/Sorairono/RestaurantClient).
- Connected to a MongoDB instance (NoSQL database), which uses documents to store data.
- By converting documents to JSON type and reverse, the server carries out CRUD operations on the MongoDB database.
- These data in the type of JSON are then transfered back and forth between server and client using Java Restful Web Services (RESTful API).
- While singleton is used to avoid overload on the server, WebSocket is used to ensure connection between server and client.

## Project status
This project is no longer being worked on. When I felt like I had gained enough knowledge about server-side architectures to work for the team, I slowly stop working on this project. However, there are still room for improvement and one day I might continue this project from where I left off.

## Room for improvement
- Improve on multi-client to one server architecture (which client has more priority, multiple threads to process requests and responses faster,...).
- Create more features relating to manager stuff (right now this server-side only serves as a middle-point between client and database to write and access data).

## Contact 
Created by [@Sorairono](https://github.com/Sorairono) - feel free to contact me!
