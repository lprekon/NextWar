public enum RoadType
{
	NONE ("NONE"), SECONDARY ("SECONDARY"), PRIMARY ("PRIMARY"), HIGHWAY ("HIGHWAY");

	private String name;

	RoadType(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		return name;
	}
}