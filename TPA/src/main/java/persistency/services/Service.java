package persistency.services;

import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.google.common.hash.Hashing;

public abstract class Service implements WithGlobalEntityManager, TransactionalOps {
	protected EntityManager em = entityManager();
	
	protected String hash(String clear) {
		return Hashing.sha256().hashString(clear, StandardCharsets.UTF_8).toString();
	}
}
