package designpatterns.creational.abstractfactory;

class FactoryProvider {
    static ShapeFactory getFactory(boolean isRound) {
        if (isRound) {
            return new RoundedShapeFactory();
        } else
            return new StandardShapeFactory();
    }
}
