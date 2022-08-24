package com.example.postpropertyservice.controller;

import com.example.postpropertyservice.entity.Category;
import com.example.postpropertyservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postService/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/addCategory")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }


    @GetMapping(value = "/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable("id") int id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping(value = "/getAllCategory")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PutMapping(value = "/updateCategory/{id}")
    public Category updateCategory(@PathVariable("id") int id ,@RequestBody Category category){
        return categoryService.updateCategory(id , category);
    }

    @DeleteMapping(value = "/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") int id){
        return categoryService.deleteCategory(id);
    }
}
