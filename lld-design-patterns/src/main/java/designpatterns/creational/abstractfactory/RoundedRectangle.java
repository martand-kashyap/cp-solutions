package designpatterns.creational.abstractfactory;

class RoundedRectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rounded Rectangle");
    }
}
