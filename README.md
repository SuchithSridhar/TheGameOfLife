# Game Of Life

#### John Conway's Game of Life Written in Java

### Requirements

- java
- python3 (optional)
- pygame (optional)

### Run the Game

You can just run the game by doing ./play.sh or compiling classes into
"GameOfLife" folder.

`./play.sh`

or

```
javac *.java
mkdir GameOfLife
mv *.class GameOfLife
java GameOfLife.Driver
```

You can provide an input file by doing on of these:

`./play.sh -i <file>`

or

`java GameOfLife.Driver <file>`

You can run the help by doing:

`./play.sh --help`

### Use pygame for generating inputFiles

To make it easier to generate inputFiles:

`python createInputFile.py`
