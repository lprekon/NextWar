public class BattleResult{
	public int attackerStepLoss=0;
	public int defenderStepLoss=0;
	public boolean defenderRetreat=false;
	public boolean redZone = false;

	public BattleResult(int attackerStepLoss, int defenderStepLoss, boolean defenderRetreat, boolean redZone){
		this.attackerStepLoss = attackerStepLoss;
		this.defenderStepLoss = defenderStepLoss;
		this.defenderRetreat = defenderRetreat;
		this.redZone = redZone;
	}
}