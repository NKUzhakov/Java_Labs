package tourniquet.skiPass;

public abstract class ClimbAmountLimitedSkiPass extends SkiPass{
    private int climbAmountLeft;
    public ClimbAmountLimitedSkiPass(SkiPassType type, int totalClimbAmount){
        super(type);
        if(!(type == SkiPassType.WEEKEND_CLIMB_AMOUNT_LIMITED || type == SkiPassType.WORKDAY_CLIMB_AMOUNT_LIMITED)){
            throw new IllegalArgumentException("The type can only be WEEKEND_CLIMB_AMOUNT_LIMITED or WORKDAY_CLIMB_AMOUNT_LIMITED");
        }
        if(totalClimbAmount < 1){
            throw new IllegalArgumentException("The total climb amount nust be natural number");
        }
        climbAmountLeft = totalClimbAmount;
    }
    public int getClimbAmountLeft(){ return climbAmountLeft;}
    @Override
    public boolean isAppliable(){
        return super.isAppliable() && climbAmountLeft > 0;
    }
    @Override
    public boolean apply(){
        if(isAppliable()){
            climbAmountLeft--;
            return true;
        }
        return false;
    }
}
