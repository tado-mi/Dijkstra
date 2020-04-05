import React, { Component } from 'react';
import WebGL from './WebGL'
import './style/Visualiser.css';

class Visualiser extends Component {

  render() {
    return (
      <div className = "Temp">
        <span> temp span </span>
								<WebGL> </WebGL>
      </div>
    );
  }

}

export default Visualiser;
