import React from 'react';
import { useNavigate } from 'react-router-dom';
import { FaGithub, FaLinkedin, FaEnvelope } from 'react-icons/fa'; // Import Font Awesome icons

import '../css/home.css';

function LoginRegisterButtons() {
  const navigate = useNavigate();

  const hanleGameOfLife = async () => {
    navigate('/game-of-life');
  };

  const handleEditor = () => {
    navigate('/code-editor');
  }

  function centerSquare() {
    const innerSquare = document.getElementById('inner-square');
    const outerSquare = document.getElementById('outer-square');
    const centerButton = document.getElementById('center-button');

    const outerWidth = outerSquare.offsetWidth;
    const outerHeight = outerSquare.offsetHeight;

    const centerTop = (outerHeight - innerSquare.offsetHeight) / 2;
    const centerLeft = (outerWidth - innerSquare.offsetWidth) / 2;

    if (innerSquare.style.top === `${centerTop}px` && innerSquare.style.left === `${centerLeft}px`) {

      const randomTop = Math.random() * (outerHeight - innerSquare.offsetHeight);
      const randomLeft = Math.random() * (outerWidth - innerSquare.offsetWidth);

      centerButton.innerHTML = 'Center div';
      innerSquare.style.top = `${randomTop}px`;
      innerSquare.style.left = `${randomLeft}px`;
    } else {
      centerButton.innerHTML = 'Shuffle';
      innerSquare.style.top = `${centerTop}px`;
      innerSquare.style.left = `${centerLeft}px`;
    }
  }

  return (
    <div className="center-container">
      <div id='outer-square' className="outer-square">
        <div id="inner-square" className="inner-square"></div>
      </div>
      <button id='center-button' className='home-page-center-button' onClick={centerSquare}>Center Div</button>

      <div className="content">
        <header>
          <h1 className='home-header-1'>Hey, I'm Cuma!</h1>
          <h2 className='home-header-2'>Software Engineer</h2>
        </header>

        <section className="about-me">
        <p>
          Hello👋 I'm Cuma Bolat, a software engineer with 2 years of experience specializing in
          Backend Development. I have a knack for Data Structures and Algorithms, turning coding
          challenges into triumphs. And yes, I also know how to center a div!
        </p>
        <p>
          Outside of my algorithmic adventures, I enjoy working on challenging projects
          that push my boundaries as a developer. Feel free to check out some of my work below.
        </p>
        <p>
          I'm always open to new opportunities and learning experiences. If you have an 
          interesting project or just want to discuss tech, don't hesitate to reach out!
          You can find my contact info in the footer.
        </p>
        </section>

        <section className='skills'>
          <div className='skill'>
            <h3 className='home-header-3'>Technologies</h3>
            <ul>
              <li className='skill-item'><span>Programming Languages:</span> Java, Ruby, PHP, JavaScript, HTML, CSS, C, SQL</li>
              <li className='skill-item'><span>Frameworks & Libraries:</span> Spring Boot, Ruby on Rails, React, RSpec, Swing</li>
              <li className='skill-item'><span>Databases:</span> PostgreSQL, MySQL, SQLite</li>
              <li className='skill-item'><span>Development Tools:</span> Git, GitHub, Jira, Docker, AWS</li>
              <li className='skill-item'><span>Methodologies:</span> Scrum, Agile Development, TDD, BDD, CD, CI, OOP</li>
              <li className='skill-item'><span>Soft Skills:</span> Adaptability, Motivation, Problem Solving, Work Ethic, Teamwork, Leadership, Communication</li>
            </ul>
          </div>
        </section>

        <div className="button-container">
          <button className='home-button disabled-button' onClick={hanleGameOfLife}>Game of Life (Coming Soon)</button>
          <button className='home-button' onClick={handleEditor}>Online Code Editor</button>
        </div>
      </div>

      <footer>
        <a href="https://github.com/CumaBolat" target="_blank" rel="noopener noreferrer">
          <FaGithub size={30} />
        </a>
        <a href="https://www.linkedin.com/in/cuma-bolat-991682200/" target="_blank" rel="noopener noreferrer">
          <FaLinkedin size={30} />
        </a>

        <a href="mailto:cumabolat@posta.mu.edu.tr" target="_blank" rel="noopener noreferrer">
          <FaEnvelope size={30} />
        </a>
      </footer>
    </div>
  );
}

export default LoginRegisterButtons;