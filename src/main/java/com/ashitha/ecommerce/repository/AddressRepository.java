package com.ashitha.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashitha.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
