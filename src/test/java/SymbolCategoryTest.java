import models.SymbolCategory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SymbolCategoryTest {
    private SymbolCategory category;

    @Before
    public void before(){
        category = new SymbolCategory("Food");
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
}
