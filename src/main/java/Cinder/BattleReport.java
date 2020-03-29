public class BattleReport extends BattleResult{
	public double powerRatio=0;
	public int efficiancyDifference=0;
	public int diceRollModifier=0;
	public int netColumnShift=0;
	public UnitStack attacker;
	public UnitStack defender;

	public BattleReport(BattleResult template){
		super(template.attackerStepLoss, template.defenderStepLoss, template.defenderRetreat, template.redZone);
	}
}