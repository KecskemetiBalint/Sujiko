Puzzle Solver

Developed by:

- Jain Ayush

- Fink Louis-Philippe

- Kecskeméti Balint

- Parashar Lakshit

- Mecsei Botond

Base code by Tom Verhoeff Eindhoven University of Technology


Overview of the game and rules:

Sujiko is a logic-based, combinatorial number-placement puzzle created by Jai Gomer of Kobayaashi Studios. The puzzle takes place on a 3x3 grid with four circled number clues at the centre of each quadrant which indicate the sum of the four numbers in that quadrant. The numbers 1-9 must be placed in the grid, in accordance with the circled clues, to complete the puzzle. Puzzle Solver is a Java-based application designed to solve Sujiko puzzles using a combination of graphical user interface (GUI) components and sophisticated solving algorithms. The project encompasses various classes to represent the puzzle structure, execute commands, manage undo/redo operations, and apply different reasoning techniques for solving the puzzle. 

[Details an sample game on wikipedia](https://en.wikipedia.org/wiki/Sujiko)

[Sujiko puzzles to download and print](https://dobmathsnumberpuzzles.weebly.com/sujiko.html)
 
Design pattern used in the implementation

- Command pattern

- Decorator pattern

- Iterator pattern

- Strategy pattern

Features

    GUI Components:
        MainFrame: Main application window.
        PuzzlePanel: Panel for displaying and interacting with the puzzle.
    Puzzle Model:
        SCell, SEntry, SGrid, SPuzzle, SSpec: Classes representing the puzzle's cells, entries, grid, overall puzzle, and specifications, respectively.
    Solvers:
        SujikoAbstractSolver: Abstract base class for solvers.
        SujikoBacktrackSolver: Implementation of a backtracking solver with reasoning capabilities.
    Reasoners:
        BasicEmptyCellByContradiction, EntryWithOneEmptyCell, FixpointReasoner, GeneralizedEmptyCellByContradiction: Various strategies to fill cells based on logical deductions.
    Command Framework:
        Command, CompoundCommand, GenericCommand, SetCommand: Classes to represent and manage executable and revertible commands.
        UndoRedo: Manages undo and redo operations.

Installation

To run this project locally, you need to have Java installed on your machine.

Clone the repository:


git clone https://github.com/yourusername/puzzle-solver.git

Navigate to the project directory:


cd puzzle-solver

Compile the project:

javac -d bin src/**/*.java

Run the application:

    java -cp bin ypa.MainFrame

Usage

    Launch the application.
    Load or create a Sujiko puzzle via the GUI.
    Use the available solving tools and strategies to solve the puzzle.
    Utilize the undo/redo functionality to manage your solving steps.

Contributing

Contributions are welcome! Please follow these steps to contribute:

    Fork the repository.
    Create a new branch:

git checkout -b feature-branch

Make your changes.
Commit your changes:

git commit -m "Description of changes"

Push to the branch:

    git push origin feature-branch

    Open a pull request.


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/_p0yNlNQ)
