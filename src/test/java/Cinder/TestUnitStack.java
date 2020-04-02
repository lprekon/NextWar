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
	

	@Test
	void simpleCombat(){
		assertEquals(new BattleResult(1, 2, true, false, 3, 7), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.URBAN, 0, 0));
	}

	@Test
	void terrainShift_5_0(){
		assertEquals(new BattleResult(1, 2, true, false, 3, 7), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.URBAN, 0, 0));
		assertEquals(new BattleResult(0, 1, true, false, 3, 8), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.MOUNTAIN, 0, 0));
		assertEquals(new BattleResult(0, 2, true, false, 3, 9), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.HIGHLAND, 0, 0));
		assertEquals(new BattleResult(1, 3, true, true, 3, 10), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.ROUGH, 0, 0));
		assertEquals(new BattleResult(1, 2, true, true, 3, 11), UnitStack.CombatResultsTable.getResult(5.0, TerrainType.FLAT, 0, 0));
	}

	@Test
	void columnShift(){
		assertEquals(new BattleResult(0, 1, true, false, 3, 6), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, 0), "Start");
		assertAll(()->assertEquals(new BattleResult(1, 2, true, false, 3, 7), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, 1), "Column +"),
			()->assertEquals(new BattleResult(0, 1, true, false, 3, 5), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, -1), "Column -"));
	}

	@Test
	void fullFlat(){
		assertAll(()->assertEquals(new BattleResult(0, 1, true, false, 3, 6), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, 0), "Start"),
			()->assertEquals(new BattleResult(1, 1, true, false, 5, 6), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 2, 0), "DRM +"),
			()->assertEquals(new BattleResult(0, 2, true, false, 1, 6), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, -2, 0), "DRM -"),
			()->assertEquals(new BattleResult(1, 2, true, false, 3, 7), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, 1), "Column +"),
			()->assertEquals(new BattleResult(1, 1, true, false, 3, 4), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.FLAT, 0, -2), "Column -"));
	}

	@Test
	void fullUrban(){
		assertAll(()->assertEquals(new BattleResult(1, 1, false, false, 3, 2), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.URBAN, 0, 0), "Start"),
			()->assertEquals(new BattleResult(1, 2, false, false, 5, 2), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.URBAN, 1, 0), "DRM +"),
			()->assertEquals(new BattleResult(1, 1, true, false, 1, 2), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.URBAN, -2, 0), "DRM -"),
			()->assertEquals(new BattleResult(1, 1, true, false, 3, 3), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.URBAN, 0, 1), "Column +"),
			()->assertEquals(new BattleResult(1, 1, false, false, 3, 1), UnitStack.CombatResultsTable.getResult(1.0, TerrainType.URBAN, 0, -1), "Column -"));
	}
}