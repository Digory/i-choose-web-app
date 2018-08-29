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
//
//        Timetable timetable = new Timetable("Party Day");
//        DBHelper.save(timetable);

        SymbolCategory categoryFood = new SymbolCategory( "fas fa-utensils", "Food");
        DBHelper.save(categoryFood);

        SymbolCategory categoryDrinks = new SymbolCategory("fas fa-coffee","Drinks");
        DBHelper.save(categoryDrinks);

        SymbolCategory categoryPlaces = new SymbolCategory("fas fa-map-marked-alt","Places");
        DBHelper.save(categoryPlaces);

        SymbolCategory categoryActivities = new SymbolCategory("fas fa-walking","Activities");
        DBHelper.save(categoryActivities);

        SymbolCategory categoryFeelings = new SymbolCategory("far fa-smile","Feelings");
        DBHelper.save(categoryFeelings);

        SymbolCategory categoryPeople = new SymbolCategory("fas fa-users","People");
        DBHelper.save(categoryPeople);

        SymbolCategory categoryTravel = new SymbolCategory("fas fa-car-side", "Travel");
        DBHelper.save(categoryTravel);

        Symbol aeroplane = new Symbol("Aeroplane", categoryTravel, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/aeroplane.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/aeroplane.wav");
        Symbol angry = new Symbol("Angry", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/angry.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/angry.wav");
        Symbol appleJuice = new Symbol("Apple Juice", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/apple_juice.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/apple_juice.wav");
        Symbol banana = new Symbol("Banana", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/banana.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/banana.wav");
        Symbol bath = new Symbol("Bath", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bath.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/bath.wav");
        Symbol bed = new Symbol("Bed", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bed.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/bed.wav");
        Symbol biscuit = new Symbol("Biscuit", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/biscuit.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/biscuit.wav");
        Symbol book = new Symbol("Book", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/book.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/book.wav");
        Symbol breakfast = new Symbol("Breakfast", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/breakfast.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/breakfast.wav");
        Symbol brushTeeth = new Symbol("Brush Teeth", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/brush_teeth.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/brush_teeth.wav");
        Symbol bus = new Symbol("Bus", categoryTravel, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bus.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/bus.wav");
        Symbol cafe = new Symbol("Cafe", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/cafe.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/cafe.wav");
        Symbol car = new Symbol("Car", categoryTravel, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/car.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/car.wav");
        Symbol chocolate = new Symbol("Chocolate", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/chocolate.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/chocolate.wav");
        Symbol computer = new Symbol("Computer", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/computer.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/computer.wav");
        Symbol crisps = new Symbol("Crisps", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/crisps.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/crisps.wav");
        Symbol dad = new Symbol("Dad", categoryPeople, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/dad.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/dad.wav");
        Symbol dinner = new Symbol("Dinner", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/dinner.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/dinner.wav");
        Symbol garden = new Symbol("Garden", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/garden.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/garden.wav");
        Symbol getDressed = new Symbol("Get dressed", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/get_dressed.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/get_dressed.wav");
        Symbol happy = new Symbol("Happy", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/happy.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/happy.wav");
        Symbol home = new Symbol("Home", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/home.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/home.wav");
        Symbol lunch = new Symbol("Lunch", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/lunch.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/lunch.wav");
        Symbol milk = new Symbol("Milk", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/milk.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/milk.wav");
        Symbol mum = new Symbol("Mum", categoryPeople, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/mum.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/mum.wav");
        Symbol orange = new Symbol("Orange", categoryFood, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/orange.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/orange.wav");
        Symbol orangeJuice = new Symbol("Orange Juice", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/orange_juice.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/orange_juice.wav");
        Symbol play = new Symbol("Play", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/play.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/play.wav");
        Symbol playground = new Symbol("Playground", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/playground.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/playground.wav");
        Symbol puzzle = new Symbol("Puzzle", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/puzzle.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/puzzle.wav");
        Symbol sad = new Symbol("Sad", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sad.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/sad.wav");
        Symbol school = new Symbol("School", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/school.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/school.wav");
        Symbol shower = new Symbol("Shower", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/shower.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/shower.wav");
        Symbol softPlay = new Symbol("Soft Play", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/soft_play.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/soft_play.wav");
        Symbol television = new Symbol("Television", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/television.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/television.wav");
        Symbol toilet = new Symbol("Toilet", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/toilet.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/toilet.wav");
        Symbol train = new Symbol("Train", categoryTravel, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/train.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/train.wav");
        Symbol tram = new Symbol("Tram", categoryTravel, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/tram.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/tram.wav");
        Symbol water = new Symbol("Water", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/water.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/water.wav");
        Symbol zoo = new Symbol("Zoo", categoryActivities, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/zoo.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/zoo.wav");
        Symbol colin = new Symbol("Colin" , categoryPeople,"https://s3-eu-west-1.amazonaws.com/ichoose-resources/colin.jpg", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/sounds/colin.wav");
        DBHelper.save(aeroplane);
        DBHelper.save(angry);
        DBHelper.save(appleJuice);
        DBHelper.save(banana);
        DBHelper.save(bath);
        DBHelper.save(bed);
        DBHelper.save(biscuit);
        DBHelper.save(book);
        DBHelper.save(breakfast);
        DBHelper.save(brushTeeth);
        DBHelper.save(bus);
        DBHelper.save(cafe);
        DBHelper.save(car);
        DBHelper.save(chocolate);
        DBHelper.save(computer);
        DBHelper.save(crisps);
        DBHelper.save(dad);
        DBHelper.save(dinner);
        DBHelper.save(garden);
        DBHelper.save(getDressed);
        DBHelper.save(happy);
        DBHelper.save(home);
        DBHelper.save(lunch);
        DBHelper.save(milk);
        DBHelper.save(mum);
        DBHelper.save(orange);
        DBHelper.save(orangeJuice);
        DBHelper.save(play);
        DBHelper.save(playground);
        DBHelper.save(puzzle);
        DBHelper.save(sad);
        DBHelper.save(school);
        DBHelper.save(shower);
        DBHelper.save(softPlay);
        DBHelper.save(television);
        DBHelper.save(toilet);
        DBHelper.save(train);
        DBHelper.save(tram);
        DBHelper.save(water);
        DBHelper.save(zoo);
        DBHelper.save(colin);
        //        Symbol symbol1 = new Symbol("Banana", category1, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/banana+(1).png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
//        DBHelper.save(symbol1);

//        Symbol symbol2 = new Symbol("Orange", category1, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/orange.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
//        DBHelper.save(symbol2);
//
//        Symbol symbol3 = new Symbol("Car", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/car.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
//        DBHelper.save(symbol3);
//
//        Symbol symbol4 = new Symbol("Bike", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bike.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
//        DBHelper.save(symbol4);
//
//        Symbol symbol5 = new Symbol("Aeroplane", category7, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/bike.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
//        DBHelper.save(symbol5);
//
//
//        DBHelper.addSymbolToTimetable(timetable, symbol1);
//
//        DBHelper.addSymbolToTimetable(timetable, symbol1);
//
//        DBHelper.addSymbolToTimetable(timetable, symbol2);
//
//        DBHelper.addSymbolToTimetable(timetable, symbol3);
//
//        DBHelper.addSymbolToTimetable(timetable, symbol4);
//
//        DBHelper.addSymbolToTimetable(timetable, symbol4);
//
//        Timetable timetable2 = new Timetable("Relax day");
//        DBHelper.save(timetable2);
//
//        DBHelper.addSymbolToTimetable(timetable2, symbol1);
//        DBHelper.addSymbolToTimetable(timetable2, symbol1);
//
//        DBHelper.addTimetableToUser(timetable, digory);
//        DBHelper.addTimetableToUser(timetable2, vicky);

   //     List<Symbol> top3mostUsed = DBHelper.findTopThreeMostUsedSymbols(digory);

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
