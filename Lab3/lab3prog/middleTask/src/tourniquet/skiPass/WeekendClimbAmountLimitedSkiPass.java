package tourniquet.skiPass;

public class WeekendClimbAmountLimitedSkiPass extends ClimbAmountLimitedSkiPass{
    public WeekendClimbAmountLimitedSkiPass(int totalClimbAmount) throws IllegalArgumentException{
        super(SkiPassType.WEEKEND_CLIMB_AMOUNT_LIMITED, totalClimbAmount);
    }
}