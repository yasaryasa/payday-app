package com.payday.observer;

import com.payday.dto.StocksPriceDto;

public interface StocksObserver {

    void update(StocksPriceDto priceMessage);

}
