package designpatterns.structural.decorator;

import java.math.BigDecimal;

class PepperoniPizza implements Pizza {
    private final Pizza pizza;

    public PepperoniPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " + Pepperoni";
    }

    @Override
    public BigDecimal getPrice() {
        return this.pizza.getPrice().add(new BigDecimal(2));
    }
}
