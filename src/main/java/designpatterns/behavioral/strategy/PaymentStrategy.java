package designpatterns.behavioral.strategy;

/**
 * Strategy pattern is used when we have multiple algorithm for a specific task
 * and client decides the actual implementation to be used at runtime.
 */
interface PaymentStrategy {
    public boolean pay(int amount);
}
