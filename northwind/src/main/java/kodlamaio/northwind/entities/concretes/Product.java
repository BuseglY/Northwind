package kodlamaio.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity //veri tabanı nesnesi olduğunu belirttik.
@Table(name="products")//hangi tabloya denk geldiğini belirttik.
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id//Id annotation ın id değişkeni olduğunu belirttik.
	@GeneratedValue(strategy=GenerationType.IDENTITY)//id nin 1 er 1 er arttırılacağını belirttik
	@Column(name="product_id")
	private int id;
	
	//@Column(name="category_id")
	//private int categoryId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="units_in_stock")
	private short unitsInStock;
	
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	
	@ManyToOne()//bir category_id nin birden fazla product ı olabilir.
	@JoinColumn(name="category_id")
	private Category category;//category de category_id olduğu için yukarıdaki @Column category_id yi silebiliriz.

}