package db;

import models.Symbol;
import models.SymbolCategory;
import models.Timetable;

import java.util.List;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Timetable.class);
        DBHelper.deleteAll(Symbol.class);
        DBHelper.deleteAll(SymbolCategory.class);

        Timetable timetable = new Timetable("Party Day");
        DBHelper.save(timetable);

        SymbolCategory fruitCategory = new SymbolCategory("Fruit");
        DBHelper.save(fruitCategory);
        SymbolCategory travelCategory = new SymbolCategory("Travel");
        DBHelper.save(travelCategory);
        Symbol symbol1 = new Symbol("Banana", fruitCategory, "www.bananas.com");
        DBHelper.save(symbol1);
        Symbol symbol2 = new Symbol("Orange", fruitCategory, "www.oranges.com");
        DBHelper.save(symbol2);
        Symbol symbol3 = new Symbol("Car", travelCategory, "www.cars.com");
        symbol3.increasePopularity();
        DBHelper.save(symbol3);


        DBHelper.associateTimetableWithSymbol(timetable, symbol1);

        DBHelper.associateTimetableWithSymbol(timetable, symbol2);

        List<Symbol> symbolsOfTimetable = DBHelper.getAllSymbolsForTimetable(timetable);

//        List<SymbolCategory> allCategories = DBHelper.getAll(SymbolCategory.class);
//        List<Symbol> allSymbols = DBHelper.getAll(Symbol.class);
//        List<Symbol> fruitSymbols = DBHelper.findSymbolsInThisCategory(fruitCategory);
//        List<Symbol> travelSymbols = DBHelper.findSymbolsInThisCategory(travelCategory);
    }
}
