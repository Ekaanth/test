package com.squapl.sa.service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import com.squapl.sa.domain.User;
import com.squapl.sa.domain.security.UserRole;
//import com.squapl.sa.domain.security.UserRole;

public interface UserService {
	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);

	List<User> findList(Principal principal);
}
