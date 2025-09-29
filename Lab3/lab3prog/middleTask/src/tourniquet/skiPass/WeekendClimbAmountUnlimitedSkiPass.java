package tourniquet.skiPass;

import java.time.LocalDateTime;

public class WeekendClimbAmountUnlimitedSkiPass extends TimeLimitedSkiPass{
    public WeekendClimbAmountUnlimitedSkiPass(LocalDateTime issueDateTime, LocalDateTime expirationDateTime) throws IllegalArgumentException{
        super(SkiPassType.WEEKEND_CLIMB_AMOUNT_UNLIMITED, issueDateTime, expirationDateTime);
    }
}
