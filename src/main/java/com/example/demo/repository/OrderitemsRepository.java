package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.orderitems;

public interface OrderitemsRepository extends JpaRepository<orderitems,Long> {

}
