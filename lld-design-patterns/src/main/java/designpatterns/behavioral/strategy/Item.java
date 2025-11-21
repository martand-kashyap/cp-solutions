package designpatterns.behavioral.strategy;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
class Item {
    private String upcCode;
    private int price;
}