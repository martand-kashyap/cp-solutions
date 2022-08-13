package designpatterns.structural.decorator;

import java.math.BigDecimal;

class Pepperoni extends PizzaIngredient {
    private final Pizza pizza;

    public Pepperoni(Pizza pizza) {
        super();
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
