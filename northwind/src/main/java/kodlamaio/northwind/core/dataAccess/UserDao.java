package kodlamaio.northwind.core.dataAccess;

//core katmanı bütün projelerimizde kullanabileceğimiz sınıfları yazdığımız katmandır.
import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
