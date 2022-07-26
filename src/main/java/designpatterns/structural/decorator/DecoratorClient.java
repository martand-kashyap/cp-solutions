package designpatterns.structural.decorator;

class DecoratorClient {
	public static void main(String[] args) {
		Pizza pizza = new ThickCrustPizza();
		System.out.println(pizza.getDescription());
		System.out.println(pizza.getPrice());

		Pepperoni pepperoni = new Pepperoni(pizza);
		System.out.println(pepperoni.getDescription());
		System.out.println(pepperoni.getPrice());

		Pepperoni doublePepperoni = new Pepperoni(pepperoni);
		System.out.println(doublePepperoni.getDescription());
		System.out.println(doublePepperoni.getPrice());
	}
}
