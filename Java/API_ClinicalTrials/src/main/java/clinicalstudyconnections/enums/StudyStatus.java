package clinicalstudyconnections.enums;

public enum StudyStatus {
	PENDING(0),
	RECRUITING(1),
	IN_PROGRESS(2),
	COMPLETED(3),
	TERMINATED(4);
	
	// https://stackoverflow.com/questions/8157755/how-to-convert-enum-value-to-int
	private final int value;
	
	StudyStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
