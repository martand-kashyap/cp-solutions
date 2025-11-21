package designpatterns.structural.decorator;

class Client {
    public static void main(String[] args) {
        Pizza pizza = new ThickCrustPizza();
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getPrice());

        PepperoniPizza pepperoni = new PepperoniPizza(pizza);
        System.out.println(pepperoni.getDescription());
        System.out.println(pepperoni.getPrice());

        PepperoniPizza doublePepperoni = new PepperoniPizza(pepperoni);
        System.out.println(doublePepperoni.getDescription());
        System.out.println(doublePepperoni.getPrice());
    }
}
