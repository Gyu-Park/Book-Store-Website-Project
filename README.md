## Online Bookstore Project (Software Engineering Method)
Public DNS: http://ec2-3-137-184-44.us-east-2.compute.amazonaws.com

## Group Members
- Frontend <br />
&nbsp;&nbsp;&nbsp;Raymond Lin <br />
&nbsp;&nbsp;&nbsp;Dimitrios Koloutsos <br />
- Backend <br />
&nbsp;&nbsp;&nbsp;Gyuseok Park <br />

## Tech Stacks
Frontend

![Frontend](src/main/resources/static/img/Frontend.jpg)

Backend

![Backend](src/main/resources/static/img/Backend.jpg)

## Architecture

![Architecture](src/main/resources/static/img/Architecture.jpg)

## Database ERD

![Database](src/main/resources/static/img/Boogle_database_design.jpg)

## CI/CD Pipeline
Free tier EC2 instance is halted when cleaning and installing our web app. <br />
It seems like it's because of a lack of RAM in the Free tier EC2 instance, <br />
so Jenkins's current job is to pull an image from a docker repository and to run it.
![CI/CD](src/main/resources/static/img/boogle-cicd.jpg)
