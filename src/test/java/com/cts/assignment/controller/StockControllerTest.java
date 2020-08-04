package com.cts.assignment.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.cts.assignment.exception.StockAlreadyExistsException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Stock;
import com.cts.assignment.service.StockServiceImpl;

public class StockControllerTest {
	@Mock
	private StockServiceImpl stockService;
	private Stock stock;
	@InjectMocks
	private StockController stockController;
	private List<Stock> stockList = null;
	private Optional<Stock> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		stock = new Stock();
		stock.setCostprice(70);
		stock.setCount(10);
		stock.setDescription("Microsoft mouse");
		stock.setName("Mouse");
		stock.setSellingprice(100);
		stock.setStockid("s001");

		stockList = new ArrayList<>();
		stockList.add(stock);
		options = Optional.of(stock);
	}

	@Test
	public void testCreateStock() throws StockAlreadyExistsException {
		when(stockService.registerStock(stock)).thenReturn(stock);
		ResponseEntity<Stock> stockSaved = stockController.createStock(stock);
		assertNotNull(stockSaved);
	}

	@Test
	public void testUpdateStock() throws StockNotFoundException {
		when(stockService.updateStock(stock.getStockid(), stock)).thenReturn(stock);
		ResponseEntity<Stock> stockSaved = stockController.updateStock(stock.getStockid(), stock);
		assertNotNull(stockSaved);
	}

	@Test
	public void testDeleteProduct() throws StockNotFoundException {
		when(stockService.deleteStock(stock.getStockid())).thenReturn(true);
		stockController.deleteProduct(stock.getStockid());
		assertTrue(true);
	}

	@Test
	public void testGetStockById() throws StockNotFoundException {
		when(stockService.getStockById(stock.getStockid())).thenReturn(stock);
		ResponseEntity<Stock> stockSaved = stockController.getStockById(stock.getStockid());
		assertNotNull(stockSaved);
	}

}
