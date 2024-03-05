import React, { useEffect } from 'react';
import { redirect, useNavigate } from 'react-router-dom';
import { FaGithub, FaLinkedin, FaEnvelope, FaHackerrank, FaPhone } from 'react-icons/fa'; // Import Font Awesome icons

import './home.css';

function LoginRegisterButtons() {
  const navigate = useNavigate();

  useEffect(() => {
    document.body.style = 'background: white;';
  });

  const hanleGameOfLife = async () => {
    navigate('/game-of-life');
  };

  const handleEditor = () => {
    navigate('/code-editor');
  };

  return (
    <div className="home-container">
      <div className='home-contents'>
        <div className='home-content home-content-1'>
          <h1 className='home-header-1'>Hey, I'm Cuma.</h1>
          <h2 className='home-header-2'>Software Engineer</h2>
          <section className="about-me">
            <p>
              Welcome! I'm Cuma Bolat, a skilled software engineer with 2 years of experience specializing in Backend Development. I excel in Data Structures and Algorithms, transforming coding challenges into successes. And yes, I'm proficient in centering a div.
            </p>
            <p>
              Beyond my algorithmic ventures, I thrive on tackling challenging projects that expand my developer horizons. Explore some of my work below and feel free to reach out for collaboration or tech discussions. You'll find my contact info in the footer.
            </p>
          </section>
          <section className='skills'>
            <h3 className='home-header-2'>Technologies</h3>
            <ul className='skills-list'>
              <li className='skill-item'><span>Programming Languages:</span> Java, Ruby, PHP, JavaScript, HTML, CSS, C, SQL</li>
              <li className='skill-item'><span>Frameworks & Libraries:</span> Spring Boot, Ruby on Rails, React, RSpec, Swing</li>
              <li className='skill-item'><span>Databases:</span> PostgreSQL, MySQL, SQLite</li>
              <li className='skill-item'><span>Development Tools:</span> Git, GitHub, Jira, Docker, AWS</li>
              <li className='skill-item'><span>Methodologies:</span> Scrum, Agile Development, TDD, BDD, CD, CI, OOP</li>
              <li className='skill-item'><span>Soft Skills:</span> Adaptability, Motivation, Problem Solving, Work Ethic, Teamwork, Leadership, Communication</li>
            </ul>
          </section>
        </div>
        <div className='home-content home-content-2'>
          <div className='experience'>
            <h1 className='home-header-2'>Experience</h1>
            <ul className='experience-list'>
              <li className='experience-item'> <span>Technical Engineer at Alemdar Enerji (August 2023 - Present)</span></li>
              <li className='experience-item'> <span>Backend Support Engineer at Jotform (July 2022 - January 2023)</span></li>
              <li className='experience-item'> <span>DevOps Developer Intern at Jotform (June 2022 - July 2022)</span></li>
              <li className='experience-item'> <span>Backend Engineer Intern at Toptal Core Team (Aug 2021 - Feb 2022)</span></li>
            </ul>
          </div>
          <div className='academics'>
            <h1 className='home-header-2'>Academic Accomplishments</h1>
            <ul className='academic-accomplishment'>
              <li className='academic-accomplishment-item'> <span>Mentor in Algorithms Club at Mugla Sitki Kocman University (October 2020 - Aug 2021)</span></li>
              <li className='academic-accomplishment-item'> <span>First Place in Competitive Programming Contest at Mugla Sitki Kocman University (October 2020)</span></li>
            </ul>
          </div>
          <div className="projects">
            <h1 className='home-header-2'>Projects</h1>
            <button className='project-button' onClick={hanleGameOfLife}>Game of Life</button>
            <button className='project-button' onClick={handleEditor}>Online Code Editor</button>
          </div>
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
        <a href="https://www.hackerrank.com/profile/CENG190709062" target="_blank" rel="noopener noreferrer">
          <FaHackerrank size={30} />
        </a>
      </footer>
    </div>

  );
}

export default LoginRegisterButtons;