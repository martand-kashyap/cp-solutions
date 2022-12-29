package designpatterns.creational.singleton;

import java.io.Serial;
import java.io.Serializable;

class ThreadSafeLazySingleton implements Serializable {
    private static volatile ThreadSafeLazySingleton threadSafeLazySingletonInstance;

    private ThreadSafeLazySingleton() {
    }

    @Serial
    private Object readResolve() {
        return threadSafeLazySingletonInstance;
    }

    public static synchronized ThreadSafeLazySingleton getInstance() {
        if (threadSafeLazySingletonInstance == null) {
            synchronized (ThreadSafeLazySingleton.class) {
                if (threadSafeLazySingletonInstance == null) {
                    threadSafeLazySingletonInstance = new ThreadSafeLazySingleton();
                }
            }
        }
        return threadSafeLazySingletonInstance;
    }
}
