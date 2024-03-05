import React, { useState } from "react";
import Popup from 'reactjs-popup';

import { useGlobalContext } from "../../../GlobalContext";
import FAQ from "./FAQ";
import ProjectDescription from "./ProjectDescription";
import "../css/controls.css";
import "../css/popup.css";

const Controls = ({ gridSize, setGridSize, grid, setGrid }) => {
  const { sessionId, ws, setWs, stompClient } = useGlobalContext();
  const [patternName, setPatternName] = useState("");
  const [patternDescription, setPatternDescription] = useState("");
  const [gameStarted, setGameStarted] = useState(false);
  const [selectedPattern, setSelectedPattern] = useState(null);

  const startGame = () => {
    console.log("Start Game");
    if (!ws) {
      console.error('WebSocket connection not established.');
      return;
    }

    const serializedGrid = JSON.stringify(grid);

    ws.subscribe('/user/' + sessionId + '/gameoflife/output', function (grid) {
      setGrid(JSON.parse(grid.body));
    });

    ws.send('/app/gameoflife/start', { "simpSessionId": sessionId }, serializedGrid);
    setGameStarted(true);
  };

  const stopGame = () => {
    ws.send('/app/gameoflife/stop', { "simpSessionId": sessionId }, '');
    setGameStarted(false);
  }

  const clearGrid = () => {
    const newGrid = Array.from({ length: gridSize }, () => Array.from({ length: gridSize }, () => 0));
    setGrid(newGrid);
    setPatternName("");
    setPatternDescription("");
    setSelectedPattern(null);
  }

  const randomizeGrid = () => {
    const newGrid = Array.from({ length: gridSize }, () => Array.from({ length: gridSize }, () => Math.random() > 0.75 ? 1 : 0));
    setGrid(newGrid);
    setPatternName("");
    setPatternDescription("");
    setSelectedPattern(null);
  }

  const handleSpeedChange = (e) => {
    ws.send('/app/gameoflife/speed', { "simpSessionId": sessionId }, e.target.value);
  }

  const handleGridSize = (e) => {
    setGridSize(parseInt(e.target.value));
  }

  const handlePreFill = (e) => {
    const pattern = e.target.value;
    setPatternName(pattern.replace(/_/g, ' '));
    setSelectedPattern(pattern);
    fetch(`http://localhost:5000/pattern?pattern=${pattern}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId,
      },
    }).then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
      .then(data => {
        setGridSize(data.length);
        setGrid(data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });

    fetch(`http://localhost:5000/pattern/description?pattern=${pattern}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'simpSessionId': sessionId,
      },
    }).then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.text();
    }).then(data => {
      setPatternDescription(data);
    }).catch((error) => {
      console.error('Error:', error);
    });
  }

  return (
    <div className="controls">
      <div className="input-container">
        <label htmlFor="speed">Speed:</label>
        <input id="speed" type="range" min="1" max="20" defaultValue={"1"} onChange={handleSpeedChange} />
      </div>
      <div className="input-container">
        <label htmlFor="gridSize">Scale:</label>
        <input id="gridSize" type="range" min="20" max="100" defaultValue={"20"} onChange={handleGridSize} disabled={gameStarted} />
      </div>
      <div className="options">
        <label htmlFor="preFill">Patterns:</label>
        <select onChange={handlePreFill} disabled={gameStarted}>
          <option hidden disabled selected={!selectedPattern} value> Select a Pattern </option>
          <option value="STABLE_REFLECTOR">STABLE_REFLECTOR</option>
          <option value="GUNSTAR">GUNSTAR</option>
          <option value="55P23">55P23</option>
          <option value="232P7H3V0">232P7H3V0</option>
        </select>
      </div>
      <div className="description">
        <h2 className="pattern-name">{patternName}</h2>
        <p className="pattern-description">{patternDescription}</p>
      </div>
      <div className="control">
        <button className="control-button" onClick={startGame} disabled={gameStarted}>Start</button>
        <button className="control-button" onClick={stopGame} disabled={!gameStarted}>Stop</button>
        <button className="control-button" onClick={clearGrid} disabled={gameStarted}>Clear</button>
        <button className="control-button" onClick={randomizeGrid} disabled={gameStarted}>Randomize</button>
        <Popup
          trigger={<button className="control-button">FAQ</button>}
          modal
          nested
        >
          {(close) => (
            <div className="modal">
              <FAQ onClose={close} />
            </div>
          )}
        </Popup>
      </div>
      {ProjectDescription()}
    </div>
  );
}

export default Controls;
