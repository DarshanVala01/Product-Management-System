package com.project.schedullers;

import com.project.service.StockReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ReportScheduller {

    @Autowired
    private StockReportService stockReportService;

    @Scheduled(cron = "00 35 14 * * *")
    public void scheduledReport(){
        stockReportService.sendStockReport();
    }
}
