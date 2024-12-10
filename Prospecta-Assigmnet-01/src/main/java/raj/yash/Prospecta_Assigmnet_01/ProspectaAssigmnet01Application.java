package raj.yash.Prospecta_Assigmnet_01;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import raj.yash.Prospecta_Assigmnet_01.DTO.ProductTO;
import raj.yash.Prospecta_Assigmnet_01.Model.Product;

@SpringBootApplication
public class ProspectaAssigmnet01Application {

	public static void main(String[] args) {
		SpringApplication.run(ProspectaAssigmnet01Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Custom mapping for ProductTO to Product
		modelMapper.addMappings(new PropertyMap<ProductTO, Product>() {
			@Override
			protected void configure() {
				map().setCategory(source.getCategoryId());
			}
		});
		return modelMapper;
	}

}
