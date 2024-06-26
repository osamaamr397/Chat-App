package com.amr.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatApplicationTests {

	@Autowired
	ChatApplication chatApplication;
	
	@Test
	void contextLoads() {
		assert(chatApplication != null);
		
	}

}
