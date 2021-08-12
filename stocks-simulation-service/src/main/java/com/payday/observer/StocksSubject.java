package com.payday.observer;

public interface StocksSubject {

    void register(StocksObserver priceObserver);

    void unregister(StocksObserver priceObserver);

    void notifyObservers();

}
