import React from "react";

const Controls = ({ gridSize, handleGridSize, setGrid }) => {

  const startGame = () => {
    console.log("Start Game");
  }

  const stopGame = () => {
    console.log("Stop Game");
  }

  const clearGame = () => {
    console.log("Clear Game");
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

  return (
    <div className="buttons">
      <input type="number" placeholder="Speed" />
      <input type="number" placeholder="Grid Size" onChange={handleGridSize} />
      <button onClick={startGame}>Start</button>
      <button onClick={stopGame}>Stop</button>
      <button onClick={clearGame}>Clear</button>
      <button onClick={randomizeGrid}>Randomize</button>
    </div>
  );
}

export default Controls;
