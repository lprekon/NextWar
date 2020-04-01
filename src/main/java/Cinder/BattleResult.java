public class BattleResult{
	public int attackerStepLoss=0;
	public int defenderStepLoss=0;
	public boolean defenderRetreat=false;
	public boolean redZone = false;
	public int CRTRow = -1;
	public int CRTColumn = -1;

	public BattleResult(int attackerStepLoss, int defenderStepLoss, boolean defenderRetreat, boolean redZone){
		this.attackerStepLoss = attackerStepLoss;
		this.defenderStepLoss = defenderStepLoss;
		this.defenderRetreat = defenderRetreat;
		this.redZone = redZone;
	}

	public BattleResult(int attackerStepLoss, int defenderStepLoss, boolean defenderRetreat, boolean redZone, int row, int column){
		this.attackerStepLoss = attackerStepLoss;
		this.defenderStepLoss = defenderStepLoss;
		this.defenderRetreat = defenderRetreat;
		this.redZone = redZone;
		this.CRTRow = row;
		this.CRTColumn = column
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof BattleResult))
			return false;
		BattleResult other = (BattleResult) o;
		return this.attackerStepLoss == other.attackerStepLoss && this.defenderStepLoss == other.defenderStepLoss && this.defenderRetreat == other.defenderRetreat && this.redZone == other.redZone;
	}

	@Override
	public String toString(){
		String result = "";
		if(attackerStepLoss!=0)
			result += attackerStepLoss;
		else
			result += "-";
		result += "/";
		if (defenderStepLoss!=0)
			result += defenderStepLoss;
		else
			result += "-";
		if(defenderRetreat)
			result += "R";
		if(redZone)
			result += " RED ZONE";
		return "BattleResult[" + (String)row + "][" + (String)column + "]("+ result + ")";
	}
}