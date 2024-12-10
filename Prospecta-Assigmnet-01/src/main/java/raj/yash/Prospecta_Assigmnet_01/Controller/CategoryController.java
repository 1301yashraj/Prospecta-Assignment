package raj.yash.Prospecta_Assigmnet_01.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raj.yash.Prospecta_Assigmnet_01.DTO.CategoryTO;
import raj.yash.Prospecta_Assigmnet_01.Service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

      private final CategoryService categoryService;
      public CategoryController(CategoryService categoryService){
          this.categoryService =  categoryService;

      }

      @GetMapping("/{id}")
      public ResponseEntity<?> getCategoryById(@PathVariable("id") Integer categoryID){
          CategoryTO category = categoryService.getCategoryById(categoryID);
          return ResponseEntity.ok().body(category);
      }

    @GetMapping({"/", "/all",""})
    public ResponseEntity<?> getAll(){
       List<CategoryTO> categoryList = categoryService.getAll();
       return ResponseEntity.ok().body(categoryList);
    }

}
