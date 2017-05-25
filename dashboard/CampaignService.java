package com.squapl.sa.service;

import java.security.Principal;
import java.util.List;

import com.squapl.sa.domain.Campaign;


public interface CampaignService {
	
    
    List<Campaign> findCampaignList(Principal principal);

    Campaign saveCampaign(Campaign campaign);

    Campaign findCampaignByName(String campaignName);
    
    void deleteCampaignByName(String campaignName);
    
    List<Campaign> findActiveCampaignbyUser(String priority);
    
}
