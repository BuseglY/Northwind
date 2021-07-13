package kodlamaio.northwind.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//core katmanı sadece northwindte değil diğer projelerde de kullanabilmemiz içindir
@Data//Anatasyon yazabilmemiz için gereklidir.(getter ve setter oluşumlarını sağlar)
@Entity//veri tabanı nesnesidir.
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email//bu değişkenimizin Email formatında olacağını belirttik.
	@NotBlank//bu değişkene aktarılan datanın ""(çift tırnak)içerisinin boş olmamasını belirttik.
	@NotNull//bu değişken için gelen datanın boş olmamasını belirttik.
	private String email;
	
	@Column(name="password")
	@NotBlank
	@NotNull
	private String password;
	

}
