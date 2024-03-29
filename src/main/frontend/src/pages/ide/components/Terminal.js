import React, { useEffect, useState } from "react";
import { useGlobalContext } from "../../../GlobalContext";

import "../css/terminal.css";


function Terminal({ code, getFilesList }) {
  const { sessionId, ws } = useGlobalContext();
  const [setInput] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [subscribe, setSubscribe] = useState(null);
  const [output, setOutput] = useState('Welcome to Online IDE!' + '\n' + '>');
  const [commandHistory, setCommandHistory] = useState([]);
  const [historyIndex, setHistoryIndex] = useState(0);

  const outputRef = React.useRef(null);

  React.useEffect(() => {
    if (outputRef.current) {
      outputRef.current.scrollTop = outputRef.current.scrollHeight;
    }
  }, [output]);

  const connect = () => {
    if (isConnected) {
      setOutput(prevOutput => prevOutput + '\n' + 'Already connected to WebSocket\n>');
      return;
    }

    const sub = ws.subscribe('/user/' + sessionId + '/editor/output', function (code) {
      setOutput(prevOutput => prevOutput + '\n' + code.body + '\n>');
    });

    setSubscribe(sub);
    setOutput(prevOutput => prevOutput + '\n' + 'Connected to WebSocket\n>');
    setIsConnected(true);
  };

  const disconnect = () => {
    if (isConnected) {
      subscribe.unsubscribe();
      setOutput(prevOutput => prevOutput + '\n' + 'Disconnected from WebSocket\n>');
      setIsConnected(false);
    } else {
      setOutput(prevOutput => prevOutput + '\n' + 'Already disconnected\n>');
    }
  };

  const createUserWorkspace = () => {
    fetch('http://localhost:5000/handleUserWorkspace', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId
      }
    })
      .then(response => {
        if (response.ok) {
          setTimeout(save, 500);
          setTimeout(getFilesList, 750);
        } else {
          setOutput(prevOutput => prevOutput + '\n' + 'Failed to create user workspace\n>');
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  const submit = () => {
    if (ws && ws.connected) {
      ws.send('/app/editor', { 'simpSessionId': sessionId }, JSON.stringify({ 'code': code }));
    } else {
      setOutput(prevOutput => prevOutput + '\n' + 'Connect to websocket first\n>');
    }
    setTimeout(getFilesList, 550);
  };

  const save = () => {
    if (ws && ws.connected) {
      ws.send("/app/save", { "simpSessionId": sessionId }, JSON.stringify({ 'code': code }));
    } else {
      console.error('Not saved');
    }
  };

  const run = (command) => {
    if (ws && ws.connected) {
      ws.send("/app/terminal", { "simpSessionId": sessionId }, JSON.stringify(command));
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
        <button id="button" onClick={createUserWorkspace}>Create User Workspace</button>
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
