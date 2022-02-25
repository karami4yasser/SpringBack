package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Custumer;

public interface CustumerRepository extends JpaRepository<Custumer, String> {

}
