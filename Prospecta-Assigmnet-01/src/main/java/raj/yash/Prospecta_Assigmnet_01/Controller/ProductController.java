package raj.yash.Prospecta_Assigmnet_01.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raj.yash.Prospecta_Assigmnet_01.DTO.ProductTO;
import raj.yash.Prospecta_Assigmnet_01.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("categoryName") String category){
          List<ProductTO> products =  this.productService.getProductsByCategory(category);
          return ResponseEntity.ok().body(products);
    }

    @PostMapping({"","/"})
    public ResponseEntity<?> addProduct(@RequestBody ProductTO product){
        return ResponseEntity.ok().body(productService.addProduct(product));
    }
}
