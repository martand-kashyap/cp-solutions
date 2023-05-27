package designpatterns.creational.singleton;

import java.io.Serial;
import java.io.Serializable;

class ThreadSafeLazySingleton implements Serializable, Cloneable {
    private static volatile ThreadSafeLazySingleton threadSafeLazySingletonInstance;

    private ThreadSafeLazySingleton() throws InstantiationException {
        if (threadSafeLazySingletonInstance != null) {
            throw new InstantiationException("saving from Reflection");
        }
    }

    //handle serialization
    @Serial
    protected Object readResolve() {
        return threadSafeLazySingletonInstance;
    }

    //handle cloning
    @Override
    protected Object clone() {
        return threadSafeLazySingletonInstance;
    }

    public static synchronized ThreadSafeLazySingleton getInstance() throws InstantiationException {
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
