import React, { useState, useEffect } from 'react';
import produce from 'immer';
import '../css/board.css';
import Cell from './Cell';

const Board = ({ numRows, numCols, grid, setGrid }) => {
  const updateGridSize = (numRows, numCols) => {
  };

  useEffect(() => {
    setGrid(updateGridSize(numRows, numCols));
  }, [numRows, numCols]);

  const toggleCell = (row, col) => {
    setGrid(prevGrid => {
      const newGrid = produce(prevGrid, gridCopy => {
        gridCopy[row][col] = gridCopy[row][col] ? 0 : 1;
      });
      return newGrid;
    });
  };

  return (
    <div className="game-container">
      {grid.map((row, rowIndex) =>
        row.map((_, colIndex) => (
          <Cell
            key={`${rowIndex}-${colIndex}`}
            row={rowIndex}
            col={colIndex}
            isAlive={grid[rowIndex][colIndex] === 1}
            toggleCell={toggleCell}
          />
        ))
      )}
    </div>
  );
};

export default Board;
