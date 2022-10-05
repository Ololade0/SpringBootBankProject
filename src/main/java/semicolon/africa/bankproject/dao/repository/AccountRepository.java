package semicolon.africa.bankproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);
}
