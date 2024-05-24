package com.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orm.model.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

}
