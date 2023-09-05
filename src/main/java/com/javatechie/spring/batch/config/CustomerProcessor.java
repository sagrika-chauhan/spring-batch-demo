package com.javatechie.spring.batch.config;

import com.javatechie.spring.batch.entity.Customer;
import com.javatechie.spring.batch.entity.CustomerInfo;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<CustomerInfo,Customer> {

    @Override
    public Customer process(CustomerInfo customerInfo) throws Exception {
        if(customerInfo.getCountry().equals("United States")) {
            Customer customer = new Customer();
            customer.setEmail(customerInfo.getEmail());
            customer.setCountry(customerInfo.getCountry());
            customer.setContactNo(customerInfo.getContactNo());
            customer.setDob(customerInfo.getDob());
            customer.setId(customerInfo.getId());
            customer.setFirstName(customerInfo.getFirstName());
            customer.setGender(customerInfo.getGender());
            customer.setLastName(customerInfo.getLastName());
            return customer;
        }if(customerInfo.getCountry().equals("France")) {
            customerInfo.setEmail(customerInfo.getFirstName()+"@gmail.com");
            Customer customer = new Customer();
            //customer.setEmail(customerInfo.getEmail());
            customer.setEmail(customerInfo.getFirstName()+"@gmail.com");
            customer.setCountry(customerInfo.getCountry());
            customer.setContactNo(customerInfo.getContactNo());
            customer.setDob(customerInfo.getDob());
            customer.setId(customerInfo.getId());
            customer.setFirstName(customerInfo.getFirstName());
            customer.setGender(customerInfo.getGender());
            customer.setLastName(customerInfo.getLastName());
            return customer;
        }else{
            return null;
        }
    }
}
