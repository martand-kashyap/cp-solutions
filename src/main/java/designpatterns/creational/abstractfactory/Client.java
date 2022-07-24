package designpatterns.creational.abstractfactory;

class Client {
    public static void main(String[] args) {
        //Standard Shapes
        ShapeFactory stdShapeFactory = FactoryProvider.getFactory(false);
        Shape square = stdShapeFactory.getInstance("square");
        square.draw();

        Shape rectangle = stdShapeFactory.getInstance("rectangle");
        rectangle.draw();

        //Round Shapes
        ShapeFactory roundShapeFactory = FactoryProvider.getFactory(true);
        square = roundShapeFactory.getInstance("square");
        square.draw();

        rectangle = roundShapeFactory.getInstance("rectangle");
        rectangle.draw();
    }
}
