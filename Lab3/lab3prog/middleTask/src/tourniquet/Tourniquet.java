package tourniquet;

import tourniquet.skiPass.SkiPass;
import tourniquet.summary.SuccessBySkiPassTypeSummary;
import tourniquet.summary.SuccessSummary;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Tourniquet {
    private final SkiPassRegister skiPassRegister;
    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    public Tourniquet(SkiPassRegister skiPassRegister){
        this.skiPassRegister = skiPassRegister;
    }

    public boolean applySkiPass(SkiPass skiPass){
        boolean successfulTransaction = true;
        boolean skiPassIsValid = skiPassRegister.validate(skiPass);
        if(skiPassIsValid){
            skiPass.apply();
            skiPassRegister.synchronizeSkiPassCredit(skiPass);
        }else{
            successfulTransaction = false;
        }

        transactionList.add(new Transaction(successfulTransaction, skiPass, LocalDateTime.now()));
        return successfulTransaction;
    }
    public SuccessSummary getGeneralSummary(){
        return new SuccessSummary(transactionList);
    }
    public SuccessBySkiPassTypeSummary getSummaryBySkiPassType(){
        return new SuccessBySkiPassTypeSummary(transactionList);
    }
}
