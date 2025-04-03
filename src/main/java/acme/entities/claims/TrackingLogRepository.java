
package acme.entities.claims;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface TrackingLogRepository extends AbstractRepository {

	@Query("select MAX(t.percentage) from TrackingLog t WHERE t.claim.id = :claimId")
	Integer findMaxPercentage(int claimId);
}
