package ru.savin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

@Slf4j
@Service
public class PricingService {

    private Double price;

    public Double getProductPrice() {
        return price;
    }


    //Выполнения задания с фиксированной задержкой.
    @Scheduled(fixedDelay = 2000)
    public void computePrice() throws InterruptedException {
        price = 10000.0;
        log.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        log.info(String.valueOf(getProductPrice()));

        Thread.sleep(4000);
    }

    //Выполнения задания в интервале ISO.
    @Scheduled(fixedDelayString = "${internal}")
    public void computePriceOne() throws InterruptedException {
        Random random = new Random();
        price = random.nextDouble() * 100;
        log.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        log.info(String.valueOf(getProductPrice()));

        Thread.sleep(4000);
    }

    //Выполнения задания каждый час.
    @Scheduled(cron = "@hourly")
    public void computePriceTwo() {
        log.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    //Выполнения задания с фиксированной частотой.
    @Async
    @Scheduled(fixedRate = 3000)
    public void refreshPricingParameters() {
        log.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }

    //Выполнения задания с начальной задержкой.
    @Async
    @Scheduled(initialDelay = 2000, fixedRate = 3000)
    public void refreshPricingParametersOne() {
        log.info("computing price at " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }
}
