public enum UnitStatus
{
	UNDEFINED ("UNDEFINED"), HEALTHY ("HEALTHY"), STEP_LOSS_TAKEN ("STEP_LOSS_TAKEN"), DEAD ("DEAD");

	private String name;

	UnitStatus(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		return name;
	}
}