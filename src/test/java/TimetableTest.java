import models.Symbol;
import models.SymbolCategory;
import models.Timetable;
import models.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TimetableTest {


    private Symbol symbol;
    private SymbolCategory category;
    private Timetable timetable;
   // private User user;

    @Before
    public void before() {
      //  user = new User("Jennifer");
        category = new SymbolCategory("<i class=\"fas fa-utensils\"></i>", "Food");
        symbol = new Symbol("Banana", category, "www.nicepictures.com");
        timetable = new Timetable("Sports Day");
    }

    @Test
    public void canGetName() {
        assertEquals("Sports Day", timetable.getName());
    }

    @Test
    public void canSetName(){
        timetable.setName("Aunty Carol's Wedding");
        assertEquals("Aunty Carol's Wedding", timetable.getName());
    }

    @Test
    public void symbolsStartsEmpty() {
        assertEquals(0, timetable.getSymbols().size());
    }

    @Test
    public void canAddSymbols() {
        timetable.addSymbol(symbol);
        assertEquals(1, timetable.getSymbols().size());
    }

}
