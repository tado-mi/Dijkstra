import React, { Component } from 'react'
import init from '../Init'
//https://www.youtube.com/watch?v=wbI55mc7ksE
export default class WebGL extends Component {
    componentDidMount(){
        init('webgl');
    }

    render() {
        return (
            <canvas id="webgl" width="400" height="400" style={{border: '1px solid black'}} >
                
            </canvas>
        )
    }
}
