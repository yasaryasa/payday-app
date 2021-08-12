package com.payday.observer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payday.dto.StocksPriceDto;
import com.payday.service.StocksService;

public class StocksSubjectRunnable implements StocksSubject, Runnable {

	private Logger logger = LoggerFactory.getLogger(StocksSubjectRunnable.class);
	
    private static final int SLEEP_TIME = 1000;
    private static final int TRADE_DIFFICULT = 2;
    private static final Random rand = new SecureRandom();
    private static final BigDecimal INITIAL_PRICE = new BigDecimal(60);

    private final List<StocksObserver> observers = new ArrayList<>();
    
    private List<StocksPriceDto> stocksList = new ArrayList<>();
    private final StocksService stocksService;
    private StocksPriceDto stockPrice;
    public StocksSubjectRunnable(StocksService stocksService) {
    	this.stocksService = stocksService;
    	//dummy stocks
    	StocksPriceDto asel = new StocksPriceDto("ASEL", "Aselsan", INITIAL_PRICE, LocalDateTime.now());
    	StocksPriceDto thy = new StocksPriceDto("THY", "Turk Hava Yollari", INITIAL_PRICE, LocalDateTime.now());
    	stocksList.add(asel);
    	stocksList.add(thy);
	}

	@Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(SLEEP_TIME);
                boolean hasChanged = simulateTrade();
                if (hasChanged) {
                	stockPrice = stocksList.get(rand.nextInt(stocksList.size() - 0));
                    BigDecimal newPrice = getNewPrice(stockPrice.getPrice());
                    stockPrice.setPrice(newPrice);
                    stockPrice.setPriceTime(LocalDateTime.now());
                    stocksService.updateStocksPrice(stockPrice);
                    notifyObservers();
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            } catch (Exception e) {
            	//if any exception is thrown, thread should continue to create price for stocks
            	logger.error(e.getMessage());
			}
        }
    }

    @Override
    public void register(StocksObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(StocksObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        logger.info("New {}", stockPrice);
        observers.forEach(observer -> observer.update(stockPrice));
    }

    private boolean simulateTrade() {
        boolean trade = true;
        int i = 0;
        do {
            trade = trade && rand.nextBoolean();
            ++i;
        } while (i < TRADE_DIFFICULT);
        return trade;
    }

    private BigDecimal getNewPrice(BigDecimal currentPrice) {
        boolean sign = rand.nextBoolean();
        double var = rand.nextDouble() * 10;
        BigDecimal variation = BigDecimal.valueOf(sign ? var : -1 * var);
        BigDecimal newValue = currentPrice.add(variation).setScale(2, RoundingMode.HALF_UP);
        return newValue;
    }

}
