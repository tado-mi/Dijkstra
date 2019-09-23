import React, { Component } from 'react';
import './ControlPanel.css';

import { Upload, Button } from 'antd';

class ControlPanel extends Component {

  foo(e) {

    const file = e.file;

    if (file.state === 'uploading') {
      return;
    }

    var reader = new FileReader();
    reader.readAsText(new Blob([file]));

    reader.onload = function(e) {
      console.log(e.target.result);
    };

  }

  render() {
    const { foo } = this;
    return (
      <div>
        <Upload onChange={foo}>
          <Button icon="upload" size="large">
            <strong> upload </strong>
          </Button>
        </Upload>
      </div>
    );
  }

}

export default ControlPanel;
