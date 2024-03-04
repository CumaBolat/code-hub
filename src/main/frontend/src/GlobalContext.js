import React, { createContext, useContext, useState, useEffect } from "react";
import { v4 as uuidv4 } from 'uuid';
import Stomp from 'stompjs';

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [sessionId, setSessionId] = useState('');
  const [ws, setWs] = useState(null);
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const id = uuidv4();
    setSessionId(id);
    console.log('Session ID: ' + id);
    const client = Stomp.client('ws://localhost:5000/socket?httpSessionId=' + id);
    client.connect({ "simpSessionId": id }, function (frame) {
      console.log('Connected to WebSocket server');
      client.debug = () => {};
      setWs(client);
      setStompClient(client);
    });
  }, []);

  return (
    <GlobalContext.Provider value={{ sessionId, setSessionId, ws, setWs, stompClient }}>
      {children}
    </GlobalContext.Provider>
  );
}

export const useGlobalContext = () => useContext(GlobalContext);
