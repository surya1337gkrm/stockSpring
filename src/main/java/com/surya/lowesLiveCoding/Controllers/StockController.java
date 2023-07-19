package com.surya.lowesLiveCoding.Controllers;

import com.surya.lowesLiveCoding.DTO.ResponseDTO;
import com.surya.lowesLiveCoding.DTO.StockDTO;
import com.surya.lowesLiveCoding.Models.Stock;
import com.surya.lowesLiveCoding.Repositories.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/stock",consumes = {"application/json"},produces = {"application/json"})
public class StockController {
    @Autowired
    private StockRepository stockRepo;

    @GetMapping("get/{stockId}")
    public ResponseEntity<Stock> getStock(@PathVariable int stockId){
        Optional<Stock> s= stockRepo.findById(stockId);
        if(s.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(s.get(),HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<ResponseDTO> saveStock(@Valid @RequestBody StockDTO stockDto){
        Stock st = new Stock(stockDto.getStockTicker(),stockDto.getStockName(),stockDto.getQuantityPurchased(),stockDto.getAmountSpent(),stockDto.getCountry());
        try{
            stockRepo.save(st);
            return new ResponseEntity<>(new ResponseDTO("Successfully Processed the Transaction"), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(new ResponseDTO("Not Able to Process the Transaction"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<Integer> getCountryStockPurchase(@RequestParam String country){
//        List<Stock> li = stockRepo.findByCountry(country);
        List<Stock> li = stockRepo.findAll().stream().filter(s->s.getCountry().equals(country)).toList();

//       li.forEach((s)->{total+s.getQuantityPurchased()});
//        for (Stock stock : li) {
//            total += stock.getQuantityPurchased();
//        }
        Integer total=li.stream().reduce(0,(sum,t)->sum+t.getQuantityPurchased(),Integer::sum);
        return new ResponseEntity<>(total,HttpStatus.OK);
    }
}
