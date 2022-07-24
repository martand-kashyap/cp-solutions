package designpatterns.creational.abstractfactory;

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square...");
    }
}
