package com.cts.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Stock;
import com.cts.assignment.service.StockService;

@RestController
public class StockController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
	private StockService stockService;

	@Autowired
	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

//	@CachePut(value = "stocks", key = "#stock.stockid")
	@PostMapping("/stock/register")
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		try {
			stockService.registerStock(stock);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<Stock>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
	}

//	@CachePut(value = "stocks", key = "#stock.stockid")
	@PutMapping("/stock/{stockid}")
	public ResponseEntity<Stock> updateStock(@PathVariable String stockid, @RequestBody Stock stock) {
		try {
			stock = stockService.updateStock(stockid, stock);
			if (stock == null) {
				return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Stock>(stock, HttpStatus.OK);
			}
		} catch (StockNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}

//	@CacheEvict(value = "stocks", allEntries = true)
	@DeleteMapping("/stock/{stockid}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String stockid) {
		try {
			boolean flag = stockService.deleteStock(stockid);
			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (StockNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/stock/{stockid}")
	public ResponseEntity<Stock> getStockById(@PathVariable String stockid) {
		try {
			Stock stock = stockService.getStockById(stockid);
			LOGGER.info("Getting stock with ID {}.", stockid);
			if (stock == null) {
				return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Stock>(stock, HttpStatus.OK);
			}

		} catch (StockNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
		}
	}

}
