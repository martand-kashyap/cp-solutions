package designpatterns.behavioral.template;

public class TemplateClient {
    public static void main(String[] args) {
        HouseTemplate houseType = new WoodenHouse();
        //using template method
        houseType.buildHouse();

        System.out.println("************");

        houseType = new GlassHouse();
        houseType.buildHouse();
    }

}
