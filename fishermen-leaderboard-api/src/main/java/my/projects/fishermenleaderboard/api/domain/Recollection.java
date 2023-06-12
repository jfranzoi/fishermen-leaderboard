package my.projects.fishermenleaderboard.api.domain;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZonedDateTime;

public class Recollection {
    String id;
    BigDecimal amount;
    ZonedDateTime date;
    URL picture;

    public Recollection(String id, BigDecimal amount, ZonedDateTime date, URL picture) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.picture = picture;
    }

    public BigDecimal amount() {
        return amount;
    }

    public ZonedDateTime date() {
        return date;
    }

    public URL picture() {
        return picture;
    }

    @Override
    public String toString() {
        return "Recollection{" +
                "id='" + id + ",amount=" + amount + ",date=" + date + ",picture=" + picture +
                '}';
    }
}
