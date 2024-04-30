package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.bean.Roles;

public interface IRolesRepo extends JpaRepository<Roles, Long> {

}
