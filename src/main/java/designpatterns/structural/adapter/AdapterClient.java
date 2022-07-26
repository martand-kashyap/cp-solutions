package designpatterns.structural.adapter;

class AdapterClient {

	public static void main(String[] args) {
		Orange orange = new MoroOrange();
		Apple apple = new AppleAdapter(orange);
		System.out.println(apple.getVariety());
		apple.eat();
	}
}
