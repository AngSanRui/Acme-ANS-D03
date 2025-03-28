
package acme.features.customer.passengers;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.bookings.Booking;
import acme.entities.passengers.Passenger;

@Repository
public interface CustomerPassengerRepository extends AbstractRepository {

	@Query("select p from Passenger p where p.id = :id")
	Passenger findPassengerById(Integer id);

	@Query("select p from Passenger p")
	Collection<Passenger> findAllPassengers();

	@Query("select t.passenger from Takes t where t.booking.id = :bookingId")
	Collection<Passenger> findAllPassengersByBookingId(Integer bookingId);

	@Query("select b from Booking b where b.id = :id")
	Booking findBookingById(Integer id);
}
