import React, { useEffect, useState } from 'react';
import AceEditor from 'react-ace';
import '../css/editor.css'
import 'ace-builds/src-noconflict/mode-java';
import 'ace-builds/src-noconflict/theme-github';

const Editor = ({ code, setCode }) => {
  
  return (
    <>
      <div>
        <AceEditor 
          mode="java"
          theme="github"
          value={code}
          onChange={setCode}
          name="deneme"
          className='code-editor'
          editorProps={{ $blockScrolling: true }}
          setOptions={{
            enableBasicAutocompletion: true,
            enableLiveAutocompletion: true,
            enableSnippets: true,
            showLineNumbers: true,
            tabSize: 2,
            fontSize: 20,
            minLines: 50,
          }}
        />
      </div>
    </>
  );
}

export default Editor;