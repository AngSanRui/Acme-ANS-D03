
package acme.features.customer.booking;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.bookings.Booking;
import acme.entities.bookings.TravelClass;
import acme.entities.flights.Flight;
import acme.realms.customers.Customer;

@GuiService
public class CustomerBookingUpdateService extends AbstractGuiService<Customer, Booking> {

	@Autowired
	private CustomerBookingRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int bookingId;
		int customerId;
		Booking booking;
		Customer customer;

		bookingId = super.getRequest().getData("id", int.class);
		booking = this.repository.findBookingById(bookingId);

		customerId = super.getRequest().getPrincipal().getAccountId();
		customer = this.repository.findCustomerById(customerId);

		status = booking != null && booking.isDraftMode() && super.getRequest().getPrincipal().hasRealm(customer);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Booking booking;
		int id;

		id = super.getRequest().getData("id", int.class);
		booking = this.repository.findBookingById(id);

		super.getBuffer().addData(booking);
	}

	@Override
	public void bind(final Booking booking) {
		Integer customerId;
		Customer customer;

		Integer flightId;
		Flight flight;

		flightId = super.getRequest().getData("flightId", int.class);
		flight = this.repository.findFlightById(flightId);

		customerId = super.getRequest().getPrincipal().getAccountId();
		customer = this.repository.findCustomerById(customerId);

		super.bindObject(booking, "locatorCode", "purchaseMoment", "travelClass", "price", "lastCreditCardDigits");
		booking.setCustomer(customer);
		booking.setFlight(flight);
	}

	@Override
	public void validate(final Booking booking) {
		;
	}

	@Override
	public void perform(final Booking booking) {
		this.repository.save(booking);
	}

	@Override
	public void unbind(final Booking booking) {
		Integer customerId;
		Customer customer;
		Dataset dataset;
		SelectChoices classChoices;

		Integer flightId;
		Flight flight;

		flightId = super.getRequest().getData("flightId", int.class);
		flight = this.repository.findFlightById(flightId);

		customerId = super.getRequest().getPrincipal().getAccountId();
		customer = this.repository.findCustomerById(customerId);
		classChoices = SelectChoices.from(TravelClass.class, booking.getTravelClass());

		dataset = super.unbindObject(booking, "locatorCode", "purchaseMoment", "travelClass", "price", "lastCreditCardDigits");
		dataset.put("flight", flight);
		dataset.put("customer", customer);
		dataset.put("classes", classChoices);
	}

}
