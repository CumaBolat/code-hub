import React, { useEffect, useState } from 'react';
import produce from 'immer';
import '../css/board.css';
import Cell from './Cell';

const Board = ({ gridSize, grid, setGrid }) => {

  const maxSize = 800;
  const [cellSize, setCellSize] = useState(20);

  useEffect(() => {
    console.log("gridsize changed");
    setCellSize(maxSize / gridSize);
  }, [gridSize]);


  useEffect(() => {
    setGrid(prevGrid => {
      const newGrid = [];
      for (let i = 0; i < gridSize; i++) {
        const row = [];
        for (let j = 0; j < gridSize; j++) {
          row.push(prevGrid[i]?.[j] || 0);
        }
        newGrid.push(row);
      }
      return newGrid;
    });
  }, [gridSize, setGrid]);

  const toggleCell = (row, col) => {
    setGrid(prevGrid => {
      const newGrid = produce(prevGrid, gridCopy => {
        gridCopy[row][col] = gridCopy[row][col] ? 0 : 1;
      });
      return newGrid;
    });
  };

  return (
    <div
      className="game-container"
      style={{
        gridTemplateColumns: `repeat(${gridSize}, ${cellSize}px)`,
        gridTemplateRows: `repeat(${gridSize}, ${cellSize}px)`,
        width: `${maxSize}px`,
        height: `${maxSize}px`,
      }}
    >
      {grid?.map((row, rowIndex) =>
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
