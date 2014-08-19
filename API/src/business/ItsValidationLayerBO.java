package business;

public enum ItsValidationLayerBO {
	getInstance;

	public Boolean valid(String value) {
		if (value.equals("33")) {
			return true;
		} else {
			return false;
		}
	}
}
