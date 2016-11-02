package org.mifos.repos;

/**
 * Created by daniel on 11/2/16.
 */
import org.mifos.portfolio.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
@Transactional
public interface TransactionsRepository extends CrudRepository<Transactions, Long> { }