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

        DBHelper.createBlankCategory();

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

        Symbol symbol1 = new Symbol("Banana", category1, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/banana+(1).png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
        DBHelper.save(symbol1);

        Symbol symbol2 = new Symbol("Orange", category1, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/orange.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
        DBHelper.save(symbol2);

        Symbol symbol3 = new Symbol("Car", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/car.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
        DBHelper.save(symbol3);

        Symbol symbol4 = new Symbol("Bike", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bike.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
        DBHelper.save(symbol4);

        Symbol symbol5 = new Symbol("Aeroplane", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bike.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
        DBHelper.save(symbol5);


        DBHelper.addSymbolToTimetable(timetable, symbol1);

        DBHelper.addSymbolToTimetable(timetable, symbol1);

        DBHelper.addSymbolToTimetable(timetable, symbol2);

        DBHelper.addSymbolToTimetable(timetable, symbol3);

        DBHelper.addSymbolToTimetable(timetable, symbol4);

        DBHelper.addSymbolToTimetable(timetable, symbol4);

        Timetable timetable2 = new Timetable("Relax day");
        DBHelper.save(timetable2);

        DBHelper.addSymbolToTimetable(timetable2, symbol1);
        DBHelper.addSymbolToTimetable(timetable2, symbol1);

        DBHelper.addTimetableToUser(timetable, digory);
        DBHelper.addTimetableToUser(timetable2, vicky);

        List<Symbol> top3mostUsed = DBHelper.findTopThreeMostUsedSymbols(digory);

   //     DBHelper.setBlankAllOccurrencesOfThisSymbol(symbol1);

 //       DBHelper.deleteUser(digory);

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
