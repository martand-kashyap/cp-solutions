package designpatterns.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public ShoppingCart(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int calculateTotal() {
        int sum = 0;

        for (Item item : items) sum += item.getPrice();

        return sum;
    }

    public void pay(PaymentStrategy ps) {
        int amt = calculateTotal();
        ps.pay(amt);
    }
}
