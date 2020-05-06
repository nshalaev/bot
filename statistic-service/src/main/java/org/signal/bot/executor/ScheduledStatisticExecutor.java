package org.signal.bot.executor;

import org.signal.bot.service.NotificationServiceRESTInvoker;
import org.signal.bot.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledStatisticExecutor {

    @Autowired
    private PriceService priceService;

    @Autowired
    private NotificationServiceRESTInvoker notificationServicePingService;

    @Scheduled(cron = "0 0/30 * * * *")
    public void handleStatistic() {
        priceService.handleNewPrice();
        notificationServicePingService.pingNotificationService();
    }

}
