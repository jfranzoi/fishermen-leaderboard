package my.projects.fishermenleaderboard.api.web.dto;

import java.math.BigDecimal;
import java.net.URL;

public class FishermenView {
    private String id;
    private String name;
    private BigDecimal amount;
    private URL picture;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public URL getPicture() {
        return picture;
    }
}
