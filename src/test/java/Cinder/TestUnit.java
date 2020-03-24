import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void preventInvalidStepLosss(){
    	assertThrows(IllegalStateException.class, ()->emptyUnit.takeStepLoss(), "Undefined units should not be able to take step losses");
    	smallUnit.takeStepLoss();
    	largeUnit.takeStepLoss();
    	largeUnit.takeStepLoss();
    	assertAll("Dead units cannot take step losses",
    		()->assertThrows(IllegalStateException.class, ()->smallUnit.takeStepLoss(), "Dead small units should not be able to take step losses"),
    		()->assertThrows(IllegalStateException.class, ()->largeUnit.takeStepLoss(), "Dead large units should not be able to take step losses"));
    }

    @Test
    void updateStatusOnStepLoss(){
    	assertEquals(UnitStatus.HEALTHY, largeUnit.getStatus());
    	assertEquals(UnitStatus.STEP_LOSS_TAKEN, largeUnit.takeStepLoss());
    	assertEquals(UnitStatus.DEAD, largeUnit.takeStepLoss());
    	assertEquals(UnitStatus.HEALTHY, smallUnit.getStatus());
    	assertEquals(UnitStatus.DEAD, smallUnit.takeStepLoss());
    }

    @Test
    void testGetAttack(){
    	assertAll("Starting stats",
    		()->assertEquals(1, smallUnit.getAttack()),
    		()->assertEquals(9, largeUnit.getAttack()));
    	largeUnit.takeStepLoss();
    	assertEquals(5, largeUnit.getAttack());
    }

    @Test
    void testGetDefense(){
    	assertAll("Starting stats",
    		()->assertEquals(2, smallUnit.getDefense()),
    		()->assertEquals(10, largeUnit.getDefense()));
    	largeUnit.takeStepLoss();
    	assertEquals(6, largeUnit.getDefense());
    }

    @Test
    void testGetTotalMovement(){
    	assertAll("Starting stats",
    		()->assertEquals(3, smallUnit.getTotalMovement()),
    		()->assertEquals(11, largeUnit.getTotalMovement()));
    	largeUnit.takeStepLoss();
    	assertEquals(7, largeUnit.getTotalMovement());
    }

    @Test
    void testGetEfficiancy(){
    	assertAll("Starting stats",
    		()->assertEquals(4, smallUnit.getEfficiancy()),
    		()->assertEquals(12, largeUnit.getEfficiancy()));
    	largeUnit.takeStepLoss();
    	assertEquals(8, largeUnit.getEfficiancy());
    }

    @Test
    void testGetStackingPoints(){
    	assertAll("Starting stats",
    		()->assertEquals(.5, smallUnit.getStackingPoints()),
    		()->assertEquals(4, largeUnit.getStackingPoints()));
    	largeUnit.takeStepLoss();
    	assertEquals(2, largeUnit.getStackingPoints());
    }
}