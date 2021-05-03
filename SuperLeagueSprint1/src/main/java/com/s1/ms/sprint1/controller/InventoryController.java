package com.s1.ms.sprint1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s1.ms.sprint1.h2.model.INVENTORY;
import com.s1.ms.sprint1.service.InventoryService;

@RestController
public class InventoryController {

	private static final String fileLocation = "./inventory.xlsx";
	
	static Consumer<INVENTORY>  c1= p -> System.out.println(p);

	@Autowired
	InventoryService inventoryService;

	@GetMapping("/process")
	public void triggerService() {
		save();
	}

	public List<INVENTORY> processFile() {
		List<INVENTORY> inventoryList = new ArrayList<INVENTORY>();
		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			int rowNumber = 0;
			while (itr.hasNext()) {
				Row currentRow = itr.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				INVENTORY inventory = new INVENTORY();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						inventory.setId((int) currentCell.getNumericCellValue());
						break;

					case 1:
						inventory.setItemName(currentCell.getStringCellValue());
						break;

					case 2:
						inventory.setPrice((Double) currentCell.getNumericCellValue());
						break;

					case 3:
						inventory.setQuantity((Double) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				inventoryList.add(inventory);
			}

			workbook.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inventoryList;
	}

	public void save() {
		try {
			List<INVENTORY> inventory = processFile();
			inventoryService.saveInventory(inventory);
			findAll();
			summaryItem(inventory, 1);
			SummaryPerDay(inventory);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public void findAll() {
		System.out.println(inventoryService.findAllInventory());
	}

	private static void summaryItem(List<INVENTORY> inventoryList, int itemId) {
		System.out.println("\nSummary of Single item with id:" + itemId);

		inventoryList.stream().filter(p -> p.getId() == itemId).forEach(System.out::println);
	}

	private static void SummaryPerDay(List<INVENTORY> inventoryList) {

		System.out.println("\nStock Summary per day:");
		inventoryList.forEach(c1);
	}
}
