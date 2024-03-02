import React from "react";
import { useGlobalContext } from "../../../GlobalContext";
import "../css/controls.css";

const Controls = ({ gridSize, setGridSize, grid, setGrid }) => {
  const { sessionId, ws, setWs, stompClient } = useGlobalContext();
  const [patternName, setPatternName] = React.useState("");
  const [patternDescription, setPatternDescription] = React.useState("");

  const startGame = () => {
    console.log("Start Game");
    if (!ws) {
      console.error('WebSocket connection not established.');
      return;
    }

    const serializedGrid = JSON.stringify(grid);

    ws.subscribe('/user/' + sessionId + '/gameoflife/output', function (grid) {
      setGrid(JSON.parse(grid.body));
    });

    ws.send('/app/gameoflife/start', { "simpSessionId": sessionId }, serializedGrid);
  };

  const stopGame = () => {
    ws.send('/app/gameoflife/stop', { "simpSessionId": sessionId }, '');
  }

  const clearGrid = () => {
    const newGrid = [];
    for (let i = 0; i < gridSize; i++) {
      const row = [];
      for (let j = 0; j < gridSize; j++) {
        row.push(0);
      }
      newGrid.push(row);
    }
    setGrid(newGrid);
  }

  const randomizeGrid = () => {
    const newGrid = [];
    for (let i = 0; i < gridSize; i++) {
      const row = [];
      for (let j = 0; j < gridSize; j++) {
        row.push(Math.random() > 0.7 ? 1 : 0);
      }
      newGrid.push(row);
    }
    setGrid(newGrid);
  }

  const handleSpeedChange = (e) => {
    ws.send('/app/gameoflife/speed', { "simpSessionId": sessionId }, e.target.value);
  }

  const handleGridSize = (e) => {
    setGridSize(parseInt(e.target.value));
  }

  const handlePreFill = (e) => {
    const pattern = e.target.value;
    setPatternName(pattern.replace(/_/g, ' '));
    fetch(`http://localhost:5000/pattern?pattern=${pattern}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId,
      },
    }).then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      console.log("Data received from backend:", data);
      setGridSize(data.length);
      setGrid(data);
    })
    .catch((error) => {
      console.error('Error:', error);
    });

    fetch(`http://localhost:5000/pattern/description?pattern=${pattern}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId,
      },
    }).then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.text();
    }).then(data => {
      console.log("Data received from backend:", data);
      setPatternDescription(data);
    }).catch((error) => {
      console.error('Error:', error);
    });
  }
  
  
  

  return (
    <div className="controls">
      <div className="control">
        <input id="speed" type="range" min="1" max="10" defaultValue={"1"} onChange={handleSpeedChange} />
        <label htmlFor="speed">Speed</label>
      </div>
      <div className="control">
        <input id="gridSize" type="range" min="20" max="100" defaultValue={"20"} onChange={handleGridSize} />
        <label htmlFor="gridSize">Grid Size</label>
      </div>
      <div className="control">
        <button onClick={startGame}>Start</button>
      </div>
      <div className="control">
        <button onClick={stopGame}>Stop</button>
      </div>
      <div className="control">
        <button onClick={clearGrid}>Clear</button>
      </div>
      <div className="control">
        <button onClick={randomizeGrid}>Randomize</button>
      </div>
      <div className="control"><option hidden disabled selected value> -- select an option -- </option>
        <select onChange={handlePreFill}>
          <option hidden disabled selected value> -- select an option -- </option>
          <option value="STABLE_REFLECTOR">STABLE_REFLECTOR</option>
          <option value="GUNSTAR">GUNSTAR</option>
          <option value="55P23">55P23</option>
          <option value="232P7H3V0">232P7H3V0</option>
          <option value="pentadecathlon">Pentadecathlon</option>
          <option value="beacon">Beacon</option>
          <option value="gosperglidergun">Gosper Glider Gun</option>
          <option value="achimsVariant">Achim's Variant</option>
        </select>
      </div>
      <div className="description">
        <h2 className="pattern-name">{patternName}</h2>
        <p className="pattern-description">{patternDescription}</p>
      </div>
    </div>
  );
}

export default Controls;
