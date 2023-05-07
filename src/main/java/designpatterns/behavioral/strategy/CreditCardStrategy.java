package designpatterns.behavioral.strategy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    @Override
    public boolean pay(int amount) {
        System.out.println("Payment of " + amount + " paid with credit/debit card");
        return true;
    }
}
