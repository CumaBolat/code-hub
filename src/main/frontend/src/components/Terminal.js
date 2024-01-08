import React, { useEffect, useState } from "react";
import Stomp from 'stompjs';

import "../css/terminal.css";
import { useNavigate } from 'react-router-dom';


function Terminal({ setWs, ws, code, getFilesList }) {
  const [input, setInput] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [output, setOutput] = useState('Welcome to Online IDE!' + '\n' + '>');
  const [isContainerCreated, setIsContainerCreated] = useState(false);
  const [commandHistory, setCommandHistory] = useState([]);
  const [historyIndex, setHistoryIndex] = useState(0);
  const navigate = useNavigate();

  const outputRef = React.useRef(null);

  React.useEffect(() => {
    if (outputRef.current) {
      outputRef.current.scrollTop = outputRef.current.scrollHeight;
    }
  }, [output]);

  const connect = () => {
    if (ws && ws.connected) {
        console.log('Already connected');
      setOutput(prevOutput => prevOutput + '\nAlready connected\n>');
      return;
    }

    const stompClient = Stomp.client('ws://localhost:5000/socket');

    stompClient.connect({}, function (frame) {
      stompClient.subscribe('/editor/output', function (code) {
        console.log('Received code ' + code.body);
        setOutput(prevOutput => prevOutput + '\n' + code.body + '\n>');
      });

      setWs(stompClient);
      setOutput(prevOutput => prevOutput + '\n' +'Connected to WebSocket\n>');
    }, function (error) {
      console.error('STOMP error ' + error);
    });
  };

  const disconnect = () => {
    if (ws && ws.connected) {
      ws.disconnect();
      setWs(null);
      setOutput(prevOutput => prevOutput + '\n' + 'Disconnected from WebSocket\n>');
    } else {
      console.log('Already disconnected');
    }
  };

  const createContainer = () => {
    if (ws && ws.connected) {
      fetch('http://localhost:5000/handleDockerContainer', { method: 'POST' })
        .catch((error) => {
          console.error('Error:', error);
        });
      setIsContainerCreated(true);
      setTimeout(save, 500);
      setTimeout(getFilesList, 750);
    } else {
      console.error('WebSocket is not connected');
      setOutput(prevOutput => prevOutput + '\n' + 'Connect to websocket first\n>');
    }
  };

  const submit = () => {
    if (ws && ws.connected) {
      ws.send("/app/editor", {}, JSON.stringify({ 'code': code }));
      console.log('Code submitted successfully');
    } else {
      console.error('WebSocket is not connected');
    }
    setTimeout(getFilesList, 450);
  };

  const save = () => {
    if (ws && ws.connected) {
      ws.send("/app/save", {}, JSON.stringify({ 'code': code }));
    } else {
      console.error('Not saved');
    }
  };

  const run = (command) => {
    if (ws && ws.connected) {
      ws.send("/app/terminal", {}, JSON.stringify(command));
      console.log('Input submitted successfully');
      setInput('');
    } else {
      console.error('WebSocket is not connected');
    }
  };

  const clear = async () => {
    setOutput('Welcome to the Online IDE\n>');
  };

  const handleOutputChange = (event) => {
    setOutput(event.target.value);
  };

  const handleKeyDown = (event) => {
    const { value, selectionStart, selectionEnd } = event.target;
    const lines = value.split('\n');
    const currentLine = lines.length - 1;
    const currentLineStart = value.lastIndexOf('\n') + 1;
  
    if (event.key === 'Enter') {
      event.preventDefault();
      
      const command = lines.at(-1).replace('>', '');
      let trimmedCommand = command.trim();

      if (trimmedCommand.length != 0) {
        setCommandHistory(prevHistory => [trimmedCommand, ...prevHistory]);
        setHistoryIndex(0);
      
        if (trimmedCommand === 'clear') {
          clear();
          return;
        } else if (trimmedCommand.includes('javac')) {
          setTimeout(save, 200);
          setTimeout(run(command), 300);
          setTimeout(getFilesList, 600);
          return;
        }
        run(command);
        return;
      }
      setOutput(prevOutput => prevOutput + "\n>")
    } else if (event.key === 'Backspace' && (selectionStart <= currentLineStart + 1 || selectionEnd <= currentLineStart + 1)) {
      event.preventDefault();
    } else if (selectionStart < currentLineStart + 1 || selectionEnd < currentLineStart + 1) {
      event.preventDefault();
    } else if (event.key === 'ArrowUp') {
      event.preventDefault();
      if (historyIndex < commandHistory.length) {
        const newHistoryIndex = historyIndex + 1;
        lines[currentLine] = '>' + commandHistory[newHistoryIndex - 1];
        setOutput(lines.join('\n'));
        setHistoryIndex(newHistoryIndex);
      }
    } else if (event.key === 'ArrowDown') {
      event.preventDefault();
      if (historyIndex > 1) {
        const newHistoryIndex = historyIndex - 1;
        lines[currentLine] = '>' + commandHistory[newHistoryIndex - 1];
        setOutput(lines.join('\n'));
        setHistoryIndex(newHistoryIndex);
      } else {
        lines[currentLine] = '>';
        setOutput(lines.join('\n'));
        setHistoryIndex(0);
      }
    }
  };

  const handleClick = (event) => {
    const { value } = event.target;
    event.target.setSelectionRange(value.length, value.length);
  };

  return (
    <div className="terminal">
      <div className='buttons'>
        <button id="button" onClick={connect}>Connect to Websocket</button>
        <button id="button" onClick={disconnect}>Disconnect from Websocket</button>
        <button id="button" onClick={createContainer}>Create Docker Container</button>
        <button id="button" onClick={submit}>Submit</button>
        <button id="button" onClick={clear}>Clear</button>
      </div>
      <div className="output-div">
        <textarea 
          ref={outputRef}
          value={output}
          onChange={handleOutputChange}
          onKeyDown={handleKeyDown}
          onClick={handleClick}
          id='output'
        />
      </div>
     
    </div>
  );
}

export default Terminal;

// guest@notroot: nano deneme.java 
// guest@notroot: (the user can write commands here but clicks submit)
// Hello World!
// guest@notroot: (the user can write commands here)
// guest@notroot: (the user can write commands here)
