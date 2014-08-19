package model;

public class ActorBean {
	private Integer actorId;
	private String firstName;
	private String lastName;
	private String lastUpdate;

	/**
	 * @return the actorId
	 */
	public Integer getActorId() {
		return actorId;
	}

	/**
	 * @param actorId
	 *            the actorId to set
	 */
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the lastUpdate
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 *            the lastUpdate to set
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"actorId\":\"" + actorId + "\", \"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"lastUpdate\":\"" + lastUpdate + "\"}";
	}

}
