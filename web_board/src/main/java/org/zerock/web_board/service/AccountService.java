package org.zerock.web_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.web_board.domain.Account;
import org.zerock.web_board.dto.AccountForm;
import org.zerock.web_board.repository.AccountRepository;


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
