javac gui/*.java
javac thread/*.java
javac main/SpeedTyper.java

jar cfm SpeedTyper.jar Manifest.txt main/SpeedTyper.class gui/*.class thread/*.class ../dictionary/american-english


