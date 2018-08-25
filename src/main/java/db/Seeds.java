package db;

import models.Symbol;
import models.SymbolCategory;
//import models.SymbolRank;
import models.Timetable;

import java.util.List;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Timetable.class);
        DBHelper.deleteAll(Symbol.class);
        DBHelper.deleteAll(SymbolCategory.class);

        Timetable timetable = new Timetable("Party Day");
        DBHelper.save(timetable);

        SymbolCategory category1 = new SymbolCategory("Food");
        DBHelper.save(category1);

        SymbolCategory category2 = new SymbolCategory("Drink");
        DBHelper.save(category2);

        SymbolCategory category3 = new SymbolCategory("Places");
        DBHelper.save(category3);

        SymbolCategory category4 = new SymbolCategory("Activities");
        DBHelper.save(category4);

        SymbolCategory category5 = new SymbolCategory("Things");
        DBHelper.save(category5);

        SymbolCategory category6 = new SymbolCategory("Feelings");
        DBHelper.save(category6);

        SymbolCategory category7 = new SymbolCategory("People");
        DBHelper.save(category7);

        SymbolCategory category8 = new SymbolCategory("Travel");
        DBHelper.save(category8);

        Symbol symbol1 = new Symbol("Banana", category1, "www.bananas.com");
        DBHelper.save(symbol1);

        Symbol symbol2 = new Symbol("Orange", category1, "www.oranges.com");
        DBHelper.save(symbol2);

        Symbol symbol3 = new Symbol("Car", category8, "www.cars.com");
        symbol3.increasePopularity();
        DBHelper.save(symbol3);


        DBHelper.addSymbolToTimetable(timetable, symbol1);

        DBHelper.addSymbolToTimetable(timetable, symbol2);

        DBHelper.addSymbolToTimetable(timetable, symbol1);

        Timetable timetable2 = new Timetable("Relax day");
        DBHelper.save(timetable2);

 //       DBHelper.getRankOfSymbol(symbol1, timetable);

//        List<Symbol> symbolsOfTimetable = DBHelper.getAllSymbolsForTimetable(timetable);
//
//        DBHelper.increaseRankingOfSymbolInTimetable(symbol2, timetable);
//
//        List<Symbol> orderedSymbolsOfTimetable = DBHelper.orderSymbolsByRank(timetable);

//        List<SymbolCategory> allCategories = DBHelper.getAll(SymbolCategory.class);
//        List<Symbol> allSymbols = DBHelper.getAll(Symbol.class);
//        List<Symbol> fruitSymbols = DBHelper.findSymbolsInThisCategory(fruitCategory);
//        List<Symbol> travelSymbols = DBHelper.findSymbolsInThisCategory(travelCategory);
    }
}
