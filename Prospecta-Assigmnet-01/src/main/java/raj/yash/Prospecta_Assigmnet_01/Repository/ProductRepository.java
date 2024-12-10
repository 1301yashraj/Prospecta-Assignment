package raj.yash.Prospecta_Assigmnet_01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raj.yash.Prospecta_Assigmnet_01.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(int category);
}
