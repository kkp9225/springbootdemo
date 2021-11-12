package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dto.Stock;
import com.bank.entity.StockEntity;
import com.bank.exception.InvalidStockIdException;
import com.bank.repo.StockRepo;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepo stockRepo;
	
	@Override
	public List<Stock> getAllStocks() {
		List<StockEntity> stockEntityList = stockRepo.findAll();
		List<Stock> stockList = new ArrayList<>();
		for(StockEntity stockEntity: stockEntityList) {
			Stock stock = 
					new Stock(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarketName(), stockEntity.getPrice());
			stockList.add(stock);
		}
		return stockList;
	}

	@Override
	public Stock getStockById(int stockId) {
		Optional<StockEntity> opStockEntity = stockRepo.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockEntity stockEntity = opStockEntity.get();
			Stock stock = 
					new Stock(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarketName(), stockEntity.getPrice());
			return stock;
		}
		throw new InvalidStockIdException(""+stockId);
	}

	@Override
	public Stock createNewStock(Stock stock, String authToken) {
		StockEntity stockEntity = 
				new StockEntity(stock.getName(), stock.getMarketName(), stock.getPrice());
		stockEntity = stockRepo.save(stockEntity);
		stock = new Stock(stockEntity.getId(), stockEntity.getName(), stockEntity.getMarketName(), stockEntity.getPrice());
		return stock;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		if(stockRepo.existsById(stockId)) {
			stockRepo.deleteById(stockId);
			return true;
		}
		throw new InvalidStockIdException(""+stockId);
	}

	@Override
	public Stock updateStockById(int stockId, Stock stock) {
		Optional<StockEntity> opStockEntity = stockRepo.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockEntity stockEntity = opStockEntity.get();
			stockEntity.setName(stock.getName());
			stockEntity.setMarketName(stock.getMarketName());
			stockEntity.setPrice(stock.getPrice());
			stockEntity = stockRepo.save(stockEntity);
			stock.setId(stockId);
			return stock;
		}
		throw new InvalidStockIdException(""+stockId);
	}

}
