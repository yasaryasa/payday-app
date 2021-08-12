package com.payday.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.payday.observer.StocksObserver;
import com.payday.observer.StocksSubjectRunnable;
import com.payday.service.StocksService;

@Component
public class SimulationRunner implements CommandLineRunner {

	@Autowired
	private StocksService stocksService;
	@Autowired
    private StocksObserver stocksObserver;

    @Override
    public void run(String... args) {
        StocksSubjectRunnable stocksSubjectRunnable = new StocksSubjectRunnable(stocksService);
        stocksSubjectRunnable.register(stocksObserver);

        new Thread(stocksSubjectRunnable).start();
    }

}
