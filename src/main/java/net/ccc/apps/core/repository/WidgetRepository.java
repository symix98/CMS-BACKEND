package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.Widget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Widget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WidgetRepository extends JpaRepository<Widget, Long> {}
