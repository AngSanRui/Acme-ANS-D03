/*
 * FlightCrewMemberActivityLogRepository.java
 *
 * Copyright (C) 2012-2025 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.flightCrewMember.flightAssignments;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.flightAssignment.Duties;
import acme.entities.flightAssignment.FlightAssignment;
import acme.entities.flights.Leg;
import acme.realms.flightCrewMembers.AvailabilityStatus;
import acme.realms.flightCrewMembers.FlightCrewMember;

@Repository
public interface FlightCrewMemberFlightAssignmentRepository extends AbstractRepository {

	@Query("select a from FlightAssignment a where a.id = :id")
	FlightAssignment findFlightAssignmentById(int id);

	@Query("select a from FlightAssignment a where a.flightCrewMember = :fcm")
	Collection<FlightAssignment> findFlightAssignmentsByCrewMember(FlightCrewMember fcm);

	@Query("select a from FlightAssignment a where a.leg.scheduledArrival<:now and a.flightCrewMember.id = :crewMemberId")
	Collection<FlightAssignment> findCompletedAssignmentsByCrewMemberId(Date now, int crewMemberId);

	@Query("select a from FlightAssignment a where a.leg.scheduledArrival>:now and a.flightCrewMember.id = :crewMemberId")
	Collection<FlightAssignment> findUncompletedAssignmentsByCrewMemberId(Date now, int crewMemberId);

	@Query("select a from FlightAssignment a")
	Collection<FlightAssignment> findAllAssignments();

	@Query("select f from FlightCrewMember f where f.availabilityStatus = :status")
	Collection<FlightCrewMember> findAllFlightCrewMembers(AvailabilityStatus status);

	@Query("select l from Leg l")
	Collection<Leg> findAllLegs();

	@Query("select f.duty from FlightAssignment f where f.leg.id = :legId")
	Collection<Duties> findPresentRolesInLeg(Leg legId);

}
