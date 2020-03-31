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
			Arguments.of(1.0, TerrainType.FLAT, 0, 0, new BattleResult(0, 1, true, false)),
			Arguments.of(2.0, TerrainType.URBAN, 9, -2, new BattleResult(3, 1, false, false)),
			Arguments.of(4.0, TerrainType.HIGHLAND, 3, 2, new BattleResult(1, 2, true, true)));
	}

	@ParameterizedTest
	@MethodSource("testCombatResultsFactory")
	void testCRT(double powerRatio, TerrainType tType, int modifiedRoll, int columnShift, BattleResult correctAnswer){
		assertEquals(correctAnswer, UnitStack.CombatResultsTable.getResult(powerRatio, tType, modifiedRoll, columnShift));
	}
}