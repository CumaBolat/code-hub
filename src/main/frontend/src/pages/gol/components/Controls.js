import React from "react";
import { useGlobalContext } from "../../../GlobalContext";
import "../css/controls.css";

const Controls = ({ gridSize, setGridSize, grid, setGrid }) => {
  const { sessionId, ws, setWs, stompClient } = useGlobalContext();

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
    setGridSize(100);
    const pattern = e.target.value;
    ws.send('/app/gameoflife/prefill', { "simpSessionId": sessionId }, pattern);
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
          <option value="glider">Glider</option>
          <option value="pulsar">Pulsar</option>
          <option value="spaceship">Spaceship</option>
          <option value="blinker">Blinker</option>
          <option value="pentadecathlon">Pentadecathlon</option>
          <option value="beacon">Beacon</option>
          <option value="gosperglidergun">Gosper Glider Gun</option>
          <option value="achimsVariant">Achim's Variant</option>
        </select>
      </div>
    </div>
  );
}

export default Controls;
