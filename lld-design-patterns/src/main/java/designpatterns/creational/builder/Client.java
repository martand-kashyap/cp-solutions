package designpatterns.creational.builder;

class Client {
    public static void main(String[] args) {
        URL url1 = new URL
                .URLBuilder("http://", "127.0.0.1")
                .portNumber(80)
                .pathParam("search")
                .queryParam("key=someSearchKey")
                .build();
        System.out.println(url1.toString());
    }
}
