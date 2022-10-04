package semicolon.africa.bankproject.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerId(Long id);
}
