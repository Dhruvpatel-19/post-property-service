package com.example.postpropertyservice.service;



import com.example.postpropertyservice.entity.Category;
import com.example.postpropertyservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category updateCategory(int id , Category updatedCategory){

        if(!categoryRepository.existsById(id))
            return null;

        Category category = categoryRepository.findById(id).orElse(null);

        category.setCategory(updatedCategory.getCategory());

        return categoryRepository.save(category);
    }

    public String deleteCategory(int id){

        boolean isExist = categoryRepository.existsById(id);

        if(isExist){
            Category category = categoryRepository.findById(id).orElse(null);
            categoryRepository.deleteById(id);
            return "Category with name "+category.getCategory()+" deleted successfully";
        }
        else{
            return "Category with id "+id+" doesn't exist";
        }
    }
}
