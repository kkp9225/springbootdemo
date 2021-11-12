package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.entity.StockEntity;

public interface StockRepo extends JpaRepository<StockEntity, Integer>{

}
