package designpatterns.creational.factory;

class ShapeFactory {
    Shape getInstance(String shapeName) throws IllegalArgumentException {
        return switch (shapeName.toLowerCase()) {
            case "square" -> new Square();
            case "rectangle" -> new Rectangle();
            default -> throw new IllegalArgumentException();
        };
    }
}
