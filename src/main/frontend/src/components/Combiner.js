import React, { useState, useEffect } from "react";
import { v4 as uuidv4 } from 'uuid';

import Editor from "./Editor";
import FileBrowser from "./FileBrowser";
import "../css/combiner.css";
import Terminal from "./Terminal";

const startUpCode =
    `public class OnlineIDE {

  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}`;

function Combiner() {
  const [code, setCode] = useState(startUpCode);
  const [ws, setWs] = useState(' ');
  const [files, setFiles] = useState([]);
  const [sessionId, setSessionId] = useState('');

  useEffect(() => {
    const id = uuidv4();
    setSessionId(id);
    console.log("id is:::"+id);
    console.log("session id is:::"+sessionId);
  }, []);

  const getFilesList = () => {
    fetch('http://localhost:5000/getFilesList',{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId
      }
    })
    .then(response => response.json())
    .then(data => setFiles(data))
    .catch((error) => {
      console.error('Error:', error);
    });
  };


  return(
    <div className="container">
      <div className="file-browser">
        <FileBrowser ws={ws} setWs={setWs} code={code} setCode={setCode} getFilesList={getFilesList} files={files} sessionId={sessionId}/>
      </div>
      <div className="main-area">
        <div className="editor">
          <Editor ws={ws} code={code} setCode={setCode} />
        </div>
        <div className="terminal">
          <Terminal setWs={setWs} ws={ws} code={code} sessionId={sessionId} getFilesList={getFilesList}/>
        </div>
      </div>
    </div>
  );
}

export default Combiner;
