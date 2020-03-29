public class UnitStack{
	protected Unit[] stack;
	private Tile location;
	public static Map<double, BattleReport[][][]> CRT = null;

	public static void buildCRT(){

	}

	public UnitStack(Unit[] units){
		this.stack = units;
	}

	public BattleReport attack(UnitStack defender, Supplier<int> die){
		double powerRatio = getAttackPower()/defender.getDefensePower();
	}

	public int getAttackPower(){
		int p = 0;
		for (Unit u:stack){
			p += u.getAttack();
		}
		return p;
	}

	public int getDefensePower(){
		int p = 0;
		for(Unit u:stack){
			p += u.getDefense();
		}
		return p;
	}
}