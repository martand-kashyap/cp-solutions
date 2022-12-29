package designpatterns.creational.singleton;

class Client {
    public static void main(String[] args) {
        // eager singleton
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        System.out.println("eager instance 1 : " + instance1);
        System.out.println("eager instance 2 : " + instance2);
        System.out.println("is instance1 == instance2 ? " + (instance1 == instance2));

        //eager singleton, static block init
        StaticBlockSingleton staticBlockSingleton1 = StaticBlockSingleton.getInstance();
        StaticBlockSingleton staticBlockSingleton2 = StaticBlockSingleton.getInstance();
        System.out.println("\neager static block instance 1 : " + staticBlockSingleton1);
        System.out.println("eager static block instance 2 : " + staticBlockSingleton2);
        System.out.println("is staticBlockSingleton1 == staticBlockSingleton2 ? " + (staticBlockSingleton1 == staticBlockSingleton2));

        //thread-safe lazy singleton
        ThreadSafeLazySingleton threadSafeLazySingleton1 = ThreadSafeLazySingleton.getInstance();
        ThreadSafeLazySingleton threadSafeLazySingleton2 = ThreadSafeLazySingleton.getInstance();
        System.out.println("\neager thread safe lazy instance 1 : " + threadSafeLazySingleton1);
        System.out.println("eager thread safe lazy instance 2 : " + threadSafeLazySingleton2);
        System.out.println("is threadSafeLazySingleton1 == threadSafeLazySingleton2 ? " + (threadSafeLazySingleton1 == threadSafeLazySingleton2));
    }
}
