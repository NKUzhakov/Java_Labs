import model.ShapeProcessor;
import controller.AppController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args){
        AppController appController = new AppController(new ShapeProcessor(), new ConsoleView());
        appController.start();
    }
}


// compile: javac -d ./out/ ./src/*.java ./src/controller/*.java ./src/model/*.java ./src/model/shapes/*.java ./src/view/*.java
// start: java -cp ./out Main
