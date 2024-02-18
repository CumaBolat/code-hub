import React, { useEffect, useState } from 'react';
import Board from './Board';
import Controls from './Controls';
import '../css/gameoflife.css';

function initializeGrid(numRows, numCols) {
  const grid = [];
  for (let i = 0; i < numRows; i++) {
    const row = [];
    for (let j = 0; j < numCols; j++) {
      row.push(0);
    }
    grid.push(row);
  }
  return grid;
}

function GameOfLife() {
  const [numberOfRows, setNumberOfRows] = useState(20);
  const [numberOfColumns, setNumberOfColumns] = useState(20);
  const [grid, setGrid] = useState([]);

  useEffect(() => {
    setGrid(initializeGrid(numberOfRows, numberOfColumns));
  } , []);

  const handleNumberOfRows = (e) => {
    if (e.target.value < 20 || e.target.value > 200) return setNumberOfRows(20);


    setNumberOfRows(parseInt(e.target.value));
  }

  const handleNumberOfColumns = (e) => {
    if (e.target.value < 20 || e.target.value > 200) return setNumberOfColumns(20);

    setNumberOfColumns(parseInt(e.target.value));
  }

  return (
    <div className='gameoflife'>
      <h1>Conway's Game of Life</h1>
      <h2>Made by Cuma Bolat</h2>
      <Board numRows={numberOfRows} numCols={numberOfColumns} grid={grid} setGrid={setGrid} />
      <Controls
        handleNumberOfRows={handleNumberOfRows}
        handleNumberOfColumns={handleNumberOfColumns}
      />
    </div>
  );
}

export default GameOfLife;
