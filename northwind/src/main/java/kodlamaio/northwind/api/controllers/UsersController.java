package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping(value="/api/users")
public class UsersController {

	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	//RequestBody : kullanıcıdan post işlemiyle aldığımız nesneyi belirtir.
	@PostMapping(value="/add")//PostMapping : kullanıcının bir data vereceğini belirtir.
	public ResponseEntity<?> add(@Valid @RequestBody User user) {//işlemin hatalı ya da başarılı döneceğini bilmediğimiz için <?> koyduk
	//@Valid : kullanıcının girdiği user nesnesini validasyondan geçirir.
		return ResponseEntity.ok(this.userService.add(user));//ResponseEntity.ok : işlem oldu demektir(swagger da 200 ü temsil eder)
	}
	
	
	//Bütün metodlarımızı buradan geçireceğiz.Sebebi, hata kontrolü yapmamız gerektiği içindir.
	@ExceptionHandler(MethodArgumentNotValidException.class)//Bu sistemde hata olursa yönlendirmeyi bu metoda yapacaktır.MethodArgumentNotValidException validasyon(doğrulama)hatalarını temsil eder.Sonuna .class diyerek tipini belirtmiş olduk
	@ResponseStatus(HttpStatus.BAD_REQUEST)//Bu methodu default olarak BAD_REQUEST olarak döndürdük(yani 500 hatası)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors=new HashMap<String,String>();//Map,diğer dillerde dictionary yapısı gibidir.<String,String> ise hatalı nesne ilk string tir(mesela email alanı), ikinci String ise hata çıktısıdır(örneğin email alanı uygun değil).
		//HashMap bir Map implementasyonudur.Ve new lenerek gelir.
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){//alanlar için oluşan tüm hataları okuyabilmek için foreach yaptık
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors=new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları");//validasyonu ErrorDataResult a çevirdik.
		return errors;
	}
	
	//Swagger da get işlemleri için 200 kullanılır.Add işlemi için örneğin 201 i kullanırız.
	//Genellikle 200,400 ve 500 leri kullanırız.400 ve 500 ler bize hatayı anlatır.
	
	
}
