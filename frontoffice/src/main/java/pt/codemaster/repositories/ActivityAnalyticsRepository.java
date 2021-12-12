package pt.codemaster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.codemaster.adt.analytics.ActivityAnalytics;

@Repository
public interface ActivityAnalyticsRepository extends JpaRepository<ActivityAnalytics, Long> {
}
