package designpatterns.creational.abstractfactory;

interface ShapeFactory {
    Shape getInstance(String shapeName);
}
