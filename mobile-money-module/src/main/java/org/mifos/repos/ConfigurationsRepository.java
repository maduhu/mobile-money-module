package org.mifos.repos;

/**
 * Created by daniel on 11/2/16.
 */
import javax.transaction.Transactional;
import org.mifos.portfolio.Configurations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


@SuppressWarnings("unused")
@Transactional
public interface ConfigurationsRepository extends CrudRepository<Configurations, Long>{}