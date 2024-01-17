# towers-of-hanoi

forked from [LuciferUchiha/Tower-of-Hanoi](https://github.com/LuciferUchiha/Tower-of-Hanoi). Made this a maven project and tweaked some small things (see commit history).

**Original project information follows:**

## About The Project

The goal of this project is to have a visual UI for the puzzle "Tower of Hanoi". The UI was created with JavaFx and the Java version 1.11. The project also includes an algorithm that solves the puzzle in as little moves as possible. The puzzle consists of three rods and a given number of rings of different sizes, which can slide onto any rod. The puzzle starts with the rings in a neat stack in ascending order of size on the far left rod, the smallest at the top. 

The Goal is to move the stack of rings to the far right rod, however no larger ring may be placed on top of a smaller ring and you may only move one ring at a time. 

With 3 rings, the puzzle can be solved in 7 moves. The minimal number of moves required to solve a Tower of Hanoi puzzle is <a href="https://www.codecogs.com/eqnedit.php?latex=2^{n}&space;-&space;1" target="_blank"><img src="https://latex.codecogs.com/gif.latex?2^{n}&space;-&space;1" title="2^{n} - 1" /></a>, where n is the number of rings.
[Read more on Tower of Hanoi](https://en.wikipedia.org/wiki/Tower_of_Hanoi)

### Algorithm
To solve the puzzle the UI has two algorithms built in. One of them is [iterative](https://en.wikipedia.org/wiki/Tower_of_Hanoi#Iterative_solution) and the other is [recursive](https://en.wikipedia.org/wiki/Tower_of_Hanoi#Recursive_solution).

### Built With
* [Java 11](https://www.java.com/en/)
* [javaFx](https://junit.org/junit4/)
* [Eclipse](https://www.eclipse.org/)

## License

Distributed under the MIT License. See `LICENSE` for more information.
