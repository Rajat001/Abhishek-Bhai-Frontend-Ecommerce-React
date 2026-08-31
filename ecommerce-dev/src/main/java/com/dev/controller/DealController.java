package com.dev.controller;


import com.dev.model.Deal;
import com.dev.response.ApiResponse;
import com.dev.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")

public class DealController {

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeals(
            @RequestBody Deal deals
    ){
        Deal createDeals = dealService.createDeal(deals);
        return new ResponseEntity<>(createDeals , HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception{
        Deal udpateDeal = dealService.updateDeal(deal , id);
        return ResponseEntity.ok(udpateDeal);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeals(
            @PathVariable Long id
    ) throws Exception{

        dealService.deleteDeal(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deal deleted");
//        apiResponse.setStatus(true);

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

}
