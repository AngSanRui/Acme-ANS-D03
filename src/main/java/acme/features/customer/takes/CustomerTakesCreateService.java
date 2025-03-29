
package acme.features.customer.takes;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.bookings.Booking;
import acme.entities.passengers.Passenger;
import acme.entities.passengers.Takes;
import acme.realms.customers.Customer;

@GuiService
public class CustomerTakesCreateService extends AbstractGuiService<Customer, Takes> {

	@Autowired
	private CustomerTakesRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Takes takes;
		takes = new Takes();
		super.getBuffer().addData(takes);
	}

	@Override
	public void bind(final Takes takes) {
		Passenger passenger = null;
		Booking booking;

		booking = this.repository.findBookingById(super.getRequest().getData("booking", Integer.class));
		Integer passengerId = super.getRequest().getData("passenger", Integer.class);

		if (passengerId != null)
			passenger = this.repository.findPassengerById(passengerId);

		super.bindObject(takes, "passenger", "booking");
		takes.setPassenger(passenger);
		takes.setBooking(booking);
	}

	@Override
	public void validate(final Takes takes) {
		;
	}

	@Override
	public void perform(final Takes takes) {
		this.repository.save(takes);
	}

	@Override
	public void unbind(final Takes takes) {
		Dataset dataset;
		SelectChoices passengerChoices;
		Collection<Passenger> passengers;
		Integer customerId;
		Integer bookingId;
		Booking booking;

		bookingId = super.getRequest().getData("masterId", Integer.class);
		booking = this.repository.findBookingById(bookingId);

		customerId = super.getRequest().getPrincipal().getActiveRealm().getId();

		passengers = this.repository.findAllPassengersByCustomerId(customerId);
		passengerChoices = SelectChoices.from(passengers, "name", takes.getPassenger());

		dataset = super.unbindObject(takes, "passenger", "booking");
		dataset.put("passengers", passengerChoices);
		dataset.put("booking", booking);

		super.getResponse().addData(dataset);
	}

}
