package designpatterns.structural.decorator;

import java.math.BigDecimal;

class ThickCrustPizza implements Pizza {
    private final String description;

    public ThickCrustPizza() {
        this.description = "Thick Crust Pizza";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("15.0");
    }
}
