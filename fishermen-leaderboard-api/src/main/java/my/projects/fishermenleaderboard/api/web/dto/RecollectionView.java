package my.projects.fishermenleaderboard.api.web.dto;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZonedDateTime;

public class RecollectionView {
    private BigDecimal amount;
    private ZonedDateTime date;
    private URL picture;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public URL getPicture() {
        return picture;
    }
}
