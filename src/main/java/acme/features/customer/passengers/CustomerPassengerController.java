
package acme.features.customer.passengers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.passengers.Passenger;
import acme.realms.customers.Customer;

@GuiController
public class CustomerPassengerController extends AbstractGuiController<Customer, Passenger> {

	@Autowired
	private CustomerPassengerFromBookingListService	listFromBookingService;

	@Autowired
	private CustomerPassengerCreateService			createService;

	@Autowired
	private CustomerPassengerShowService			showService;

	@Autowired
	private CustomerPassengerListService			listService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);

		super.addCustomCommand("listFromBooking", "list", this.listFromBookingService);

	}
}
