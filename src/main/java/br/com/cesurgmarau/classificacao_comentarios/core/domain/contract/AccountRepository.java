package br.com.cesurgmarau.classificacao_comentarios.core.domain.contract;

import br.com.cesurgmarau.classificacao_comentarios.core.domain.entity.Account;

import java.util.List;

public interface AccountRepository {
    public Account create(Account account);
    public Account update(int accountID, Account account);
    public void delete(int accountID);
    public Account getByID(int accountID);
    public List<Account> fetch();
}
