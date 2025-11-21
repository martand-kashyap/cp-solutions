package designpatterns.creational.factory;

class Client {
    public static void main(String[] args) {
        Shape square = new ShapeFactory().getInstance("square");
        square.draw();

        Shape rectangle = new ShapeFactory().getInstance("rectangle");
        rectangle.draw();
    }
}
