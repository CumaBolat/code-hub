# Code Hub

[![GitHub license](https://img.shields.io/github/license/CumaBolat/code-hub)](License)


Welcome to Code Hub! 🚀 This repository serves as my digital playground as I embark on a journey of exploration and innovation while seeking new opportunities. Here, you'll find a collection of my past projects alongside exciting new endeavors. Additionally, I'll be sharing my insights, notes, and recommendations from the vast world of software development.

Feel free to browse through the code, explore the projects, and dive into the knowledge I've gathered along the way. Whether you're a fellow developer, a prospective employer, or simply curious about the world of coding, there's something here for everyone.


## Table of Contents
* [Screenshots](#screenshots)
* [Why?](#why)
* [Built With](#built-with)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Game Of Life](#game-of-life)
  * [Getting Started](#getting-started-with-game-of-life)
  * [Key Features](#key-features-of-game-of-life)
  * [Future Plans](#future-plans-for-game-of-life)
* [OnlineIDE](#onlineide)
  * [Getting Started](#getting-started-with-onlineide)
  * [Key Features](#key-features-of-onlineide)
  * [Future Plans](#future-plans-for-onlineide)
* [Contributing](#contributions)
* [License](#license)

## Screenshots

#### Home

![Home](/src/main/frontend/src/pages/images/home.png)

#### Game Of Life
![Game Of Life](/src/main/frontend/src/pages/images/gol.png)

#### OnlineIDE

![OnlineIDE](/src/main/frontend/src/pages/images/ide.png)


## Why?

I initiated this project to enhance my front-end skills and gain hands-on experience with AWS. Along the way, I faced a lot of challenges such as user sessions, CORS, and WebSocket Configurations. I also had to find a way to execute terminal commands sent from the users safely. Last but not least, AWS charged me even for the free tier (they said T2.nano instances were free - it wasn't).

In a nutshell, this project was my playground for overcoming challenges, enhancing my front-end proficiency, and creating a In a nutshell, this project was my playground for overcoming challenges, enhancing my front-end proficiency, and creating a user-friendly coding space in OnlineIDE. User-friendly coding space in OnlineIDE.

## Built With

Every project in this repo is built with the same technologies ("Inte­lect is a magnitude of intensity, not a magnitude of extensity." -Schopenhauer). 

**Backend:**
- Java (17)
- Spring Boot (3.1.1)
- Maven (3.6.3)

**Frontend:**
- JavaScript
- HTML
- CSS
- React (8.11.0)

**Containerization:**
- Docker (24.0.7)

**Deployment:**
- AWS

**Additional Technologies:**
- REST APIs
- WebSockets
- Object Oriented Programming
- Data Structures and Algorithms
- Git (2.34.1)

## Prerequisites

Make sure you have the following tools installed on your machine:

- [Maven](https://maven.apache.org/install.html)
- [Node.js and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [Docker](https://docs.docker.com/get-docker/)

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/CumaBolat/code-hub.git
   cd code-hub 
2. **Frontend Setup:**
    ```bash
    cd src/main/frontend
    npm install
3. **Backend Setup:**
    ```bash
    # Go back to CodeHUB directory,
    # choose either of the following commands
    cd ../../..
    # or
    cd /code-hub
    mvn spring-boot:run
4. **Access the Application:**
    
    When you successfully run the spring application, go to http://localhost:5000  (A spring application usually runs on 8080. However, since AWS Elastic Beanstalk uses apache and apaches listens to port 5000/80. So I changed my application to run on port 5000. You can change it in your local envrionment by opening `application.properties` and deleting the line `server.port=5000`)
    
    
    Feel free to customize it further or add any additional details based on your specific setup.


## Game Of Life

Game of Life is a cellular automaton devised by the British mathematician John Horton Conway in 1970. I created this project to explore the game's rules and principles while enhancing my front-end skills. The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves.

#### Getting Started With Game Of Life
1. **Open Game of Life Page:**
    - Go to the Game of Life page to start the game through the Home page.
2. **Fill The Grid However You Like:**
    - Fill the grid with cells by clicking on the cells, use randomize button or select any of the patterns from the dropdown menu.
3. **Start The Game:**
    - Start the game by clicking the start button.
4. **Enjoy The Game:**
    - Watch the cells evolve and enjoy the game.

#### Features of Game Of Life
- **Grid Controls:**
  - Start, stop, clear, and randomize the grid to control the simulation.

- **Simulation Speed:**
  - Adjust the speed of the simulation to observe the evolution at different rates.

- **Grid Size Adjustment:**
  - Change the size of the grid to accommodate different configurations and resolutions.

- **Pattern Loading:**
  - Load the grid with prefilled, famous patterns to explore various configurations.

- **Pattern Library:**
  - View a list of patterns along with their descriptions to understand their behavior and significance.

- **Game Description:**
  - Access a comprehensive description of Conway's Game of Life to learn about its rules and principles.


#### Future Plans for Game Of Life

- Will add tests.
- Will add more patterns.
- Will add more features to the game.
- Will optimize the code.
- See what happens..



## OnlineIDE

OnlineIDE is a web-based Integrated Development Environment (IDE) that allows you to write and execute code online.


#### Getting Started with OnlineIDE
1. **Establish a WebSocket Connection:**
   - Connect to the WebSocket server to enable real-time communication.

2. **Create Your Personal Workspace:**
   - Set up your dedicated workspace to manage your projects efficiently.

3. **Start Coding:**
   - Dive into coding using the advanced features of the OnlineIDE.

4. **Submit Your Code:**
   - Submit your code effortlessly through the integrated system.

#### Key Features of OnlineIDE
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


#### Future Plans for OnlineIDE
- Will add tests.
- Will add multiple languages options.
- Pay off some technichal debt.
- Improve the frontend.
- See what happens..

## Contributing

Contributions are welcome!

## License

This project is licensed under the [MIT License](LICENSE).
