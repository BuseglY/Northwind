
package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController//bu class ın controller class ı olduğunu belirttik.
@RequestMapping("/api/products")//bu domain ile çağrılırsa bu class gelir.
@CrossOrigin//backendimizin tanımadığı kaynaklaro da desteklemesini sağlar(javascriptteki sayfamız için bunu yazdık) 
public class ProductsController {
	
	private ProductService productService;
	
	//Autowired, ProductService interface ini implements eden sınıfa gidip 
	//onu new'ler.Aynı zamanda o sınıfın constructor parametresindeki sınıfı da new ler.
	//ProductManager P=new ProductManager(new ProductDao);
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/getall")//kodlamaio/api/products/getall isteğinde bulunursak burası çalışır.
	public DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	@GetMapping("/getProductWithCategoryDetails")//kodlamaio/api/products/getall isteğinde bulunursak burası çalışır.
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
		return this.productService.getProductWithCategoryDetails();
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}
	
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName){
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId
	(@RequestParam ("productName")String productName,@RequestParam ("categoryId")int categoryId){
		return this.productService.getByProductNameAndCategoryId(productName, categoryId);
	}
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getAllMyPage")
	DataResult<List<Product>> getAll(int pageNo,int pageSize){
		return this.productService.getAll(pageNo,pageSize);
	}
	
	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted() {
		return this.productService.getAllSorted();
	}
	
	

}
