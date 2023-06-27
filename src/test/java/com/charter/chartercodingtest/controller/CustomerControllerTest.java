package com.charter.chartercodingtest.controller;

import com.charter.chartercodingtest.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCustomerById() throws MalformedURLException {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/getCustomerById/1").toString(), String.class);

        String expected = "{\"id\":1,\"name\":\"Randy\",\"moneySpent\":100.0,\"rewardsTotal\":10}";
        assertEquals(expected, response.getBody());
    }

    @Test
    void testUpdateCustomerRewardsById() throws MalformedURLException {
        Customer customer = new Customer("Tom", 310.0, 0);

        ResponseEntity<String> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/updateCustomerRewardBalance/2").toString(), customer, String.class);

        String expected = "{\"id\":2,\"name\":\"Tom\",\"moneySpent\":310.0,\"rewardsTotal\":570}";
        assertEquals(expected, response.getBody());
    }

    @Test
    void testGetAllCustomers() throws MalformedURLException {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/getAllCustomers").toString(), String.class);

        String expected = getExpectedString();
        assertEquals(expected, response.getBody());
    }

    private String getExpectedString() {

        return
            "[{\"id\":1,\"name\":\"Randy\",\"moneySpent\":100.0,\"rewardsTotal\":10}," +
            "{\"id\":2,\"name\":\"Tom\",\"moneySpent\":310.0,\"rewardsTotal\":570}," +
            "{\"id\":3,\"name\":\"Sarah\",\"moneySpent\":250.0,\"rewardsTotal\":20}," +
            "{\"id\":4,\"name\":\"Maddie\",\"moneySpent\":99.0,\"rewardsTotal\":0}," +
            "{\"id\":5,\"name\":\"Brad\",\"moneySpent\":240.0,\"rewardsTotal\":10}]";
    }

}