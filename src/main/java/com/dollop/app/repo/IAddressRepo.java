package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.bean.Address;

public interface IAddressRepo extends JpaRepository<Address, Integer> {

}
