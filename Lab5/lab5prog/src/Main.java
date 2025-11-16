import view.ConsoleView;
import controller.AppController;

public class Main {
    public static void main(String[] args){    
        AppController app = new AppController(new ConsoleView());
        app.start();
    }
}

// compile: javac -d ./out/ ./src/*.java ./src/view/*.java ./src/controller/*.java  ./src/model/tourniquet/*.java ./src/model/tourniquet/skiPass/*.java ./src/model/tourniquet/summary/*.java ./src/model/fileProcessor/*.java
// start: java -cp ./out Main


// filePath: ./file.txt
// resourse: http://localhost:30050
