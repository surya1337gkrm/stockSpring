package com.surya.lowesLiveCoding.Repositories;

import com.surya.lowesLiveCoding.Models.Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock,Integer> {
    List<Stock> findByCountry(String country);
}
