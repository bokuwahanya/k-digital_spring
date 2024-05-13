//package com.rubypaper;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
////import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
////import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
////import org.springframework.test.web.servlet.MockMvc;
//
//import com.rubypaper.domain.BoardVO;
//
//import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public final class BoardControllerTest {
//	
//	private TestRestTemplate restTemplate;
//	
//	@Test
//	public void testHello() throws Exception {
//		BoardVO board = 
//				restTemplate.getForObject("/getBoard", BoardVO.class);
//		assertEquals("테스터", board.getWriter());
//		System.out.println(board);
////		mockMvc.perform(get("/hello").param("name","둘리"))
////		.andExpect(status().isOk())
////		.andExpect(content().string("Hello : 둘리"))
////		.andDo(print());
//	}
//}
