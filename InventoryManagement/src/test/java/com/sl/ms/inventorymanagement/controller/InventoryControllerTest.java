package com.sl.ms.inventorymanagement.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.inventorymanagement.model.Inventory;
import com.sl.ms.inventorymanagement.model.Product;
import com.sl.ms.inventorymanagement.repository.InventoryRepository;
import com.sl.ms.inventorymanagement.repository.ProductRepository;

import net.minidev.json.JSONArray;

@WebMvcTest(InventoryControllerTest.class)
public class InventoryControllerTest {

	@MockBean
	ProductRepository pr;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	InventoryRepository ir;
	Product p = new Product((long) 1, "Product1", 12.50, 12);
	Inventory i = new Inventory(new Date("01/05/2021"), new JSONArray());
	List<Product> prds = new ArrayList<Product>();

	@Test
	void shouldUploadInventory() {
		Mockito.when(ir.save(Mockito.any())).thenReturn(i);
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
				"[{ \"id\":2,\"name\":\"Product20\",\"price\": 400,\"quantity\":4},{\"id\":3,\"name\":\"Product12\",\"price\": 14,\"quantity\":1}]"
						.getBytes()
//"s".getBytes()
		);
		try {
			this.mockMvc.perform(multipart("/products/file")// .contentType("multipart/form-data")
					.file(file)).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void shouldReturnAllProducts() throws Exception {
		prds.add(p);
		Mockito.when(pr.findAll()).thenReturn(prds);

		System.out.println("get:" + pr.findById((long) 1));
		this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(containsString("Product1")));

	}

	@Test
	public void shouldReturnGivenOrder() throws Exception {

		Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(p));
		this.mockMvc.perform(get("/products/1")).andDo(print()).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().string(containsString("Product1")));
	}

	@Test
	public void shouldAddNewOrder() throws Exception {

		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		Mockito.when(pr.save(Mockito.any())).thenReturn(prd);

		this.mockMvc.perform(post("/products/3").contentType("application/json").content(asJsonString(prd)))
				.andExpect(status().isOk()).andExpect((ResultMatcher) content().string(containsString("Product3")));
	}

	@Test
	public void shouldPutNewProduct() throws Exception {

		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		Mockito.when(pr.save(Mockito.any())).thenReturn(prd);

		this.mockMvc.perform(put("/products/3").contentType("application/json").content(asJsonString(prd)))
				.andExpect(status().isOk()).andExpect((ResultMatcher) content().string(containsString("Product3")));
	}

	@Test
	public void shouldPostAllProducts() throws Exception {
		prds.add(p);
		Product prd = new Product((long) 3, "Product3", (double) 100, 2);
		prds.add(prd);
		Mockito.when(pr.saveAll(Mockito.any())).thenReturn(prds);

		this.mockMvc.perform(post("/products").contentType("application/json").content(asJsonString(prds)))
				.andExpect(status().isOk()).andExpect((ResultMatcher) content().string(containsString("Product3")));
	}


	@Test
	public void shouldDeleteGivenOrder() throws Exception {
		Mockito.when(pr.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(p));
		doNothing().when(pr).deleteById(Mockito.anyLong());
		this.mockMvc.perform(delete("/products/1")).andDo(print()).andExpect(status().isOk());
	}

}
