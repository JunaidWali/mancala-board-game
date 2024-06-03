# Mancala

This project is a mancala game between two human players that has a UI using Java SWING

## Description

This is an object-oriented Mancala game made with 5 core classes - Pit, Store, Player, GameRules, MancalaDataStructure and MancalaGame. The are exceptions that are custom made for this game and are handled accordingly. The MancalaUI class in the ui package runs the programs and handles all the display tasks.

## Getting Started

### Dependencies

* Make sure Java and Gradle is installed before running the program.



### Executing program

* Make sure you are in the GP2 directory
* This directory should have the build.gradle file
* The README.md file is also in this directory
* To compile the program run this command in the command line:
```
gradle build
```
* The program will compile going through test cases in the src\test folder and checkstyle.
* After compilation is done, run the program by clicking on the 'Run' button
* You can also run it from the jar file using this command:
```
java -jar build/libs/Mancala.jar
```

## Limitations

There are no errors, and program compiles and runs perfectly. There are PMD warnings and a lot of them are redundant. The UI has some problems with fucntionality, and cannot update the store properly.

## Author Information

* Name: Junaid Wali Khan
* Student ID: 1121707
* Email: junaidwa@uoguelph.ca

## Development History

* 1.0
    * Completed README and final push
    * Completed game logic and UI
    * Completed and passed all test cases
* 0.4
    * Completed KalahRules and AyoRules
    * See [commit change]() 
* 0.1
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)



