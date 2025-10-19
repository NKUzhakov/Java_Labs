package model.tourniquet.summary;
import model.tourniquet.skiPass.SkiPassType;
import model.tourniquet.Transaction;
import java.util.*;

public class SuccessBySkiPassTypeSummary {
    private final HashMap<SkiPassType, SuccessSummary> summaryByTypes = new HashMap<>();

    public SuccessBySkiPassTypeSummary(ArrayList<Transaction> transactions){
        HashMap<SkiPassType, Integer> successCount = new HashMap<>();
        HashMap<SkiPassType, Integer> failCount = new HashMap<>();
        for(SkiPassType type : SkiPassType.values()){
            successCount.put(type, 0);
            failCount.put(type, 0);
        }

        for(Transaction transaction : transactions){
            SkiPassType curentType = transaction.getSkiPass().getType();
            if(transaction.isSuccessful()){                
                successCount.put(curentType, successCount.get(curentType) + 1);
            }else{
                failCount.put(curentType, failCount.get(curentType) + 1);
            }
        }

        for(SkiPassType type : SkiPassType.values()){
            summaryByTypes.put(type, new SuccessSummary(successCount.get(type), failCount.get(type)));
        }
    }

    public SuccessSummary getSuccessSummaryByType(SkiPassType type){
        return summaryByTypes.get(type);
    }

    @Override
    public String toString(){
        String result = "SuccessBySkiPassTypeSummary{\n";
        for(SkiPassType type : SkiPassType.values()){
            result += type.toString() + " - " + summaryByTypes.get(type).toString() + "\n";
        }
        return result;
    }
}
