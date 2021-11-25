package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}