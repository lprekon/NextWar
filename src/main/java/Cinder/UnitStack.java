import java.util.EnumMap;
import java.util.HashMap;
import java.util.function.Supplier;

public class UnitStack{
	protected Unit[] stack;
	private Tile location;

	public UnitStack(Unit[] units){
		this.stack = units;
	}

	public BattleReport attack(UnitStack defender, Supplier<Integer> die){
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

	private static class CombatResultsTable{
		private static HashMap<Double, Integer> oddsForUrban;
		static{
			oddsForUrban = new HashMap<Double, Integer>();
			oddsForUrban.put(1.0/3, 1);
			oddsForUrban.put(1.0/2, 2);
			oddsForUrban.put(1.0, 3);
			oddsForUrban.put(1.5, 4);
			oddsForUrban.put(2.0, 5);
			oddsForUrban.put(3.0, 6);
			oddsForUrban.put(4.0, 7);
			oddsForUrban.put(5.0, 8);
			oddsForUrban.put(6.0, 9);
			oddsForUrban.put(7.0, 10);
			oddsForUrban.put(8.0, 11);
			oddsForUrban.put(9.0, 12);
			oddsForUrban.put(10.0, 13);
		}
		//private static int[][]crt = {{new BattleResult(1, 1, true, false), new BattleResult(1, 1, true, false), new BattleResult(0, 1, true, false), new BattleResult(0, 1, true, false), new BattleResult(0, 2, true, false), new BattleResult(0, 2, true, false), new BattleResult(0, 2, true, false), new BattleResult(0, 3, true, false),},}
		private static int [][][] crt ={{{1, 1}, {1, 1}, {0, 1}, {0, 1}, {0, 2}, {0, 2}, {0, 2}, {0, 3}, {0, 3}, {0, 3}, {0, 4}, {0, 4}, {0, 4}},
										{{1, 1}, {1, 1}, {1, 1}, {0, 1}, {0, 1}, {1, 2}, {0, 2}, {0, 2}, {1, 3}, {0, 3}, {1, 3}, {1, 4}, {0, 4}},
										{{1, 1}, {1, 1}, {1, 1}, {1, 1}, {0, 1}, {0, 1}, {1, 2}, {0, 2}, {0, 2}, {1, 3}, {0, 3}, {0, 3}, {1, 4}},
										{{1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {0, 1}, {0, 1}, {1, 2}, {0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 3}},
										{{1, 0}, {1, 1}, {1, 2}, {1, 1}, {1, 1}, {1, 2}, {0, 1}, {0, 1}, {1, 2}, {0, 1}, {0, 2}, {1, 3}, {0, 3}},
										{{2, 1}, {1, 0}, {1, 1}, {0, 1}, {1, 1}, {1, 1}, {1, 1}, {0, 1}, {0, 1}, {1, 2}, {0, 2}, {0, 2}, {1, 3}},
										{{2, 1}, {2, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 2}, {1, 1}, {0, 2}, {0, 1}, {1, 2}, {0, 2}, {0, 2}},
										{{2, 0}, {2, 1}, {2, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {0, 1}, {0, 1}, {1, 2}, {0, 2}},
										{{1, 1}, {2, 0}, {2, 1}, {2, 1}, {1, 0}, {2, 1}, {1, 1}, {1, 2}, {1, 1}, {1, 2}, {0, 1}, {0, 1}, {1, 2}},
										{{2, 0}, {2, 0}, {2, 0}, {2, 1}, {2, 1}, {1, 0}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {0, 1}, {1, 1}, {0, 1}},
										{{3, 1}, {2, 1}, {2, 0}, {1, 1}, {2, 1}, {2, 1}, {1, 0}, {1, 1}, {1, 2}, {1, 1}, {1, 2}, {0, 1}, {0, 1}},
										{{3, 0}, {3, 1}, {2, 0}, {2, 0}, {2, 0}, {2, 1}, {2, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {0, 1}},
										{{2, 1}, {3, 0}, {3, 1}, {2, 0}, {2, 0}, {2, 0}, {1, 1}, {2, 1}, {1, 0}, {2, 1}, {1, 1}, {1, 1}, {1, 1}},
										{{4, 0}, {3, 0}, {2, 1}, {3, 1}, {2, 0}, {1, 1}, {2, 0}, {1, 1}, {1, 1}, {1, 0}, {1, 1}, {1, 1}, {1, 1}},
										{{4, 0}, {2, 1}, {3, 0}, {2, 1}, {3, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 1}, {1, 0}, {1, 1}, {1, 0}},
										{{4, 0}, {4, 0}, {4, 0}, {2, 0}, {3, 0}, {3, 1}, {2, 0}, {2, 1}, {3, 1}, {1, 0}, {2, 1}, {2, 1}, {2, 1}}};

		public static BattleReport getResult(double powerRatio, TerrainType tType, int diceRollModifier, int columnShift, int initialRoll){
			double maxOdds = 0;
				switch (tType){
					case URBAN: maxOdds = 10.0;
						break;
					case MOUNTAIN: maxOdds = 9.0;
						break;
					case HIGHLAND_WOODS:
					case HIGHLAND: maxOdds = 8.0;
						break;
					case ROUGH_WOODS:
					case ROUGH:
					case MARSH: maxOdds = 7.0;
						break;
					case FLAT_WOODS:
					case FLAT: maxOdds = 6.0;
						break;
				}
			// following if-else chain ensures that powerRatio is mappable
			if(powerRatio < 1/2)
			{
				if(powerRatio > 1/3)
				{
					diceRollModifier += 1;
				}
				powerRatio = 1/3;
			}
			else if (powerRatio < 1)
			{
				powerRatio = 1/2;
				diceRollModifier += 1;
			}
			else if (powerRatio < 1.5)
			{
				powerRatio = 1;
				diceRollModifier += 1;
			}
			else if (powerRatio < 2)
			{
				powerRatio = 1.5;
				diceRollModifier += 1;
			}
			else if (powerRatio > maxOdds)
			{
				powerRatio = maxOdds;
				diceRollModifier += 1;
			}
			else if (powerRatio != Math.floor(powerRatio))
			{
				powerRatio = Math.floor(powerRatio);
				diceRollModifier += 1;
			}

			int column = oddsForUrban.get(powerRatio);

			switch(tType){
				case FLAT_WOODS:
				case FLAT: column += 1;
				case ROUGH_WOODS:
				case ROUGH:
				case MARSH: column += 1;
				case HIGHLAND_WOODS:
				case HIGHLAND: column += 1;
				case MOUNTAIN: column += 1;
				case URBAN: break;
			}
		}
	}
}