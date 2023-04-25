package com.adria.audit.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



@Component
public class UserCredentialsImpl implements UserCredentials {

	@Override
	public String getUsername() {
		 if(getAuthentication()!=null)
		 {
			 UserDetails userDetails= (UserDetails)getAuthentication().getPrincipal();
			 return userDetails.getUsername();
		 }
		 else
		 {
			 return "Anonymous";
		 }
	}

	@Override
	public List<String> getRoles() {
		List<String> roleNames = new ArrayList<>();
		  if (getAuthentication() != null && getAuthentication().isAuthenticated()&&getAuthentication().getAuthorities() != null && !getAuthentication().getAuthorities().isEmpty())
		  {
			  List<GrantedAuthority> userRoles = (List<GrantedAuthority>) getAuthentication().getAuthorities();
			  for (GrantedAuthority role : userRoles) {
				  roleNames.add(role.getAuthority());
			  }
			  return roleNames;
	     }
		  return roleNames;
	}
	
	 private Authentication getAuthentication(){
		  
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 return auth;
	  
	  }
}
