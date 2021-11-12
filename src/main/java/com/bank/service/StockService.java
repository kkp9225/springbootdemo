package com.bank.service;

import java.util.List;

import com.bank.dto.Stock;

public interface StockService {
	List<Stock> getAllStocks();
	Stock getStockById(int stockId);
	Stock createNewStock(Stock stock, String authToken);
	boolean deleteStockById(int stockId);
	Stock updateStockById(int stockId, Stock stock);
}
