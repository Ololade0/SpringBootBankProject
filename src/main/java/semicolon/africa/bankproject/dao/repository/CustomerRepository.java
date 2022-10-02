package semicolon.africa.bankproject.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Customer;

import java.nio.file.LinkOption;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
