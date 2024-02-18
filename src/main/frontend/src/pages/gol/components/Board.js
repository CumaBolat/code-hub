import React, { useEffect, useState } from 'react';
import produce from 'immer';
import '../css/board.css';
import Cell from './Cell';

const Board = ({ numRows, numCols, grid, setGrid }) => {
  const [cellHeight, setCellHeight] = useState(20);
  const [cellWidth, setCellWidth] = useState(20);

  useEffect(() => {
    const containerWidth = 800;
    const containerHeight = 800;

    const columnSize = containerWidth / numCols;
    const rowSize = containerHeight / numRows;

    setCellHeight(columnSize);
    setCellWidth(rowSize);
  } , [numRows, numCols]);

  useEffect(() => {
    setGrid(prevGrid => {
      const newGrid = [];
      for (let i = 0; i < numRows; i++) {
        const row = [];
        for (let j = 0; j < numCols; j++) {
          row.push(prevGrid[i]?.[j] || 0);
        }
        newGrid.push(row);
      }
      return newGrid;
    });
  }, [numRows, numCols, setGrid]);

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
        '--grid-columns': numCols,
        '--grid-rows': numRows,
        '--cell-height': `${cellHeight}px`,
        '--cell-width': `${cellWidth}px`,
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
            numRows={numRows}
            numCols={numCols}
          />
        ))
      )}
    </div>
  );
};

export default Board;
