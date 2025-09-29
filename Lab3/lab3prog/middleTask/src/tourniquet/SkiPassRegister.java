package tourniquet;
import tourniquet.skiPass.SkiPass;
import java.util.HashMap;

public class SkiPassRegister {
    private HashMap<Long, SkiPass> skiPassData= new HashMap<>();

    public SkiPass createSkiPass(SkiPass skiPass) throws IllegalArgumentException, RuntimeException{
        if(skiPassData.keySet().contains(skiPass.getId())) throw new IllegalArgumentException("Ski-pass with the same id already exists");        
        if(!skiPass.isAppliable()) throw new IllegalArgumentException("Cannot create ski-pass that is unable to be applied");

        SkiPass copiedSkiPass;
        try{
            copiedSkiPass = (SkiPass)skiPass.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("Could not copy the ski-pass while creating it in the register", e);
        }
        skiPassData.put(copiedSkiPass.getId(), copiedSkiPass);
        return skiPass;       
    }
    public SkiPass blockSkiPass(SkiPass skiPass)throws IllegalArgumentException{
        SkiPass skiPassToBlock = skiPassData.get(skiPass.getId());
        if(skiPassToBlock == null) throw new IllegalArgumentException("Cannot block not existing ski-pass");
        skiPassToBlock.block();
        System.out.println("isAppliable: " + skiPassData.get(skiPass.getId()).isAppliable());
        return skiPass;
    }
    public boolean validate(SkiPass skiPass){
        SkiPass skiPassToValidate = skiPassData.get(skiPass.getId());
        return (skiPassToValidate != null) && skiPassToValidate.isAppliable();
    }
    public SkiPass synchronizeSkiPassCredit(SkiPass skiPass)throws IllegalArgumentException, RuntimeException{
        SkiPass skiPassToUpdate = skiPassData.get(skiPass.getId());
        if(skiPassToUpdate == null) throw new IllegalArgumentException("Cannot update the credit of not existing ski-pass");
        // if(!checkRegistration(skiPass)) throw new IllegalArgumentException("Cannot update the credit of not existing ski-pass");
        
        SkiPass copiedUpdatedSkiPass;
        try{
            copiedUpdatedSkiPass = (SkiPass)skiPass.clone();
        }catch(CloneNotSupportedException e){
            throw new RuntimeException("Could not copy the ski-pass while synchronizing it`s credits", e);
        }
        skiPassData.put(copiedUpdatedSkiPass.getId(), copiedUpdatedSkiPass);
        return skiPass;  
    }
}
