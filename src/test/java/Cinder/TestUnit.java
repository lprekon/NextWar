import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class TestUnit {

    @Test
    void gettersWithEmptyUnit() {
        Unit emptyUnit = new Unit();
        assertEquals("Empty Unit", emptyUnit.getName());
        assertEquals(UnitStatus.DEAD, emptyUnit.getStatus());
    }

}