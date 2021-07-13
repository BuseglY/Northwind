package kodlamaio.northwind.entities.dtos;

//DTO(Data Transformation object-Veri Transfer Objesi)
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//arka planda lombok tarafından getter ve setterları oluşturur
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDto {

	private int id;
	private String productName;
	private String categoryName;
}
