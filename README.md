# RubikLearn
This project is the idea to have an AI be able to learn how to solve the Rubik's Cube with no help. Currently only an emulator is created with a simple UI. The way the cube was created allows to abstract just the Cube out. Meaning a UI is not needed therefore can be slightly faster in moving the cube with an AI.

## Features
* Add a ChangeListener to know when the Cube has moved
* Simple methods to move and reset the cube
* CubeCanvas included for a 2D image of the cube (Uses the listener to know when to render cube).

## Interesting facts
* The main component is the Cubit. It is responsible for it's movement and does not rely on any other cubits or sides when it is time to move.
* Sides were created mainly for organization and to make the color matrix.
* The Cube itself creates all the cubits whenever a new cube is extantiated. But calls all the cubits when it is time to move. It is also responsible for letting the listeners know that the cube has changed.

## TODO
* Add a MessUp/Shuffle/Random button to mix up the cube
* After it is mixed up assert that all cubits are valid.
* Add Validity to sides and cubes to make sure the cube is a possible cube.