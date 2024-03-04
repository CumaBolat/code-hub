import React from "react";
import "../css/projectdescription.css";

const ProjectDescription = () => {
  return (
    <div className="project-description">
      <h1>Conway's Game of Life (Cellular Automaton)</h1>
      <p>
        While taking Formal Languages and Abstract Machines last year at Mugla Sitki Kocman University,
        I was introduced to the concept of cellular automata. I was fascinated by the idea of a simple set
        of rules that could create complex patterns and behaviors.
      </p>
      <p>
        I decided to create a web application that would allow users to interact with a cellular automaton.
        I chose Conway's Game of Life because it is simple to understand and has interesting emergent behavior.
      </p>
      <h1>Features</h1>
      <ul>
        <li>Start, stop, clear, and randomize the grid.</li>
        <li>Change the speed of the simulation.</li>
        <li>Change the size of the grid.</li>
        <li>Load the grid with prefilled,famous patterns.</li>
        <li>View a list of patterns and their descriptions.</li>
        <li>View a description of Conway's Game of Life.</li>
      </ul>
      <h1>Technologies</h1>
      <ul>
        <li>Frontend: Javascript, React, HTML, CSS </li>
        <li>Backend: Java, Spring Boot</li>
        <li>Deployment: AWS</li>
        <li>Additional Technologies: Data Structures and (A lot of) Algorithms, Object Oriented Programming, REST API'S, WebSockets, STOMP Protocol</li>
      </ul>

    </div>
  )
}

export default ProjectDescription;