import controller.AppController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args){
        ConsoleView consoleView = new ConsoleView();
        AppController app = new AppController(consoleView);
        app.start();
    }
}

// compile: javac -d ./out/ ./src/*.java ./src/view/*.java ./src/controller/*.java  ./src/model/*.java
// start: java -cp ./out Main