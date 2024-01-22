# OnlineIDE

OnlineIDE is a web-based Integrated Development Environment (IDE) that allows you to write and execute code online.

## Demo

![OnlineIDE DEMO](https://imgur.com/a/1LGZk4p)

## Why?

I initiated this project to enhance my front-end skills and gain hands-on experience with AWS. Along the way, I faced a lot of challenges such as user sessions, CORS, and WebSocket Configurations. I also had to find a way to execute terminal commands sent from the users safely. Last but not least, AWS charged me even for the free tier (they said T2.nano instances were free - it wasn't).


In a nutshell, this project was my playground for overcoming challenges, enhancing my front-end proficiency, and creating a user-friendly coding space in OnlineIDE.



## Getting Started

1. **Establish a WebSocket Connection:**
   - Connect to the WebSocket server to enable real-time communication.

2. **Create Your Personal Workspace:**
   - Set up your dedicated workspace to manage your projects efficiently.

3. **Start Coding:**
   - Dive into coding using the advanced features of the OnlineIDE.

4. **Submit Your Code:**
   - Submit your code effortlessly through the integrated system.

## Key Features
- **Advanced Syntax Highlighting:**
  - Enjoy a visually pleasing coding experience with syntax highlighting.

- **Semi Auto-Completion:**
  - Accelerate your coding process with semi-automatic code completion.

- **Efficient File Browsing:**
  - Navigate through your files seamlessly for an organized coding environment.

- **Easy File Creation:**
  - Create new files easily within the OnlineIDE.

- **Integrated Terminal:**
  - Access a terminal build with pure HTML, CSS and Javascript directly within the IDE.

- **Secure Execution of Commands in Docker Container:**
  - Ensure secure execution of commands within isolated Docker container.

## Technologies Used

**Backend:**
- Java
- Spring Boot

**Frontend:**
- JavaScript
- HTML
- CSS
- React

**Containerization:**
- Docker

**Deployment:**
- AWS

**Additional Technologies:**
- REST APIs
- WebSockets

### Run Locally

#### Prerequisites

Make sure you have the following tools installed on your machine:

- [Maven](https://maven.apache.org/install.html)
- [Node.js and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [Docker](https://docs.docker.com/get-docker/)

#### Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/CumaBolat/online-ide.git
   cd online-ide 
2. **Frontend Setup:**
    ```bash
    cd src/main/frontend
    npm install
3. **Backend Setup:**
    ```bash
    # Go back to OnlineIDE directory,
    # choose either of the following commands
    cd ../../..
    # or
    cd /online-ide
    mvn spring-boot:run
4. **Access the Application:**
    
    When you successfully run the spring application, go to http://localhost:5000  (A spring application usually runs on 8080. However, since AWS Elastic Beanstalk uses apache and apaches listens to port 5000/80. So I changed my application to run on port 5000. You can change it in your local envrionment by opening `application.properties` and deleting the line `server.port=5000`)
    
    
    Feel free to customize it further or add any additional details based on your specific setup.
## Future Plans

- Will add tests.
- Will add multiple languages options.
- Pay off some technichal debt.
- Improve the frontend.
- See what happens..

## Contributing

Contributions are welcome!

## License

This project is licensed under the [MIT License](LICENSE).

