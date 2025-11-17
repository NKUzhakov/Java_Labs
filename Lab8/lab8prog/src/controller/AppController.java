package controller;

import model.ParallelMonteCarloPi;
import view.ConsoleView;

public class AppController {
    private final ConsoleView consoleView;

    public AppController(ConsoleView consoleView){
        this.consoleView = consoleView;
    }

    public void start(){
        while(true){
            int threadNum = consoleView.askInteger(consoleView.THREAD_COUNT_QUERY); 
            if(threadNum == 0) break;
            try{
                ParallelMonteCarloPi mc = new ParallelMonteCarloPi(threadNum);
                consoleView.printCalcResult(mc.calculate());
            }catch(IllegalArgumentException e){
                consoleView.errorNotification(e.getMessage());
            }            
        }
    }
}
