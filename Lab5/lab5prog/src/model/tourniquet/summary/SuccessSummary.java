package model.tourniquet.summary;

import java.util.ArrayList;
import model.tourniquet.Transaction;

public class SuccessSummary {
    private int successfulTransactionCount;
    private int failTransactionCount;

    public SuccessSummary(int successfulTransactionCount, int failTransactionCount) throws IllegalArgumentException{
        if(successfulTransactionCount < 0) throw new IllegalArgumentException("The count of successful transactions cannot be negative");
        if(failTransactionCount < 0) throw new IllegalArgumentException("The count of failed transactions cannot be negative");
    
        this.successfulTransactionCount = successfulTransactionCount;
        this.failTransactionCount = failTransactionCount;
    }
    public SuccessSummary(ArrayList<Transaction> transactions){
        int successCount = 0, failCount = 0;
        for(Transaction transaction : transactions){
            if(transaction.isSuccessful()) successCount++;
            else failCount++;
        }

        this.successfulTransactionCount = successCount;
        this.failTransactionCount = failCount;
    }

    public int getSuccessfulTransactionCount(){ return successfulTransactionCount;}
    public int getFailTransactionCount(){ return failTransactionCount;}
    @Override
    public String toString(){
        return "SuccessSummary{\n" +
                "   successfulTransactionCount = " + successfulTransactionCount + "\n" + 
                "   failTransactionCount = " + failTransactionCount + "\n}";
    }
}
