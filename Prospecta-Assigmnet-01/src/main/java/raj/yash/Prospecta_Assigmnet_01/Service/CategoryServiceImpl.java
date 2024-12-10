package raj.yash.Prospecta_Assigmnet_01.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import raj.yash.Prospecta_Assigmnet_01.DTO.CategoryTO;
import raj.yash.Prospecta_Assigmnet_01.Exception.CategoryNotFoundException;
import raj.yash.Prospecta_Assigmnet_01.Model.Category;
import raj.yash.Prospecta_Assigmnet_01.Repository.CategoryRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;


    //using constructor injection
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository/*,ModelMapper modelMapper*/){
        this.categoryRepository = categoryRepository;
//        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryTO getCategoryById(Integer id) {

          Optional<Category> option =
               this.categoryRepository
                       .findById(id);
        System.out.println(option);
                     return option.map(cat -> modelMapper.map(cat,CategoryTO.class))
                       .orElseThrow(() -> new CategoryNotFoundException("Category not found with id : "+id ));
    }

    @Override
    public CategoryTO getCategoryByName(String categoryName) {
        return categoryRepository
                .findByName(categoryName)
                .map(cat->modelMapper.map(cat,CategoryTO.class))
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + categoryName));
    }

    @Override
    public Category addCategory(CategoryTO category) {
        Category category1 = new Category();
        category1.setIsActive(category.getIsActive());
        category1.setName(category.getName());
       return categoryRepository.saveAndFlush(category1);
    }

    @Override
    public List<CategoryTO> getAll(){
        List<CategoryTO> categoryList = categoryRepository.findAll()
                .stream()
                .map(cat->modelMapper.map(cat,CategoryTO.class))
                .collect(Collectors.toList());
        return categoryList.isEmpty() ? Collections.emptyList() : categoryList;
    }
}
