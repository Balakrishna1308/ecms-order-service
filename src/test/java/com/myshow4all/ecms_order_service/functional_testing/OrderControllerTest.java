//package com.myshow4all.ecms_order_service.functional_testing;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.hamcrest.Matchers.hasSize;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class OrderControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testCreateOrderWithItemsEndpoint() throws Exception {
//        // JSON body for the order request
//        String orderJson = "{ \"userId\": 123, \"status\": \"PENDING\", \"orderItems\": [ " +
//                "{ \"productName\": \"Product 1\", \"quantity\": 2, \"price\": 10.0 }, " +
//                "{ \"productName\": \"Product 2\", \"quantity\": 1, \"price\": 20.0 } ] }";
//
//        // Perform a POST request to the "/api/orders/create-with-items" endpoint
//        mockMvc.perform(post("/api/orders/create-with-items")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(orderJson))
//                .andExpect(status().isCreated()) // Assert HTTP status 201 Created
//                .andExpect(jsonPath("$.orderItems", hasSize(2))) // Assert two items in the response
//                .andExpect(jsonPath("$.status").value("PENDING")); // Assert order status is "PENDING"
//    }
//}
