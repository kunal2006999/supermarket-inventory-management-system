package com.supermarket.inventory.reports;

import com.supermarket.inventory.bill.BillEntity;
import com.supermarket.inventory.bill.BillRepository;
import com.supermarket.inventory.bill.SaleItemEntity;
import com.supermarket.inventory.bill.SaleItemRepository;
import com.supermarket.inventory.product.ProductRepository;
import com.supermarket.inventory.reports.dtos.DailyReportResponse;
import com.supermarket.inventory.reports.dtos.MonthlyReportResponse;
import com.supermarket.inventory.reports.dtos.ProductResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReportService {

    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;
    private final BillRepository billRepository;

    public ReportService(ProductRepository productRepository, SaleItemRepository saleItemRepository, BillRepository billRepository) {
        this.productRepository = productRepository;
        this.saleItemRepository = saleItemRepository;
        this.billRepository = billRepository;
    }

    public DailyReportResponse getDailyReport(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<BillEntity> bill = billRepository.findAllBySaleDateTimeBetween(start, end);
        BigDecimal total = BigDecimal.valueOf(0.00);
        int totalTransactions = 0;
        int totalItemSold = 0;
        float highestBillAmount = 0;


        for(BillEntity b: bill) {
            BigDecimal billTotal = b.getTotalAmount();
            highestBillAmount = Math.max(highestBillAmount, billTotal.floatValue());
            total = total.add(billTotal);
            totalTransactions++;
            for(SaleItemEntity s: b.getSaleItems()) {
                totalItemSold += s.getQuantity();
            }
        }

        DailyReportResponse report = new DailyReportResponse();
        report.setDate(start);
        report.setTotalAmount(total);
        report.setTotalTransactions(totalTransactions);
        //report.setAverageBillAmount(total.divide(BigDecimal.valueOf(totalTransactions)));
        if (totalTransactions > 0) {
            report.setAverageBillAmount(
                    total.divide(BigDecimal.valueOf(totalTransactions), 2, RoundingMode.HALF_UP)
            );
        } else {
            report.setAverageBillAmount(BigDecimal.ZERO);
        }
        report.setTotalItemSold(totalItemSold);
        report.setHighestBillAmount(highestBillAmount);
        return report;
    }

    public MonthlyReportResponse getMonthlyReport(LocalDate date) {

        LocalDate startOfMonth = date.withDayOfMonth(1);
        LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        LocalDateTime start = startOfMonth.atStartOfDay();
        LocalDateTime end = endOfMonth.atTime(LocalTime.MAX);

        List<BillEntity> bill = billRepository.findAllBySaleDateTimeBetween(start, end);
        BigDecimal total = BigDecimal.valueOf(0.00);
        int totalTransactions = 0;
        int totalItemSold = 0;
        float highestBillAmount = 0;

        for (BillEntity b : bill) {
            BigDecimal billTotal = b.getTotalAmount();
            highestBillAmount = Math.max(highestBillAmount, billTotal.floatValue());
            total = total.add(billTotal);
            totalTransactions++;

            for (SaleItemEntity s : b.getSaleItems()) {
                totalItemSold += s.getQuantity();
            }
        }

        MonthlyReportResponse report = new MonthlyReportResponse();
        report.setDate(start);
        report.setTotalAmount(total);
        report.setTotalTransactions(totalTransactions);

        if (totalTransactions > 0) {
            report.setAverageBillAmount(
                    total.divide(BigDecimal.valueOf(totalTransactions), 2, RoundingMode.HALF_UP)
            );
        } else {
            report.setAverageBillAmount(BigDecimal.ZERO);
        }

        report.setTotalItemSold(totalItemSold);
        report.setHighestBillAmount(highestBillAmount);

        return report;
    }






}
