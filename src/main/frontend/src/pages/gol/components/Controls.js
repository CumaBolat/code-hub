import React, { useEffect } from "react";
import { useGlobalContext } from "../../../GlobalContext";

const Controls = ({ gridSize, grid, handleGridSize, setGrid }) => {
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

  return (
    <div className="buttons">
      <input type="range" min="1" max="10" onChange={handleSpeedChange} />
      <input type="number" placeholder="Grid Size" onChange={handleGridSize} />
      <button onClick={startGame}>Start</button>
      <button onClick={stopGame}>Stop</button>
      <button onClick={clearGrid}>Clear</button>
      <button onClick={randomizeGrid}>Randomize</button>
    </div>
  );
}

export default Controls;
