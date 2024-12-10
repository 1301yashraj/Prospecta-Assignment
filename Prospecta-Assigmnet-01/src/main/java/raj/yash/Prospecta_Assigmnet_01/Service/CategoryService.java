package raj.yash.Prospecta_Assigmnet_01.Service;

import raj.yash.Prospecta_Assigmnet_01.DTO.CategoryTO;
import raj.yash.Prospecta_Assigmnet_01.Model.Category;

import java.util.List;

public interface CategoryService {
    CategoryTO getCategoryByName(String categoryName);
    CategoryTO getCategoryById(Integer id);
    List<CategoryTO> getAll();
    Category addCategory(CategoryTO category);
}
