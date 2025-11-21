package designpatterns.behavioral.strategy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class PaypalStrategy implements PaymentStrategy {
    private String username;
    private String password;

    @Override
    public boolean pay(int amount) {
        System.out.println("Payment of " + amount + " paid with Paypal");
        return false;
    }
}
