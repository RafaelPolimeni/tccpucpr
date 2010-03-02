package br.com.security;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import br.com.model.User;

public class UserDaoImpl {
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public User findUser(String userName) {
		// Set<String> roles = new HashSet<String>();

		// if (userName.equals("john")) {
		// roles.add("ROLE_URLACCESS");
		// appUser = new AppUser("john", "John", "Turner", "john", roles);
		// } else if (userName.equals("jim")) {
		// appUser = new AppUser("jim", "Jim", "Daniel", "jim", roles);
		// } else if (userName.equals("tina")) {
		// roles.add("ROLE_ALLACCESS");
		// appUser = new AppUser("tina", "Tina", "Joseph", "tina", roles);
		// } else if (userName.equals("victor")) {
		// roles.add("ROLE_ALLACCESS");
		// appUser = new AppUser("tina", "Tina", "Joseph", "tina", roles);
		// }

		List users = hibernateTemplate.find("from User where userName = " + userName);
		// User user = new User();
		// user.setFirstName("Victor");
		// user.setLastName("Salvalagio");
		// user.setPassword("senha");
		return (User) users.get(0);
	}
}