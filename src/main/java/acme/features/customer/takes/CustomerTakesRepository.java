
package acme.features.customer.takes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.bookings.Booking;
import acme.entities.passengers.Passenger;

@Repository
public interface CustomerTakesRepository extends AbstractRepository {

	@Query("select b from Booking b where b.id = :id")
	Booking findBookingById(int id);

	@Query("select p from Passenger p where p.id = :id")
	Passenger findPassengerById(int id);

	@Query("select p from Passenger p where p.customer.id = :id")
	Collection<Passenger> findAllPassengersByCustomerId(int id);
}
