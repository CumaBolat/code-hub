import React from "react";

const Controls = ({ handleGridSize }) => {

  const startGame = () => {
    console.log("Start Game");
  }

  const stopGame = () => {
    console.log("Stop Game");
  }

  const clearGame = () => {
    console.log("Clear Game");
  }

  const randomizeGame = () => {
    console.log("Randomize Game");
  }

  return (
    <div className="buttons">
      <input type="number" placeholder="Speed" />
      <input type="number" placeholder="Grid Size" onChange={handleGridSize} />
      <button onClick={startGame}>Start</button>
      <button onClick={stopGame}>Stop</button>
      <button onClick={clearGame}>Clear</button>
      <button onClick={randomizeGame}>Randomize</button>
    </div>
  );
}

export default Controls;
