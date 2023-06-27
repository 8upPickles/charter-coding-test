package com.charter.chartercodingtest.controller;

import com.charter.chartercodingtest.model.Customer;
import com.charter.chartercodingtest.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/getAllCustomers", produces = "application/json")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customerList = new ArrayList<>();
            for (Customer customer : customerRepository.findAll()) {
                customerList.add(customer);
            }

            if (customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customerList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/updateCustomerRewardBalance/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, Customer customer) {
        try {
            Optional<Customer> customerData = customerRepository.findById(id);
            if (customerData.isPresent()) {
                Customer updatedCustData = customerData.get();
                calculateRewardTotal(updatedCustData);

                Customer custObj = customerRepository.save(updatedCustData);
                return new ResponseEntity<>(custObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/addCustomers", consumes = "application/json")
    public ResponseEntity<List<Customer>> addCustomers(@RequestBody List<Customer> customerList) {
        List<Customer> custList = customerRepository.saveAllAndFlush(customerList);

        return new ResponseEntity<>(custList, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCustomerById/{id}", produces = "application/json")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Customer calculateRewardTotal(Customer customer) {
        int reward = 0;
        double quotient = 0.0;

        double bal = customer.getMoneySpent();
        int balanceNum = (int) bal - 100;

        if (balanceNum > 0) reward = balanceNum*2;
        else reward = 0;

        quotient = bal / 100;
        reward += Math.round(quotient)*50;

        long oldRewardsTotal = customer.getRewardsTotal();
        customer.setRewardsTotal(reward + oldRewardsTotal);

        return customer;
    }
}
