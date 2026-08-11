package com.dev.repository;

import com.dev.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long> {

    Category findByCategoryId(String categoryId);

}
