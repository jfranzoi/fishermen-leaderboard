package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Recollection {
    BigDecimal amount;
    ZonedDateTime date;

    public Recollection(BigDecimal amount, ZonedDateTime date) {
        this.amount = amount;
        this.date = date;
    }
}
