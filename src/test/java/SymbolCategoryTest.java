import models.Symbol;
import models.SymbolCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SymbolCategoryTest {
    private SymbolCategory category;
    private Symbol symbol;

    @Before
    public void before(){
        category = new SymbolCategory("<i class=\"fas fa-utensils\"></i>","Food");
        symbol = new Symbol("Banana", category, "www.bananapics.co.uk", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
    }

    @Test
    public void getCategory() {
        assertEquals("Food", category.getDescriptor());
    }

    @Test
    public void setDescriptor() {
        category.setDescriptor("Travel");
        assertEquals("Travel", category.getDescriptor());
    }

    @Test
    public void getSymbols() {
        assertEquals(0, category.getSymbols().size());
    }

    @Test
    public void setSymbols() {
        List<Symbol> listOfSymbols = new ArrayList<Symbol>();
        listOfSymbols.add(symbol);
        category.setSymbols(listOfSymbols);
        assertEquals(1, category.getSymbols().size());
    }

    @Test
    public void addThisSymbol(){
        category.addThisSymbol(symbol);
        assertEquals(1, category.getSymbols().size());
    }
}
