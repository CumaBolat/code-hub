import React from 'react';

const Cell = ({ row, col, isAlive, toggleCell }) => {
  const handleClick = () => {
    toggleCell(row, col);
  };

  return (
    <div
      className={`cell ${isAlive ? 'alive' : ''}`}
      onClick={handleClick}
    ></div>
  );
};

export default Cell;
