package my.projects.fishermenleaderboard.api.web;

import java.math.BigDecimal;

public class FishermenView {
    private String id;
    private BigDecimal amount;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
