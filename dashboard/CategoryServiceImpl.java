package com.squapl.sa.service.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squapl.sa.domain.Category;
import com.squapl.sa.jparepository.CategoryDao;
import com.squapl.sa.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao; 
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}
