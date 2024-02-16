import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Combiner from './pages/ide/components/Combiner';
import GameOfLife from './pages/gol/components/GameOfLife';

function App() {
    
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/code-editor" element={<Combiner />} />
          <Route path="/game-of-life" element={<GameOfLife />} />
        </Routes>
      </Router>
    </div>
    );
}

export default App;