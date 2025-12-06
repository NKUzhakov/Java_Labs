import view.ConsoleView;
import controller.AppController;

public class Main {
    public static void main(String[] args){    
        AppController app = new AppController(new ConsoleView());
        app.start();
    }
}


// compile: javac -d ./out/ ./src/*.java ./src/view/*.java ./src/controller/*.java  ./src/model/*.java
// javac --release 8 -d ./out/ ./src/*.java ./src/view/*.java ./src/controller/*.java  ./src/model/*.java

// start: & "C:\Program Files\Java\jre1.8.0_471\bin\java.exe" -cp ./out Main