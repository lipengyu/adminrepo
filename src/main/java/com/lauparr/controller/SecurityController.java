package com.lauparr.controller;

import com.lauparr.model.Customer;
import com.lauparr.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauparr on 14/11/2016.
 */
@RequestMapping("/api/security")
@RestController
public class SecurityController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Customer getCustomerByPassword() throws Exception {

        return repository.findByFirstName("Alice");
    }

}
