package com.squapl.sa.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.squapl.sa.domain.Campaign;
import com.squapl.sa.elasticrepository.CategoryElasticRepository;
import com.squapl.sa.jparepository.CampaignRepository;
import com.squapl.sa.jparepository.CampaignRepositoryCustom;
import com.squapl.sa.service.CampaignService;

@Service
public class CampaignRepositoryImpl implements CampaignService, CampaignRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Autowired
	private CampaignRepository campaignRepository;
    
    @Autowired
    ElasticsearchOperations operations;
    
    @Autowired
    CategoryElasticRepository categoryRepository;
    
        
    public List<Campaign> findCampaignList(Principal principal) {
        List<Campaign> campaignList = campaignRepository.findAll();
        
        return campaignList;
    }

    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }   

    public Campaign findCampaignByName(String campaignName) {
        return campaignRepository.findByName(campaignName);
    }

    public void deleteCampaignByName(String campaignName) {
        campaignRepository.deleteByName(campaignName);
    }    

    
    @Override
    public List<Campaign> findActiveCampaignbyUser(String priority) {
    	
        Query query = entityManager.createNativeQuery("select * from Campaign c where c.priority = ? and c.status='ACTIVE'", Campaign.class);
        query.setParameter(1, priority);
        
 
        return query.getResultList();
    }
    
}
