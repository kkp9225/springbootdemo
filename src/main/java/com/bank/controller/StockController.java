package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.Stock;
import com.bank.exception.InvalidStockIdException;
import com.bank.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mymarketplace")
public class StockController {

	@Autowired
	StockService stockService;
	
	@ExceptionHandler(InvalidStockIdException.class)
	public ResponseEntity<String> handleInvalidStockIdException(InvalidStockIdException invalidStockIdException) {
		return new ResponseEntity<String>("Local handler " + invalidStockIdException.toString(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	@GetMapping(value="/stock", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Returns all stocks", notes = "This service returns all stocks situated in the market")
	public List<Stock> getAllStocks() {
		return stockService.getAllStocks();
	}
	
	@GetMapping(value="/stock/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Returns stock by Id", notes = "This service returns stock by id situated in the market")
	public Stock getStockById(@ApiParam(value = "Stock Identifier", required=true)  @PathVariable("id") int stockId) {
		return stockService.getStockById(stockId);
	}
	
	@PostMapping(value="/stock", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Stock> createNewStock(@RequestBody Stock stock, @RequestHeader("auth-token") String authToken) {
		Stock newStock = stockService.createNewStock(stock, authToken);
		return new ResponseEntity<Stock>(newStock, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/stock/{id}")
	public ResponseEntity<Boolean> deleteStockById(@PathVariable("id") int stockId) {
		return new ResponseEntity<Boolean>(stockService.deleteStockById(stockId), HttpStatus.OK);
	}

	@PutMapping(value="/stock/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Stock> updateStockById(@PathVariable("id") int stockId, @RequestBody Stock stock) {
		return new ResponseEntity<Stock>(stockService.updateStockById(stockId, stock), HttpStatus.OK);
	}
}





