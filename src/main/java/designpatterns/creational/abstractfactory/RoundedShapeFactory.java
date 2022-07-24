package designpatterns.creational.abstractfactory;

class RoundedShapeFactory implements ShapeFactory {
    @Override
    public Shape getInstance(String shapeName) throws IllegalArgumentException {
        return switch (shapeName.toLowerCase()) {
            case "square" -> new RoundedSquare();
            case "rectangle" -> new RoundedRectangle();
            default -> throw new IllegalArgumentException();
        };
    }
}
