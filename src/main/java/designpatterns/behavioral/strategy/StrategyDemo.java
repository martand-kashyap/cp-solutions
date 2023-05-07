package designpatterns.behavioral.strategy;

class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new Item("1234", 10);
        Item item2 = new Item("5678", 40);

        cart.addItem(item1);
        cart.addItem(item2);

        PaymentStrategy paypal = new PaypalStrategy("myemail@example.com", "mypwd");
        PaymentStrategy card = new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15");

        //pay by paypal
        cart.pay(paypal);

        //pay by credit card
        cart.pay(card);
    }
}
