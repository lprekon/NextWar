import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TestUnit {
	private Unit emptyUnit;
	private Unit smallUnit;
	private Unit largeUnit;

	@BeforeEach
	void init(){
		emptyUnit = new Unit();
		//args: name, attack, defense, movement, efficiancy, SP
		smallUnit = new Unit("Small Unit", 1, 2, 3, 4, .5);
		//args: name, attack, defense, movement, efficiancy, SP, r-attack, r-defence, r-movement, r-efficiancy, r-sp
		largeUnit = new Unit("Large Unit", 9, 10 ,11, 12, 4.0, 5, 6, 7, 8, 2.0);
	}

    @Test
    void testGetName() {
        assertEquals("Empty Unit", emptyUnit.getName());
        assertEquals("Small Unit", smallUnit.getName());
        assertEquals("Large Unit", largeUnit.getName());
    }

    @Test
    void testGetStatus(){
    	assertEquals(UnitStatus.UNDEFINED, emptyUnit.getStatus());
    	assertEquals(UnitStatus.HEALTHY, smallUnit.getStatus());
    	assertEquals(UnitStatus.HEALTHY, largeUnit.getStatus());
    }
}