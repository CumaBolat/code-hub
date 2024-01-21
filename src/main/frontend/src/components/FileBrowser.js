import React, { useEffect, useState } from "react";

import '../css/filebrowser.css';

function FileBrowser({ setWs, ws, code, setCode, getFilesList, files, sessionId }) {
  const [isListVisible, setListVisible] = useState(true);
  const [isPopupVisible, setPopupVisible] = useState(false);
  const [newFileName, setNewFileName] = useState('');

  const toggleListVisibility = () => {
      setListVisible(!isListVisible);
  };

  const openFile = (file) => {
      fetch('http://localhost:5000/openFile', {
          method: 'POST',
          body: file,
          headers: {
              'Content-Type': 'text/plain',
              'simpSessionId': sessionId,
          },
      })
          .then(response => response.text())
          .then(data => setCode(data))
          .catch((error) => {
              console.error('Error:', error);
          });
  };

  const createFile = (fileName) => {
      fetch('http://localhost:5000/createFile', {
          method: 'POST',
          body: JSON.stringify(fileName),
          headers: {
              'Content-Type': 'application/json',
              'simpSessionId': sessionId,
          },
      })
          .then(response => response.text())
          .then(data => setCode(data))
          .catch((error) => {
              console.error('Error:', error);
          });
  };

  const handleCreateFileClick = () => {
      if (isPopupVisible) {
          setPopupVisible(false);
          return;
      }
      setPopupVisible(true);
  };

  const handleFileNameChange = (event) => {
      setNewFileName(event.target.value);
  };

  const handleCreateFile = () => {
      createFile(newFileName);
      setTimeout(getFilesList, 100);
      setPopupVisible(false);
  };

  return (
    <div className="browser">
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <button onClick={() => window.history.back()} style={{ marginRight: '10px' }}>Go Back</button>
        <h1 id="browser-header">FileBrowser</h1>
      </div>

      <div>
        <button className="list-button" onClick={toggleListVisibility}>
          {isListVisible ? 'Hide Files' : 'Show Files'}
        </button>

        <button className="create-file-button" onClick={handleCreateFileClick} >Create File</button>

        {isPopupVisible && (
          <div>
            <input type="text" value={newFileName} onChange={handleFileNameChange} placeholder="Enter file name" />
            <button className="file-button" onClick={handleCreateFile}>OK</button>
          </div>
        )}

        {isListVisible && (
          <ul>
            {Array.isArray(files) && files.map((file, index) => (
              <li className="buttons" key={index} >
                <button className="file-button" onClick={() => openFile(file)}>{file}</button>
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="docs">
        <h2 id="editor-header">Getting Started with OnlineIDE</h2>
        <ol id="editor-text">
          <li>Establish a WebSocket Connection</li>
          <li>Create Your Personal Workspace</li>
          <li>Start Coding</li>
          <li>Submit Your Code</li>
        </ol>
        <h2 id="editor-header">Key Features</h2>
        <ul id="editor-text">
          <li>Advanced Syntax Highlighting</li>
          <li>Semi Auto-Completion</li>
          <li>Efficient File Browsing</li>
          <li>Easy File Creation</li>
          <li>Integrated Terminal</li>
          <li>Secure Execution of Commands in Docker Container</li>
        </ul>
        <h2 id="editor-header">Technologies Used</h2>
        <ul id="editor-text">
          <li>Backend: Java, Spring Boot</li>
          <li>Frontend: JavaScript, HTML, CSS, React</li>
          <li>Containerization: Docker</li>
          <li>Deployment: AWS</li>
          <li>Additional Technologies: REST APIs, WebSockets</li>
        </ul>
      </div>
    </div>
  );
}

export default FileBrowser;