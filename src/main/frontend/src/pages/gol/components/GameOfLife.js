import React, { useState } from 'react';
import Board from './Board';
import Controls from './Controls';
import '../css/gameoflife.css';

function initializeGrid(numRows, numCols) {
  const rows = [];
  for (let i = 0; i < numRows; i++) {
    rows.push(Array.from(Array(numCols), () => 0));
  }
  return rows;
}

function GameOfLife() {
  const [nummberOfRows, setNumberOfRows] = useState(20);
  const [nummberOfColumns, setNumberOfColumns] = useState(20);
  const [grid, setGrid] = useState(() => initializeGrid(nummberOfRows, nummberOfColumns));

  initializeGrid(nummberOfRows, nummberOfColumns);

  const handleNumberOfRows = (e) => {
    if (e.target.value < 10) return setNumberOfRows(10);

    setNumberOfRows(parseInt(e.target.value));
  }

  const handleNumberOfColumns = (e) => {
    if (e.target.value < 10) return setNumberOfColumns(10);

    setNumberOfColumns(parseInt(e.target.value));
  }

  return (
    <div className='gameoflife'>
      <h1>Conway's Game of Life</h1>
      <h2>Made by Cuma Bolat</h2>
      <Board numRows={nummberOfRows} numCols={nummberOfColumns} grid={grid} setGrid={setGrid} />
      <Controls
        handleNumberOfRows={handleNumberOfRows}
        handleNumberOfColumns={handleNumberOfColumns}
      />
    </div>
  );
}

export default GameOfLife;
