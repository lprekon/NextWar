public class Unit
{
	private int total_movement;
	private int remaining_movement;
	private int attack;
	private int defense;
	private int efficiancy;
	private int stacking_points;
	private boolean can_take_loss;
	private UnitStatus unit_status;
	private int reduced_total_movement;
	private int reduced_remaining_movement;
	private int reduced_attack;
	private int reduced_defense;
	private int reduced_efficiancy;
	private int reduced_stacking_points;
	private String name;

	public Unit()
	{
		this.name = "Empty Unit";
		this.unit_status = UnitStatus.DEAD;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, int stacking_points)
	{
		this.unit_status = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.total_movement = movement;
		this.remaining_movement = this.total_movement;
		this.efficiancy = efficiancy;
		this.stacking_points = stacking_points;
		can_take_loss = false;
	}

	public Unit(String name, int attack, int defense, int movement, int efficiancy, int stacking_points, int reduced_attack, int reduced_defense, int reduced_movement, int reduced_efficiancy, int reduced_stacking_points)
	{
		this.unit_status = UnitStatus.HEALTHY;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.total_movement = movement;
		this.remaining_movement = this.total_movement;
		this.efficiancy = efficiancy;
		this.stacking_points = stacking_points;
		this.can_take_loss = true;
		this.reduced_attack = reduced_attack;
		this.reduced_defense = reduced_defense;
		this.reduced_total_movement = reduced_movement;
		this.reduced_efficiancy = reduced_efficiancy;
		this.reduced_stacking_points = reduced_stacking_points;
	}

	public String get_name()
	{
		return this.name;
	}

	public int get_attack()
	{
		return (this.unit_status == UnitStatus.STEP_LOSS_TAKEN)?this.reduced_attack:this.attack;
	}

	public int get_defense()
	{
		return (this.unit_status == UnitStatus.STEP_LOSS_TAKEN)?this.reduced_defense:this.defense;
	}

	public int get_efficiancy()
	{
		return (this.unit_status == UnitStatus.STEP_LOSS_TAKEN)?this.reduced_efficiancy:this.efficiancy;
	}

	public int get_stacking_points()
	{
		return (this.unit_status == UnitStatus.STEP_LOSS_TAKEN)?this.reduced_stacking_points:this.stacking_points;
	}

	public int get_remaining_movement()
	{
		return this.remaining_movement;
	}

	public int get_total_movement()
	{
		return this.total_movement;
	}

	public void subtract_movement(int spaces)
	{
		if((this.unit_status != UnitStatus.HEALTHY) || (this.unit_status != UnitStatus.STEP_LOSS_TAKEN))
		{
			throw new UnsupportedOperationException("UNMOVEABLE - " + this.toString());
		}
		if (spaces > this.remaining_movement)
		{
			throw new IllegalArgumentException("OVERMOVE - Attempt to move " + spaces + " spaces by " + this.toString());
		}
	}

	public void reset_movement()
	{
		this.remaining_movement = this.total_movement;
	}

	public void take_step_loss()
	{
		if(!this.can_take_loss || (this.unit_status != UnitStatus.HEALTHY && this.unit_status!= UnitStatus.STEP_LOSS_TAKEN))
		{
			throw new IllegalStateException("Unit cannot take a step loss:\n" + this.toString());
		}

		this.unit_status = (this.unit_status == UnitStatus.HEALTHY)?UnitStatus.STEP_LOSS_TAKEN:UnitStatus.DEAD;
	}

	public String toString()
	{
		return "(" + this.unit_status + ")" + this.name;
	}
}