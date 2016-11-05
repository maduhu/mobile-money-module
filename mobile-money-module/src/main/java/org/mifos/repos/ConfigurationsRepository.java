package org.mifos.repos;

/**
 * Created by daniel on 11/2/16.
 */
import javax.transaction.Transactional;
import org.mifos.portfolio.Configurations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


@SuppressWarnings("unused")
@Transactional
public interface ConfigurationsRepository extends CrudRepository<Configurations, Long>{

    // Query to get the current default configuration
    @Query("SELECT CONFIG FROM CONFIGURATIONS WHERE default=1")
    public List<Configurations> findByDefaultId(@Param("default") int defaultId);
}