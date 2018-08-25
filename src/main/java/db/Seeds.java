package db;

import models.Symbol;
import models.SymbolCategory;
//import models.SymbolRank;
import models.Timetable;

import java.util.List;

public class Seeds {

    public static void seedData(){
    //    DBHelper.deleteAll(SymbolRank.class);

        DBHelper.deleteAll(SymbolCategory.class);
        DBHelper.deleteAll(Symbol.class);
        DBHelper.deleteAll(Timetable.class);



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

        Symbol symbol1Copy = new Symbol(symbol1.getName(), symbol1.getCategory(), symbol1.getImageUrl());
        DBHelper.save(symbol1Copy);
        Symbol symbol2Copy = new Symbol(symbol2.getName(), symbol2.getCategory(), symbol2.getImageUrl());
        DBHelper.save(symbol2Copy);

        DBHelper.associateTimetableWithSymbol(timetable, symbol1Copy);

        DBHelper.associateTimetableWithSymbol(timetable, symbol2Copy);

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
