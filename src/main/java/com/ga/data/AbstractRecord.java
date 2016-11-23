package com.ga.data;

public abstract class AbstractRecord implements Record {
	
	@Override
	public abstract boolean compareInputs(BinaryRecord otherRecord);

	@Override
	public abstract boolean compareOutputs(BinaryRecord otherRecord);
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof BinaryRecord)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		BinaryRecord otherRecord = (BinaryRecord) obj;
		
		if (!compareInputs(otherRecord)) {
			return false;
		}
		
		return compareOutputs(otherRecord);
	}
}

