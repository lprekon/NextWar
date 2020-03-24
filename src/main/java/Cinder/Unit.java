public class Unit
{
	private int totalMovement;
	private int remainingMovement;
	private int attack;
	private int defense;
	private int efficiancy;
	private double stackingPoints;
	private boolean hasReducedStep;
	private UnitStatus unitStatus;
	private int reducedTotalMovement;
	private int reducedAttack;
	private int reducedDefense;
	private int reducedEfficiancy;
	private double reducedStackingPoints;
	private String name;

	public Unit()
	{
		this.name = "Empty Unit";
		this.unitStatus = UnitStatus.UNDEFINED;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, double stackingPoints)
	{
		this.unitStatus = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.totalMovement = movement;
		this.remainingMovement = this.totalMovement;
		this.efficiancy = efficiancy;
		this.stackingPoints = stackingPoints;
		hasReducedStep = false;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, double stackingPoints, int reducedAttack, int reducedDefense, int reducedMovement, int reducedEfficiancy, double reducedStackingPoints)
	{
		this.unitStatus = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.totalMovement = movement;
		this.remainingMovement = this.totalMovement;
		this.efficiancy = efficiancy;
		this.stackingPoints = stackingPoints;
		this.hasReducedStep = true;
		this.reducedAttack = reducedAttack;
		this.reducedDefense = reducedDefense;
		this.reducedTotalMovement = reducedMovement;
		this.reducedEfficiancy = reducedEfficiancy;
		this.reducedStackingPoints = reducedStackingPoints;
	}

	public String getName()
	{
		return this.name;
	}

	public UnitStatus getStatus()
	{
		return this.unitStatus;
	}

	public int getAttack()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedAttack:this.attack;
	}

	public int getDefense()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedDefense:this.defense;
	}

	public int getEfficiancy()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedEfficiancy:this.efficiancy;
	}

	public double getStackingPoints()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedStackingPoints:this.stackingPoints;
	}

	public int getRemainingMovement()
	{
		return this.remainingMovement;
	}

	public int getTotalMovement()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedTotalMovement:this.totalMovement;
	}

	public void subtractMovement(int spaces)
	{
		if((this.unitStatus != UnitStatus.HEALTHY) || (this.unitStatus != UnitStatus.STEP_LOSS_TAKEN))
		{
			throw new UnsupportedOperationException("UNMOVEABLE - " + this.toString());
		}
		if (spaces > this.remainingMovement)
		{
			throw new IllegalArgumentException("OVERMOVE - Attempt to move " + spaces + " spaces by " + this.toString());
		}
	}

	public void resetMovement()
	{
		this.remainingMovement = (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedTotalMovement:this.totalMovement;
	}

	public UnitStatus takeStepLoss()
	{
		if(this.unitStatus != UnitStatus.HEALTHY && this.unitStatus!= UnitStatus.STEP_LOSS_TAKEN)
		{
			throw new IllegalStateException("Unit cannot take a step loss:\n" + this.toString());
		}

		if(this.unitStatus == UnitStatus.HEALTHY && this.hasReducedStep)
		{
			this.unitStatus = UnitStatus.STEP_LOSS_TAKEN;
		}
		else
		{
			this.unitStatus = UnitStatus.DEAD;
		}

		return this.unitStatus;
	}

	public String toString()
	{
		return "(" + this.unitStatus + ")" + this.name;
	}
}