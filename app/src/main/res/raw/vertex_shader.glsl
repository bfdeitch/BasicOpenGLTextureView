attribute vec4 position;
attribute vec4 color;
varying vec4 v_Color;

void main () {
   gl_Position = position;
   v_Color = color;
}