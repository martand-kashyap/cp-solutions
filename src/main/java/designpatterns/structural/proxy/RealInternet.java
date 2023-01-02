package designpatterns.structural.proxy;

class RealInternet implements Internet {
    @Override
    public void connectTo(String hostName) {
        System.out.println("Connecting to the URL : " + hostName);
    }
}
