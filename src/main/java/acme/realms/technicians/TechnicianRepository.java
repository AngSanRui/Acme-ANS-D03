
package acme.realms.technicians;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface TechnicianRepository extends AbstractRepository {

	@Query("SELECT t FROM Technician t WHERE t.licenseNumber= :licenseNumber")
	Technician findTechnicianSameLicenseNumber(String licenseNumber);

}
