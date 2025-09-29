package tourniquet.skiPass;

import java.time.LocalDateTime;

public class WorkdayClimbAmountUnlimitedSkiPass extends TimeLimitedSkiPass{
    public WorkdayClimbAmountUnlimitedSkiPass(LocalDateTime issueDateTime, LocalDateTime expirationDateTime) throws IllegalArgumentException{
        super(SkiPassType.WORKDAY_CLIMB_AMOUNT_UNLIMITED, issueDateTime, expirationDateTime);
    }
}
