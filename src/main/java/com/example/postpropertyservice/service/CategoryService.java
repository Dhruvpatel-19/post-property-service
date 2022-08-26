package com.example.postpropertyservice.service;



import com.example.postpropertyservice.entity.Category;
import com.example.postpropertyservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Category addCategory(Category category){
        HttpEntity<Category> categoryObj = new HttpEntity<>(category);
        restTemplate.postForEntity("http://localhost:8081/callGuestService/addCategory" , categoryObj , String.class);
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

        HttpEntity<Category> categoryObj = new HttpEntity<>(category);
        restTemplate.exchange("http://localhost:8081/callGuestService/updateCategory/"+id , HttpMethod.PUT , categoryObj , String.class);

        return categoryRepository.save(category);
    }

    public String deleteCategory(int id){

        boolean isExist = categoryRepository.existsById(id);

        if(isExist){
            Category category = categoryRepository.findById(id).orElse(null);
            categoryRepository.deleteById(id);
            restTemplate.delete("http://localhost:8081/callGuestService/deleteCategory/"+id);
            return "Category with name "+category.getCategory()+" deleted successfully";
        }
        else{
            return "Category with id "+id+" doesn't exist";
        }
    }
}
