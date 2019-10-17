import GLC from '../GLCommander'
import ModelRenderer from '../Render/ModelRenderer';
import ModelType from '../Models/ModelType';

const render = () =>{
    GLC.clear(1.0,0.0,0.0,1.0);//Default colorfor the canvas to be in
    // window.requestAnimationFrame(render);//Makes it run in a loop

}
export default (id) => {
    const canvas = document.querySelector(`#${id}`);

    if(!canvas){
        return;
    }
    const gl = canvas.getContext("webgl");
    if (!gl) {
        return;
    }
    GLC.init(gl);
    const vertices = [//makes a triangle
        0.0, 0.5, 0.0,
        -0.5, -0.5, 0.0,
        0.5, -0.5, 0.0
    ];

    const indices = [0,1,2];

    const modelRender = new ModelRenderer();
    modelRender.registerNewModel(new ModelType(vertices, indices), 'triangle');//This process can be abstracted away
    modelRender.addInstance('instance1', 'triangle');
    GLC.clear(1.0, 1.0, 1.0, 1.0);
    modelRender.render();
    // window.requestAnimationFrame(render);
}