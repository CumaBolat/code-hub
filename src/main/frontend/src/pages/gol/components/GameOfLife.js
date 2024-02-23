import React, { useState, useEffect } from 'react';
import Space from 'react-zoomable-ui';
import Board from './Board';
import Controls from './Controls';

import '../css/gameoflife.css';

function initializeGrid(gridSize) {
  const grid = [];
  for (let i = 0; i < gridSize; i++) {
    const row = [];
    for (let j = 0; j < gridSize; j++) {
      row.push(0);
    }
    grid.push(row);
  }
  return grid;
}

function GameOfLife() {
  const [gridSize, setGridSize] = useState(20);
  const [grid, setGrid] = useState([]);

  useEffect(() => {
    setGrid(initializeGrid(gridSize));
  }, []);

  const handleGridSize = (e) => {
    setGridSize(parseInt(e.target.value));
  }

  return (
    <div className='gameoflife'>
      <h1>Conway's Game of Life</h1>
      <h2>Made by Cuma Bolat</h2>
      <Board gridSize={gridSize} grid={grid} setGrid={setGrid} />
      <Controls gridSize={gridSize} grid={grid} handleGridSize={handleGridSize} setGrid={setGrid} />
    </div>
  );
}

export default GameOfLife;
