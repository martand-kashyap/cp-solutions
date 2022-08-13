package designpatterns.structural.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class AppleAdapter implements Apple {
    private Orange orange;

    @Override
    public String getVariety() {
        return orange.getVariety();
    }

    @Override
    public void eat() {
        orange.peel();
        orange.eat();
    }
}
