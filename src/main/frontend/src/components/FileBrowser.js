import React, { useEffect, useState } from "react";

import '../css/filebrowser.css';

function FileBrowser( { setWs, ws, code, setCode, getFilesList, files}) {
  const [isListVisible, setListVisible] = useState(true);
  const [isPopupVisible, setPopupVisible] = useState(false);
  const [newFileName, setNewFileName] = useState('');

  const toggleListVisibility = () => {
    setListVisible(!isListVisible);
  };
  
  const openFile = (file) => {
    fetch('http://localhost:8080/openFile', { method: 'POST', body: file })
      .then(response => response.text())
      .then(data => setCode(data))
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  const createFile = (fileName) => {
    fetch('http://localhost:8080/createFile', { method: 'POST', body: JSON.stringify(fileName)})
      .then(response => response.text())
      .then(data => setCode(data))
      .catch((error) => {
        console.error('Error:', error);
      });
  }

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
  
  return(
    <div className="browser">
      <h1 id="browser-header" >FileBrowser</h1>

      <div>
        <button className="list-button" onClick={toggleListVisibility}>
          {isListVisible ? 'Hide Files' : '> java-ide'}
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
    </div>
  );
}

export default FileBrowser;