package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZonedDateTime;

public class Recollection {
    BigDecimal amount;
    ZonedDateTime date;
    URL picture;

    public Recollection(BigDecimal amount, ZonedDateTime date, URL picture) {
        this.amount = amount;
        this.date = date;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Recollection{" +
                "amount=" + amount + ", date=" + date + ", picture=" + picture +
                '}';
    }
}
