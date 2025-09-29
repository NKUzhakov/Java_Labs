package tourniquet.skiPass;
import java.time.LocalDateTime;

public abstract class TimeLimitedSkiPass extends SkiPass{
    private final LocalDateTime issueDateTime; // LocalDateTime is immutable type
    private final LocalDateTime expirationDateTime;
    
    public TimeLimitedSkiPass(SkiPassType type, LocalDateTime issueDateTime, LocalDateTime expirationDateTime) throws IllegalArgumentException{
        super(type);
        if(type == SkiPassType.WEEKEND_CLIMB_AMOUNT_LIMITED || type == SkiPassType.WORKDAY_CLIMB_AMOUNT_LIMITED){
            throw new IllegalArgumentException("The type cannot be WEEKEND_CLIMB_AMOUNT_LIMITED or WORKDAY_CLIMB_AMOUNT_LIMITED");
        }
        if(!expirationDateTime.isAfter(issueDateTime)){
            throw new IllegalArgumentException("The expiration date cannot be less or equals to issue date");
        }
        this.issueDateTime = issueDateTime;
        this.expirationDateTime = expirationDateTime;
    }
    public boolean isExpired(){ return expirationDateTime.isBefore(LocalDateTime.now());}
    public LocalDateTime getIssueDateTime(){ return issueDateTime;}
    public LocalDateTime getExpirationDateTime(){ return expirationDateTime;}
    @Override
    public boolean isAppliable(){
        return super.isAppliable() && !isExpired();
    }
    // @Override
    // public boolean apply(){ // Already in base class
    //     return isAppliable();
    // }
    // @Override
    // public Object clone() throws CloneNotSupportedException {
    //     return super.clone();
    // }
}
