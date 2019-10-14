package persistency.services;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.usuario.Usuario;

public class LoginService implements WithGlobalEntityManager {
	EntityManager em = entityManager();
	
	public Boolean usuarioValido(String username, String password) {
		Usuario us = em.createQuery("from Usuario where Usuario.username="+username,Usuario.class).getSingleResult();
		if(us==null)
			return false;
		return us.getPassword() == password;
	}
}
