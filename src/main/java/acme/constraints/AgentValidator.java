
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.realms.Agent;

@Validator
public class AgentValidator extends AbstractValidator<ValidAgent, Agent> {

	@Override
	protected void initialise(final ValidAgent annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Agent agent, final ConstraintValidatorContext context) {

		assert context != null;
		boolean result;

		if (agent == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else {
			String initials = this.getInitials(agent);
			String code = agent.getCode();
			boolean initialsLikeName;

			initialsLikeName = code != null && code.startsWith(initials);
			super.state(context, initialsLikeName, "code", "acme.constraints.ValidAgent.message");
		}
		result = !super.hasErrors(context);
		return result;
	}

	private String getInitials(final Agent agent) {

		String initials = "";
		String name = agent.getUserAccount().getIdentity().getName().trim();
		String surname = agent.getUserAccount().getIdentity().getSurname().trim();

		if (name != null && surname != null)
			initials = name.substring(0, 1) + surname.substring(0, 1);

		return initials;
	}
}
