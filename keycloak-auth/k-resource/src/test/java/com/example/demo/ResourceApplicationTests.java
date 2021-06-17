package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceApplicationTests {
//	@LocalServerPort
//	private int port;

	@Test
	public void contextLoads() throws Exception {
//		System.out.println(port);
//		mvc.perform(MockMvcRequestBuilders.get("/"));
	}

}
