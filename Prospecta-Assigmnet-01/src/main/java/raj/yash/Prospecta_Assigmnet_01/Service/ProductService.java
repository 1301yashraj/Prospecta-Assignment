package raj.yash.Prospecta_Assigmnet_01.Service;


import org.springframework.stereotype.Service;
import raj.yash.Prospecta_Assigmnet_01.DTO.ProductTO;

import java.util.List;

public interface ProductService {
     List<ProductTO> getProductsByCategory(String category);
     ProductTO addProduct(ProductTO product);
}
