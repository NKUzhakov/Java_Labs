package tourniquet;

import tourniquet.skiPass.SkiPass;
import java.time.LocalDateTime;

public class Transaction {
    private final boolean successful;
    private final SkiPass skiPass;
    private final LocalDateTime dateTime;
    
    public Transaction(boolean successful, SkiPass skiPass, LocalDateTime dateTime) throws RuntimeException{
        this.successful = successful;
        try{
            this.skiPass = (SkiPass)skiPass.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("Could not copy the ski-pass while creating transaction", e);
        }
        
        this.dateTime = dateTime;
    }
    public boolean isSuccessful(){ return successful;}
    public SkiPass getSkiPass() throws RuntimeException{
        try{
            return (SkiPass)skiPass.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("Could not copy the ski-pass while getting it", e);
        }         
    }
    public LocalDateTime getDateTime(){ return dateTime;}
}
