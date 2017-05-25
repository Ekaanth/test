package com.squapl.sa.web.api;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.squapl.sa.domain.Article;
import com.squapl.sa.domain.Campaign;
import com.squapl.sa.domain.Category;
import com.squapl.sa.domain.Client;
import com.squapl.sa.domain.Tag;
import com.squapl.sa.domain.User;
import com.squapl.sa.domain.security.UserRole;
import com.squapl.sa.jparepository.RoleDao;
import com.squapl.sa.jparepository.TagDao;
import com.squapl.sa.service.ArticleService;
//import com.userfront.domain.PrimaryAccount;
//import com.userfront.domain.SavingsAccount;
//import com.userfront.domain.User;
//import com.squapl.sa.domain.security.UserRole;
import com.squapl.sa.service.UserService;
import com.squapl.sa.service.UserServiceImpl.ArticleServiceImpl;
import com.squapl.sa.service.UserServiceImpl.CategoryServiceImpl;
import com.squapl.sa.service.UserServiceImpl.ClientServiceImpl;
import com.squapl.sa.service.UserServiceImpl.TransactionServiceImpl;
import com.squapl.sa.service.UserServiceImpl.UserServiceImpl;


@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleDao roleDao;
	
	@Autowired
   private TransactionServiceImpl transactionServiceImp;
	
	
	@Autowired
	ArticleServiceImpl articleServiceImp;
	

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user,  Model model) {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
        	 Set<UserRole> userRoles = new HashSet<>();
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
        	
        	userService.save(user);

            return "redirect:/";
        }
    }
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        
        System.out.println("TEST:: "  + user);
        System.out.println("TEST:: "  + user.getUsername());
        
//        PrimaryAccount primaryAccount = user.getPrimaryAccount();
//        SavingsAccount savingsAccount = user.getSavingsAccount();
//
//        model.addAttribute("primaryAccount", primaryAccount);
//        model.addAttribute("savingsAccount", savingsAccount);
        
        List<Article> totalapproved = articleService.findByStatus("approved");
		model.addAttribute("totalApprovedArticle", totalapproved.size());
//    
//		List<Article> approvedEnglish = articleService.findByStatusLanguage("English","approved");
//		model.addAttribute("approvedEnglish", approvedEnglish.size());
//		
//		List<Article> approvedTamil = articleService.findByStatusLanguage("Tamil","approved");
//		model.addAttribute("approvedTamil", approvedTamil.size());
//		
		
        List<Article> totalpending = articleService.findByStatus("Pending");
		model.addAttribute("totalPendingArticle", totalpending.size());
//		
//
//		List<Article> pendingEnglish = articleService.findByStatusLanguage("English","Pending");
//		model.addAttribute("pendingEnglish", pendingEnglish.size());
//		
//
//		List<Article> pendingTamil = articleService.findByStatusLanguage("Tamil","Pending");
//		model.addAttribute("pendingTamil", pendingTamil.size());
//		
        //
		

		 List<Campaign> totalcampaign =  transactionServiceImp.findCampaignList(principal);
		 model.addAttribute("totalcampaign", totalcampaign.size());
		 
		 List<Campaign> activecampaign =  transactionServiceImp.findstatus("Active");
		 model.addAttribute("activecampaign", activecampaign.size());
		 
		 List<Campaign> pendingcampaign =  transactionServiceImp.findstatus("Pending");
		 model.addAttribute("pendingcampaign", pendingcampaign.size());
		 
		 List<Campaign> onholdcampaign =  transactionServiceImp.findstatus("Onhold");
		 model.addAttribute("onholdcampaign", onholdcampaign.size());
		 
		 //
		 
			List<Category> totalcat =  categoryServiceImpl.findAll();
			 model.addAttribute("totalcategory", totalcat.size());
			 
			 List<Tag> totaltags =  tagDao.findAll();
			 model.addAttribute("totaltags", totaltags.size());
			 
			 
			//
			 
			 List<Client> totalnumber = clientServiceImpl.findAll();
			 model.addAttribute("totalnumber", totalnumber.size());
			
			 List<Client> activeclients = clientServiceImpl.findByStatus("Active");
			 model.addAttribute("activeclients", activeclients.size());
			
			 List<Client> onholdclients = clientServiceImpl.findByStatus("Onhold");
			 model.addAttribute("onholdclients", onholdclients.size());
			
        //
			 
			 
			// UserServiceImpl

			 List<User> totaluser = userServiceImpl.findUserList();
			 model.addAttribute("totaluser", totaluser.size());
			 
			 
        return "userFront";
    }
	
	@RequestMapping("/userFront/campaign/{status}")
	public String campaign(@PathVariable("status") String status, Principal principal, Model model) {
		
		model.addAttribute("campaignname", status);
		
		 List<Campaign> statuscampaign =  transactionServiceImp.findstatus(status);
		 model.addAttribute("statuscampaign", statuscampaign);
		 
		 
		
		return "campaignlist";
	}
	
	@RequestMapping("/userFront/client/{status}")
	public String client(@PathVariable("status") String status, Principal principal, Model model) {
		
		model.addAttribute("clientname", status);
		
		List<Client> statusclients = clientServiceImpl.findByStatus(status);
		 model.addAttribute("statusclients", statusclients);
		 
		 
		
		return "clientlist";
	}
	
}
