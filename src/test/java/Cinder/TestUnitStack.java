import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUnitStack{
	
	static Stream<Arguments> testCombatResultsFactory(){
		return Stream.of(
			Arguments.of(1.0, TerrainType.FLAT, 0, 0, new BattleResult(0, 1, true, false, 3, 6)),
			Arguments.of(2.0, TerrainType.URBAN, 9, -2, new BattleResult(3, 1, false, false, 12, 2)),
			Arguments.of(4.0, TerrainType.HIGHLAND, 3, 2, new BattleResult(1, 2, true, true, 6, 10)),
			Arguments.of(4.1, TerrainType.HIGHLAND_WOODS, 3, 2, new BattleResult(0, 2, true, true, 5, 10)),
			Arguments.of(1.5, TerrainType.ROUGH, -2, 2, new BattleResult(1, 3, true, false, 1, 8)),
			Arguments.of(.5, TerrainType.MOUNTAIN, 1, 1, new BattleResult(1, 1, false, false, 4, 3)),
			Arguments.of(1, TerrainType.MOUNTAIN, 1, 0, new BattleResult(1, 1, false, false, 4, 3)),
			Arguments.of(1.0/3, TerrainType.URBAN, -3, 0, new BattleResult(1, 1, true, false, 0, 0)),
			Arguments.of(1.0/3, TerrainType.FLAT_WOODS, -3, 0, new BattleResult(0, 2, true, false, 0, 4)));
	}

	@ParameterizedTest
	@MethodSource("testCombatResultsFactory")
	void testCRT(double powerRatio, TerrainType tType, int modifiedRoll, int columnShift, BattleResult correctAnswer){
		assertEquals(correctAnswer, UnitStack.CombatResultsTable.getResult(powerRatio, tType, modifiedRoll, columnShift));
	}
}