package com.dev.service.impl;

import com.dev.model.Seller;
import com.dev.model.SellerReport;
import com.dev.repository.SellerReportRepository;
import com.dev.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if(sr==null){
            SellerReport newReport = new SellerReport();
            newReport.setNetEarnings(seller.getId());
            return sellerReportRepository.save(newReport);
        }

        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
