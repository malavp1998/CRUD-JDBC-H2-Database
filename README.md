# Spring configuration with H2 database using JDBC
In this project, we demonstrate how to create a JDBC connection to the H2 database and how to perform CRUD (Create, Retrieve, Update, and Delete) operations with the H2 database.

## API

Start the spring boot application and run the following URLs in Postman or another API testing tool.                          
  

| HTTP method  | URL       |  Description      |
| :--------  | :------------------------- |:------------------------- |
| `GET`  |http://localhost:8080/employees | Get all employees |
| `GET`  |http://localhost:8080/employees/${id} | Get employee by id |
| `DELETE`  |http://localhost:8080/employee/${id} | Delete employee by id |
| `PUT`  |http://localhost:8080/employee | Update employee (send new employee through request body) |
| `POST`  |http://localhost:8080/employees | Post/save employee(send new employee through request body) |

## Upload and Download files with Spring Boot REST API
I create another controller for uploading and downloading files using URLs in this project.
In the directory, you can see the uploaded files.
#### src/main/resources/files
You can learn how to send a file through Postman [here](https://stackoverflow.com/questions/39037049/how-to-upload-a-file-and-json-data-in-postman).


## API

| HTTP method  | URL       |  Description      |
| :--------  | :------------------------- |:------------------------- |
| `POST`  | http://localhost:8080/upload | To upload a file|
| `POST`  | http://localhost:8080/download | To download a file|


#### Upload
<img width="1138" alt="Screenshot 2022-01-15 at 3 42 01 PM" src="https://user-images.githubusercontent.com/48180372/149622569-94cf66f9-9349-4e67-8c3a-9cc5438ea005.png">

### Download
<img width="1132" alt="Screenshot 2022-01-15 at 6 12 29 PM" src="https://user-images.githubusercontent.com/48180372/149622652-a8f017ad-4bf2-44e4-be01-9e0dc461c0e7.png">
