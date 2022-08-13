package designpatterns.creational.singleton;

class Apple {
    private static volatile Apple appleInstance;

    private Apple() {

    }

    public static Apple getInstance() {
        if (appleInstance == null) {
            appleInstance = new Apple();
        }
        return appleInstance;
    }

    public static synchronized Apple getInstanceThreadSafe() {
        if (appleInstance == null) {
            synchronized (Apple.class) {
                if (appleInstance == null) {
                    appleInstance = new Apple();
                }
            }
        }
        return appleInstance;
    }
}
