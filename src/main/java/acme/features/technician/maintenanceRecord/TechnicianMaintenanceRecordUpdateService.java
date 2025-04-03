
package acme.features.technician.maintenanceRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.aircrafts.Aircraft;
import acme.entities.maintenanceRecords.MaintenanceRecord;
import acme.entities.maintenanceRecords.MaintenanceStatus;
import acme.realms.technicians.Technician;

@GuiService
public class TechnicianMaintenanceRecordUpdateService extends AbstractGuiService<Technician, MaintenanceRecord> {

	@Autowired
	private TechnicianMaintenanceRecordRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		MaintenanceRecord maintenanceRecord;
		int id;

		id = super.getRequest().getData("id", int.class);
		maintenanceRecord = this.repository.findMaintenanceRecordById(id);

		super.getBuffer().addData(maintenanceRecord);
	}

	@Override
	public void bind(final MaintenanceRecord maintenanceRecord) {
		int aircraftId;
		int technicianId;
		Aircraft aircraft;
		Technician technician;

		aircraftId = super.getRequest().getData("aircraft", int.class);
		aircraft = this.repository.findAircraftById(aircraftId);
		technicianId = super.getRequest().getPrincipal().getActiveRealm().getId();
		technician = this.repository.findTechnicianById(technicianId);

		super.bindObject(maintenanceRecord, "maintenanceStatus", "nextInspectionDate", "estimatedCost", "notes");
		maintenanceRecord.setMaintenanceMoment(maintenanceRecord.getMaintenanceMoment());
		maintenanceRecord.setAircraft(aircraft);
		maintenanceRecord.setDraftMode(maintenanceRecord.getDraftMode());
		maintenanceRecord.setTechnician(technician);
	}

	@Override
	public void validate(final MaintenanceRecord maintenanceRecord) {
		boolean nextInspectionDateNotNull;

		nextInspectionDateNotNull = maintenanceRecord.getNextInspectionDate() != null;
		super.state(nextInspectionDateNotNull, "nextInspectionDate", "validation.maintenanceRecords.nextInspectionDateNotNull");
	}

	@Override
	public void perform(final MaintenanceRecord maintenanceRecord) {
		this.repository.save(maintenanceRecord);
	}

	@Override
	public void unbind(final MaintenanceRecord maintenanceRecord) {
		Dataset dataset;
		SelectChoices statusChoices;
		SelectChoices aircraftChoices;
		Collection<Aircraft> aircrafts;

		aircrafts = this.repository.findAllAircrafts();

		aircraftChoices = SelectChoices.from(aircrafts, "registrationNumber", maintenanceRecord.getAircraft());
		statusChoices = SelectChoices.from(MaintenanceStatus.class, maintenanceRecord.getMaintenanceStatus());

		dataset = super.unbindObject(maintenanceRecord, "maintenanceMoment", "nextInspectionDate", "estimatedCost", "notes", "technician");
		dataset.put("statuses", statusChoices);
		dataset.put("draftMode", maintenanceRecord.getDraftMode());
		dataset.put("aircrafts", aircraftChoices);

		super.getResponse().addData(dataset);
	}
}
