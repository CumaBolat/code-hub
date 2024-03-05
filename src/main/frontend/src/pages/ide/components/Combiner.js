import React, { useState, useEffect } from "react";

import Editor from "./Editor";
import FileBrowser from "./FileBrowser";
import "../css/combiner.css";
import Terminal from "./Terminal";
import { useGlobalContext } from "../../../GlobalContext";

const startUpCode =
    `public class OnlineIDE {

  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}`;

function Combiner() {
  const { sessionId, ws } = useGlobalContext();
  const [code, setCode] = useState(startUpCode);
  const [files, setFiles] = useState([]);

  useEffect (() => {
    document.querySelector("link[rel='icon']").href = 'favicon-ide.ico';
    document.title='OnlineIDE';
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
        <FileBrowser code={code}
                     setCode={setCode}
                     getFilesList={getFilesList}
                     files={files}/>
      </div>
      <div className="main-area">
        <div className="editor">
          <Editor code={code} setCode={setCode} />
        </div>
        <div className="terminal">
          <Terminal code={code}
                    getFilesList={getFilesList}/>
        </div>
      </div>
    </div>
  );
}

export default Combiner;
