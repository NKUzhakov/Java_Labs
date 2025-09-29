package tourniquet.skiPass;

import java.time.LocalDateTime;

public class SeasonSkiPass extends TimeLimitedSkiPass{
    public SeasonSkiPass(LocalDateTime issueDateTime, LocalDateTime expirationDateTime) throws IllegalArgumentException{
        super(SkiPassType.SEASON, issueDateTime, expirationDateTime);
    }
}
