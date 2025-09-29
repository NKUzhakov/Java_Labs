package tourniquet.skiPass;

public class WorkdayClimbAmountLimitedSkiPass extends ClimbAmountLimitedSkiPass{
    public WorkdayClimbAmountLimitedSkiPass(int totalClimbAmount) throws IllegalArgumentException{
        super(SkiPassType.WORKDAY_CLIMB_AMOUNT_LIMITED, totalClimbAmount);
    }
}
