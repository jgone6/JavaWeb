package team_6.web_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_6.web_project.domain.Account;
import team_6.web_project.dto.AccountForm;
import team_6.web_project.repository.AccountRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountForm form){
        Account account = form.toEntity();
        accountRepository.save(account);
        return account.getId();
    }
}
