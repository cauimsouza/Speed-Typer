# Speed-Typer
Simple java application for typing speed test

Speed Typer is a project developed by me at École Polytechnique whose objective is to implement multithreading techniques seen in course.
The application allows the user to enter his name and the duration of the test. For every word that the user enters, he will gain points proportional to the length of the word if the word is correct, and will lose points proportional to the length of the word if the word is incorrect (does not exist in the dictionary included in the folder dictionary/). The user sees the changes in his score while he types.

HOW TO BUILD

- Requirements:
  Java 8 (or higher)

On Ubuntu 16.04 LTS:
  - open a terminal in /src directory
  - "bash build.sh" to build
  - "java -jar SpeedTyper.jar" to execute
