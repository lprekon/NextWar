public enum TerrainType
{
	FLAT ("FLAT"), FLAT_WOODS ("FLAT_WOODS"), ROUGH ("ROUGH"), ROUGH_WOODS ("ROUGH_WOODS"), 
	MARSH ("MARSH"), HIGHLAND ("HIGHLAND"), HIGHLAND_WOODS ("HIGHLAND_WOODS"), 
	MOUNTAIN ("MOUNTAIN"), URBAN ("URBAN");

	private String name;

	TerrainType(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		return name;
	}
}