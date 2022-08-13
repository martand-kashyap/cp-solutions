package designpatterns.structural.decorator;

import java.math.BigDecimal;

class ThickCrustPizza extends Pizza {

    public ThickCrustPizza() {
        super();
        this.description = "Thick Crust Pizza";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(15.0);
    }

}
