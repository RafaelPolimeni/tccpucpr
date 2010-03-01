package br.com.security;

import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl {
	public AppUser findUser(String userName) {
		AppUser appUser = null;
		Set<String> roles = new HashSet<String>();
		
		if (userName.equals("john")) {
			roles.add("ROLE_URLACCESS");
			appUser = new AppUser("john", "John", "Turner", "john", roles);
		} else if (userName.equals("jim")) {
			appUser = new AppUser("jim", "Jim", "Daniel", "jim", roles);
		} else if (userName.equals("tina")) {
			roles.add("ROLE_ALLACCESS");
			appUser = new AppUser("tina", "Tina", "Joseph", "tina", roles);
		} else if (userName.equals("victor")) {
			roles.add("ROLE_ALLACCESS");
			appUser = new AppUser("tina", "Tina", "Joseph", "tina", roles);
		}
		
		return appUser;
	}
}