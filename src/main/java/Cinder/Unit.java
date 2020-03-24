public class Unit
{
	private int totalMovement;
	private int remainingMovement;
	private int attack;
	private int defense;
	private int efficiancy;
	private int stackingPoints;
	private boolean canTakeLoss;
	private UnitStatus unitStatus;
	private int reducedTotalMovement;
	private int reducedRemainingMovement;
	private int reducedAttack;
	private int reducedDefense;
	private int reducedEfficiancy;
	private int reducedStackingPoints;
	private String name;

	public Unit()
	{
		this.name = "Empty Unit";
		this.unitStatus = UnitStatus.DEAD;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, int stackingPoints)
	{
		this.unitStatus = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.totalMovement = movement;
		this.remainingMovement = this.totalMovement;
		this.efficiancy = efficiancy;
		this.stackingPoints = stackingPoints;
		canTakeLoss = false;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, int stackingPoints, int reducedAttack, int reducedDefense, int reduced_movement, int reducedEfficiancy, int reducedStackingPoints)
	{
		this.unitStatus = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.totalMovement = movement;
		this.remainingMovement = this.totalMovement;
		this.efficiancy = efficiancy;
		this.stackingPoints = stackingPoints;
		this.canTakeLoss = true;
		this.reducedAttack = reducedAttack;
		this.reducedDefense = reducedDefense;
		this.reducedTotalMovement = reduced_movement;
		this.reducedEfficiancy = reducedEfficiancy;
		this.reducedStackingPoints = reducedStackingPoints;
	}

	public String getName()
	{
		return this.name;
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

	public int getStackingPoints()
	{
		return (this.unitStatus == UnitStatus.STEP_LOSS_TAKEN)?this.reducedStackingPoints:this.stackingPoints;
	}

	public int getRemainingMovement()
	{
		return this.remainingMovement;
	}

	public int getTotalMovement()
	{
		return this.totalMovement;
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
		this.remainingMovement = this.totalMovement;
	}

	public void takeStepLoss()
	{
		if(!this.canTakeLoss || (this.unitStatus != UnitStatus.HEALTHY && this.unitStatus!= UnitStatus.STEP_LOSS_TAKEN))
		{
			throw new IllegalStateException("Unit cannot take a step loss:\n" + this.toString());
		}

		this.unitStatus = (this.unitStatus == UnitStatus.HEALTHY)?UnitStatus.STEP_LOSS_TAKEN:UnitStatus.DEAD;
	}

	public String toString()
	{
		return "(" + this.unitStatus + ")" + this.name;
	}
}