package com.squapl.sa.service;

import java.security.Principal;
import java.util.List;


import com.squapl.sa.domain.Category;

import com.squapl.sa.domain.Tag;


public interface TransactionService {
	
    
    
    

    
    List<Category> findCategoryList(Principal principal);

    Category saveCategory(Category category);

    Category findCategoryByName(String categoryName);
    
    void deleteCategoryByName(String categoryName);
    void deleteCategoryById(Long id);
    
    List<Category> findCategoryListforMe(Principal principal);
    
    Category findCategoryById(Long id);

    
    List<Tag> findTagList();
    
    
}
