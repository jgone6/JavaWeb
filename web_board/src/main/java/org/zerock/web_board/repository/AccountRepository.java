package org.zerock.web_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.web_board.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
