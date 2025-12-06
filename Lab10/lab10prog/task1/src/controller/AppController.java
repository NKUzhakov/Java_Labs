package controller;

import view.ConsoleView;
import model.StringModifier;


public class AppController {
    private final ConsoleView consoleView;

    public AppController(ConsoleView consoleView) throws IllegalArgumentException{
        if(consoleView == null) throw new IllegalArgumentException("The console view cannot be null");
        this.consoleView = consoleView;
    }

    public void start(){
        int strInitWayNum = consoleView.askStrInitWay(consoleView.INIT_WAY_QUERY);
        String initialStringValue = null;
        switch(strInitWayNum){
            case 1: // Initialise with default program value
                initialStringValue = StringModifier.DEFAULT_INIT_STR_VAL;
                break;
            case 2: // Initialise from keyboard
                initialStringValue = consoleView.askString(consoleView.INIT_STR_VALUE_QUERY);
                break;
        }

        String newStringValue = null;
        strInitWayNum = consoleView.askStrInitWay(consoleView.NEW_VAL_WAY_QUERY);
        switch(strInitWayNum){
            case 1: // Initialise with default program value
                newStringValue = StringModifier.DEFAULT_NEW_STR_VAL;
                break;
            case 2: // Initialise from keyboard
                newStringValue = consoleView.askString(consoleView.NEW_STR_VALUE_QUERY);
                break;
        }
        
        StringModifier sm = new StringModifier(initialStringValue);
        sm.changeValue(newStringValue);
        consoleView.printModificationReport(initialStringValue, newStringValue);
    }
}

