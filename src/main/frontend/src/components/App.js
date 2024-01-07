import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Home';
import Editor from './Editor';
import Combiner from './Combiner';

function App() {
    
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/code-editor" element={<Combiner />} />
        </Routes>
      </Router>
    </div>
    );
}

export default App;