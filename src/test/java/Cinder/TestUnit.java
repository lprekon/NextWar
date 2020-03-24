import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class TestUnit {

    @Test
    void gettersWithEmptyUnit() {
        Unit emptyUnit = new Unit();
        assertEquals("Empty Unit", emptyUnit.getName());
        assertEquals(UnitStatus.DEAD, emptyUnit.getStatus());
    }

    @Test
    void moveEmptyUnit(){
    	Unit emptyUnit = new Unit();
    	assertThrows(UnsupportedOperationException.class, ()->emptyUnit.subtractMovement(1));
    }
}