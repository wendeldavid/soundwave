package inf.furb.synthesis.jsml;

class AttributeImpl implements IAttribute {

	private String name;
	private String value;
	private boolean required = false;
	private String defaultValue = "";
	private String[] validValues;

	public AttributeImpl(String name) {
		setName(name);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		if (this.value == null) {
			return defaultValue;
		}
		return value;
	}

	@Override
	public boolean isRequired() {
		return required;
	}

	@Override
	public void setValidValues(String[] validValues, String defaultValue) {
		this.validValues = validValues;
		this.defaultValue = defaultValue;
	}

	@Override
	public boolean isValid(String value) {
		if (validValues == null) {
			return true;
		}

		for (int i = 0; i < validValues.length; i++) {
			//isso é para permitir que null também seja visto como uma valor válido
			if(validValues[i] == null && value == null) {
				return true;
			}
			
			if (value.equals(validValues[i])) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void setName(String name) {
		if (name != null && !name.equals("")) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("the attribute name \"" + name + "\" is invalid");
		}
	}

	@Override
	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public void setValue(String value) {
		if (isValid(value)) {
			this.value = value;
		}
	}

}
