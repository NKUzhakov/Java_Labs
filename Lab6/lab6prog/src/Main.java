import view.ConsoleView;
import controller.AppController;
import model.Translator;

public class Main {
    public static void main(String[] args){
        Translator translator = new Translator();
        ConsoleView consoleView = new ConsoleView();
        AppController app = new AppController(consoleView, translator);
        app.start();
    }
}


// compile: javac -d ./out/ ./src/*.java ./src/view/*.java ./src/controller/*.java  ./src/model/*.java
// start: java -cp ./out Main