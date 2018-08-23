package db;

import models.Symbol;
import models.SymbolCategory;

import java.util.List;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Symbol.class);
        DBHelper.deleteAll(SymbolCategory.class);

        SymbolCategory fruitCategory = new SymbolCategory("Fruit");
        DBHelper.save(fruitCategory);
        SymbolCategory travelCategory = new SymbolCategory("Travel");
        DBHelper.save(travelCategory);
        Symbol symbol1 = new Symbol("Banana", fruitCategory, "www.bananas.com");
        DBHelper.save(symbol1);
        Symbol symbol2 = new Symbol("Orange", fruitCategory, "www.oranges.com");
        DBHelper.save(symbol2);
        Symbol symbol3 = new Symbol("Car", travelCategory, "www.cars.com");
        DBHelper.save(symbol3);

//        List<SymbolCategory> allCategories = DBHelper.getAll(SymbolCategory.class);
//        List<Symbol> allSymbols = DBHelper.getAll(Symbol.class);
//        List<Symbol> fruitSymbols = DBHelper.findSymbolsInThisCategory(fruitCategory);
//        List<Symbol> travelSymbols = DBHelper.findSymbolsInThisCategory(travelCategory);
    }
}
