package com.sb.curd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sb.curd.exception.ResourceNotFoundException;
import com.sb.curd.model.Customer;
import com.sb.curd.repository.CustomerRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CurdController {

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/customer")
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@PostMapping("/customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") Long customerId, @Valid @RequestBody Customer customerDetails) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
		customer.setName(customerDetails.getName());
		customer.setAddress(customerDetails.getAddress());
		customer.setCity(customerDetails.getCity());
		customer.setCountry(customerDetails.getCountry());
		Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Curd", "id", customerId));
		customerRepository.delete(customer);
		return ResponseEntity.ok().build();
	}

}
