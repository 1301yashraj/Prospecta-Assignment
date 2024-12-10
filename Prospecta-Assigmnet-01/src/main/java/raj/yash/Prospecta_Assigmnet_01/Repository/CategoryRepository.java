package raj.yash.Prospecta_Assigmnet_01.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raj.yash.Prospecta_Assigmnet_01.Model.Category;

import java.util.Optional;
@Repository //optional annotation not required, just used for our clarity
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
}
