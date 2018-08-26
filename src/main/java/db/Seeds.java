package db;

import models.Symbol;
import models.SymbolCategory;
//import models.SymbolRank;
import models.Timetable;
import models.User;

import java.util.List;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Timetable.class);
        DBHelper.deleteAll(User.class);
        DBHelper.deleteAll(Symbol.class);
        DBHelper.deleteAll(SymbolCategory.class);

        User digory = new User("Digory");
        DBHelper.save(digory);

        User vicky = new User("Vicky");
        DBHelper.save(vicky);

        Timetable timetable = new Timetable("Party Day");
        DBHelper.save(timetable);

        SymbolCategory category1 = new SymbolCategory( "fas fa-utensils", "Food");
        DBHelper.save(category1);

        SymbolCategory category2 = new SymbolCategory("fas fa-coffee","Drinks");
        DBHelper.save(category2);

        SymbolCategory category3 = new SymbolCategory("fas fa-map-marked-alt","Places");
        DBHelper.save(category3);

        SymbolCategory category4 = new SymbolCategory("fas fa-walking","Activities");
        DBHelper.save(category4);

        SymbolCategory category5 = new SymbolCategory("far fa-smile","Feelings");
        DBHelper.save(category5);

        SymbolCategory category6 = new SymbolCategory("fas fa-users","People");
        DBHelper.save(category6);

        SymbolCategory category7 = new SymbolCategory("fas fa-car-side", "Travel");
        DBHelper.save(category7);

        Symbol symbol1 = new Symbol("Banana", category1, "www.bananas.com");
        DBHelper.save(symbol1);

        Symbol symbol2 = new Symbol("Orange", category1, "www.oranges.com");
        DBHelper.save(symbol2);

        Symbol symbol3 = new Symbol("Car", category7, "www.cars.com");
        symbol3.increasePopularity();
        DBHelper.save(symbol3);


        DBHelper.addSymbolToTimetable(timetable, symbol1);

        DBHelper.addSymbolToTimetable(timetable, symbol2);

        DBHelper.addSymbolToTimetable(timetable, symbol1);

        Timetable timetable2 = new Timetable("Relax day");
        DBHelper.save(timetable2);

        DBHelper.addTimetableToUser(timetable, digory);
        DBHelper.addTimetableToUser(timetable2, vicky);

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
