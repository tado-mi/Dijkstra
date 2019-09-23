import React, { Component } from 'react';
import './App.css';

import ControlPanel from './content/ControlPanel';
import Visualiser from './content/Visualiser';

class App extends Component {

    render () {
      return (
        <div className="App">
          <header className="App-header">
              Dijkstra simulator
          </header>
          <ControlPanel/>
          <Visualiser/>
        </div>
      );
    }

}

export default App;
