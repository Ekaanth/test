package com.squapl.sa.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.squapl.sa.domain.Campaign;
import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Tag;
import com.squapl.sa.elasticrepository.CategoryElasticRepository;
import com.squapl.sa.jparepository.CampaignRepository;
import com.squapl.sa.jparepository.CategoryDao;
import com.squapl.sa.jparepository.TagDao;
import com.squapl.sa.service.TransactionService;
import com.squapl.sa.util.constants.CampaignStatus;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@PersistenceContext
    EntityManager entityManager;
	
	
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private TagDao tagDao;
    
    @Autowired
    ElasticsearchOperations operations;
    
    @Autowired
    CategoryElasticRepository categoryRepository;
    

    
    public List<Category> findCategoryList(Principal principal) {
        List<Category> categoryList = categoryDao.findAll();
        
        return categoryList;
    }

    public Category saveCategory(Category category) {

    	Category savedCategory = categoryDao.save(category);
    	
    	categoryRepository.save(savedCategory);
    	
    	return savedCategory;
    }

    public Category findCategoryByName(String categoryName) {
        return categoryDao.findByName(categoryName);
    }

    public void deleteCategoryByName(String categoryName) {
        categoryDao.deleteByName(categoryName);
    }
    
    public void deleteCategoryById(Long id) {
        categoryDao.delete(id);
    }
    
    
    public List<Category> findCategoryListforMe(Principal principal) {   
	String username = principal.getName();
    List<Category> categoryList = categoryDao.findAll().stream() 			//convert list to stream
            .filter(recipient -> username.equals(recipient))	//filters the line, equals to username
            .collect(Collectors.toList());

    return categoryList;
}
    
        

    public Category findCategoryById(Long id) {
        return categoryDao.findOne(id);
    }
    
    public List<Tag> findTagList() {
		 List<Tag> tagsList = (List<Tag>) tagDao.findAll();
		return tagsList;
	}

	public List<Campaign> findCampaignList(Principal principal) {
List<Campaign> categoryList = campaignRepository.findAll();
        
        return categoryList;
	}

	public List<Campaign> findstatus(CampaignStatus active) {

		CampaignStatus str = active;
		List<Campaign> categoryList = campaignRepository.findByStatus(str);
        
        return categoryList;
	}
	

    
}
