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
    document.body.style.backgroundColor = '#000000';
  }, []);

  return (
    <div className='gameoflife'>
      <h1>Conway's Game of Life</h1>
      <h2>Made by Cuma Bolat</h2>
      <div className='contents'>
        <div className='content'>
        <Controls gridSize={gridSize} setGridSize={setGridSize} grid={grid} setGrid={setGrid} />
        </div>

        <div className='content'>
        <Board gridSize={gridSize} grid={grid} setGrid={setGrid} />
        </div>
      </div>
    </div>
  );
}

export default GameOfLife;
