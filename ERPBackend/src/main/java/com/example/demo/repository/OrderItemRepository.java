package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderItem;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>, PagingAndSortingRepository<OrderItem, Integer> {

}
