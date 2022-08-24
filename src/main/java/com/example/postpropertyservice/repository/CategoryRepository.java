package com.example.postpropertyservice.repository;


import com.example.postpropertyservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCategory(String category);

    Category findByCategory(String category);
}
