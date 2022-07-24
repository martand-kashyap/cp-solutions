package designpatterns.creational.abstractfactory;

class RoundedSquare implements Shape{
    @Override
    public void draw() {
        System.out.println("Rounded Square...");
    }
}
