package raj.yash.Prospecta_Assigmnet_01.Service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raj.yash.Prospecta_Assigmnet_01.DTO.CategoryTO;
import raj.yash.Prospecta_Assigmnet_01.DTO.ProductTO;
import raj.yash.Prospecta_Assigmnet_01.Exception.CategoryNotFoundException;
import raj.yash.Prospecta_Assigmnet_01.Model.Category;
import raj.yash.Prospecta_Assigmnet_01.Model.Product;
import raj.yash.Prospecta_Assigmnet_01.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
     public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService){
          this.modelMapper = modelMapper;
          this.productRepository = productRepository;
          this.categoryService = categoryService;
     }

    @Override
    public List<ProductTO> getProductsByCategory(String category) {
      CategoryTO categoryObj =  categoryService.getCategoryByName(category);
      if(categoryObj!= null  && categoryObj.getIsActive()){
          List<ProductTO> productList =  productRepository
                  .findByCategory(categoryObj.getId())
                  .stream()
                  .map(product -> {
                      ProductTO productTO = modelMapper.map(product, ProductTO.class);
                      productTO.setCategory(categoryObj.getName());
                      return productTO;
                  })
                  .collect(Collectors.toList());
          System.out.println(productList);
          return productList;
      }
      return null;
    }


    @Override
    @Transactional
    public ProductTO addProduct(ProductTO product) {
        if(product.getCategory() == null) product.setCategory("-");
        CategoryTO categoryObj = null;
        try{
             categoryObj = categoryService.getCategoryByName(product.getCategory());
        }catch(CategoryNotFoundException e){
            if(product.getCreateNewCategory() == null || !product.getCreateNewCategory())
                 throw e;
            else{
                if(categoryObj == null && product.getCreateNewCategory()){
                    categoryObj = new CategoryTO();
                    categoryObj.setName(product.getCategory());
                    categoryObj.setIsActive(true);
                    Category categoryBO = categoryService.addCategory(categoryObj);
                    categoryObj = modelMapper.map(categoryBO,CategoryTO.class);
                }
            }
        }

        Product productBO = modelMapper.map(product, Product.class);
        productBO.setCategory(categoryObj.getId());
        Product pp = productRepository.save(productBO);
        ProductTO productTO =  modelMapper.map(pp,ProductTO.class);
        productTO.setCategory(categoryObj.getName());
        return productTO;

    }
}
