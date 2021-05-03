package com.sl.ms.inventorymanagement.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sl.ms.inventorymanagement.model.Inventory;
import com.sl.ms.inventorymanagement.model.Product;
import com.sl.ms.inventorymanagement.repository.InventoryRepository;
import com.sl.ms.inventorymanagement.repository.ProductRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
public class InventoryController {

	private static Logger logger = LoggerFactory.getLogger(InventoryController.class);
	ProductRepository productRepo;
	InventoryRepository inventoryRepo;

	@RequestMapping(value = "/products/file", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public Inventory uploadInve(@RequestParam("file") MultipartFile file) {

		Date date = new Date();
		logger.info("Uploading Inventory file");
		Inventory inv;
		Set<Product> prodList = new HashSet<Product>();
		try {
			// JSON parser object to parse read file
			@SuppressWarnings("deprecation")
			JSONParser jsonParser = new JSONParser(file.getInputStream());
			Object obj = jsonParser.parse();
			JSONArray productList = (JSONArray) obj;
			logger.info("Product List:" + productList);

			productList.forEach(prd -> parseProductObject((JSONObject) prd, prodList));
			System.out.println("Data" + prodList);

			inv = new Inventory(date, productList);

		} catch (IOException | ParseException e) {
			inv = new Inventory();
			logger.error(e.getMessage());
		}
		inventoryRepo.save(inv);

		return inventoryRepo.save(inv);

	}

	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		logger.info("Inside GET products");
		return productRepo.findAll();
	}

	@GetMapping("/products/{id}")
	public Product getbyId(@PathVariable long id) {
		logger.info("Inside GET products by ID:" + id);
		return productRepo.findById(id).get();
	}

	@PostMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
	public Product saveById(@PathVariable long id, @RequestBody Product p) {
		p.setId(id);
		logger.info("Inside POST products by id:" + id);
		return productRepo.save(p);
	}

	@PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	public Iterable<Product> saveAll(@RequestBody Iterable<Product> p) {
		logger.info("Inside POST products");
		return productRepo.saveAll(p);
	}

	@PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
	public Product updateById(@PathVariable long id, @RequestBody Product p) {
		p.setId(id);
		logger.info("Inside PUT products by id:" + id);
		return productRepo.save(p);
	}

	@DeleteMapping(value = "/products/{id}")
	public void deleteById(@PathVariable Long id) {

		logger.info("Inside delete products by id:" + id);
		Product p = productRepo.findById(id).get();
		p.setQuantity(0);
		productRepo.save(p);
	}

	@GetMapping("/supported_products")
	public HashMap<Long, String> getSupportedProducts() {
		logger.info("Inside /supported_products ");
		HashMap<Long, String> uniqueMap = new HashMap<Long, String>();

		productRepo.findAll().forEach(p -> {
			uniqueMap.put(p.getId(), p.getName());
		});
		return uniqueMap;
	}

	private void parseProductObject(JSONObject prd, Set<Product> prodList) {

		Product p = new Product(((Integer) prd.get("id")).longValue(), (String) prd.get("name"),
				((Integer) prd.get("price")).doubleValue(), (Integer) prd.get("quantity"));
		prodList.add(p);
		productRepo.save(p);
	}

}
