
package acme.features.customer.booking;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.bookings.Booking;
import acme.entities.passengers.Passenger;

@Repository
public interface CustomerBookingRepository extends AbstractRepository {

	@Query("select b from Booking b where b.id = :id")
	Booking findBookingById(Integer id);

	@Query("select b from Booking b")
	Collection<Booking> findAllBookings();

	@Query("select b from Booking b where b.customer.userAccount.id = :customerId")
	Collection<Booking> findAllBookingsByCustomerId(Integer customerId);

	@Query("select t.passenger from Takes t where t.booking.id = :bookingId")
	Collection<Passenger> findAllPassengersByBookingId(Integer bookingId);
}
