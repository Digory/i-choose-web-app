import models.Symbol;
import models.SymbolCategory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SymbolTest {
    private Symbol symbol;
    private SymbolCategory category;

    @Before
    public void before(){
        category = new SymbolCategory("Food");
        symbol = new Symbol("Banana", category, "www.nicepictures.com");
    }

    @Test
    public void getName() {
        assertEquals("Banana", symbol.getName());
    }

    @Test
    public void setName() {
        symbol.setName("Orange");
        assertEquals("Orange", symbol.getName());
    }

    @Test
    public void getCategory() {
        assertEquals(category, symbol.getCategory());
    }

    @Test
    public void setCategory() {
        SymbolCategory category2 = new SymbolCategory("Travel");
        symbol.setCategory(category2);
        assertEquals(category2, symbol.getCategory());
    }

    @Test
    public void getImageUrl() {
        assertEquals("www.nicepictures.com", symbol.getImageUrl());
    }

    @Test
    public void setImageUrl() {
        symbol.setImageUrl("www.okpictures.org");
        assertEquals("www.okpictures.org", symbol.getImageUrl());
    }

    @Test
    public void getPopularityRating() {
        assertEquals(0, symbol.getPopularityRating());
    }

    @Test
    public void setPopularityRating() {
        symbol.setPopularityRating(3);
        assertEquals(3, symbol.getPopularityRating());
    }

    @Test
    public void increasePopularityRating(){
        symbol.increasePopularity();
        assertEquals(1, symbol.getPopularityRating());
    }


}
