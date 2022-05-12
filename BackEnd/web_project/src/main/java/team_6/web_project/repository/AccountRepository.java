package team_6.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_6.web_project.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
