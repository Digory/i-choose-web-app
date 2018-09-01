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

        Timetable funDay = new Timetable("Fun Day");
        DBHelper.save(funDay);

        Timetable relaxDay = new Timetable("Relax day");
        DBHelper.save(relaxDay);

        Timetable schoolDay = new Timetable("School day");
        DBHelper.save(schoolDay);

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

        Symbol aeroplane = new Symbol("Aeroplane", categoryTravel, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/travel_symbols/aeroplane.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/aeroplane.wav");
        Symbol angry = new Symbol("Angry", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/feelings_symbols/angry.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/angry.wav");
        Symbol appleJuice = new Symbol("Apple Juice", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/drink_symbols/apple_juice.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/apple_juice.wav");
        Symbol banana = new Symbol("Banana", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/banana.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/banana.wav");
        Symbol bath = new Symbol("Bath", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/bath.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/bath.wav");
        Symbol bed = new Symbol("Bed", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/bed.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/bed.wav");
        Symbol biscuit = new Symbol("Biscuit", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/biscuit.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/biscuit.wav");
        Symbol book = new Symbol("Book", categoryActivities, "hhttps://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/book.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-filesbook.wav");
        Symbol breakfast = new Symbol("Breakfast", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/breakfast.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/breakfast.wav");
        Symbol brushTeeth = new Symbol("Brush Teeth", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/brush_teeth.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/brush_teeth.wav");
        Symbol bus = new Symbol("Bus", categoryTravel, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/travel_symbols/bus.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/bus.wav");
        Symbol cafe = new Symbol("Cafe", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/places_symbols/cafe.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/cafe.wav");
        Symbol car = new Symbol("Car", categoryTravel, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/travel_symbols/car.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/car.wav");
        Symbol chocolate = new Symbol("Chocolate", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/chocolate.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/chocolate.wav");
        Symbol computer = new Symbol("Computer", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/computer.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/computer.wav");
        Symbol crisps = new Symbol("Crisps", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/crisps.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/crisps.wav");
        Symbol dad = new Symbol("Dad", categoryPeople, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/people_symbols/dad.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/dad.wav");
        Symbol dinner = new Symbol("Dinner", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/dinner.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/dinner.wav");
        Symbol garden = new Symbol("Garden", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/garden.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/garden.wav");
        Symbol getDressed = new Symbol("Get dressed", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/get_dressed.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/get_dressed.wav");
        Symbol happy = new Symbol("Happy", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/feelings_symbols/happy.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/happy.wav");
        Symbol home = new Symbol("Home", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/places_symbols/home.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/home.wav");
        Symbol lunch = new Symbol("Lunch", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/lunch.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/lunch.wav");
        Symbol milk = new Symbol("Milk", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/drink_symbols/milk.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/milk.wav");
        Symbol mum = new Symbol("Mum", categoryPeople, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/people_symbols/mum.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/mum.wav");
        Symbol orange = new Symbol("Orange", categoryFood, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/food_symbols/orange.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/orange.wav");
        Symbol orangeJuice = new Symbol("Orange Juice", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/drink_symbols/orange_juice.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/orange_juice.wav");
        Symbol play = new Symbol("Play", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/play.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/play.wav");
        Symbol playground = new Symbol("Playground", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/places_symbols/playground.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/playground.wav");
        Symbol puzzle = new Symbol("Puzzle", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/puzzle.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/puzzle.wav");
        Symbol sad = new Symbol("Sad", categoryFeelings, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/feelings_symbols/sad.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/sad.wav");
        Symbol school = new Symbol("School", categoryPlaces, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/places_symbols/school.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/school.wav");
        Symbol shower = new Symbol("Shower", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/shower.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/shower.wav");
        Symbol softPlay = new Symbol("Soft Play", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/soft_play.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/soft_play.wav");
        Symbol television = new Symbol("Television", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/television.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/television.wav");
        Symbol toilet = new Symbol("Toilet", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/activities_symbols/toilet.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/toilet.wav");
        Symbol train = new Symbol("Train", categoryTravel, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/travel_symbols/train.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/train.wav");
        Symbol tram = new Symbol("Tram", categoryTravel, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/travel_symbols/tram.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/tram.wav");
        Symbol water = new Symbol("Water", categoryDrinks, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/drink_symbols/water.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/water.wav");
        Symbol zoo = new Symbol("Zoo", categoryActivities, "https://s3-eu-west-1.amazonaws.com/i-choose-symbols/places_symbols/zoo.png", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/zoo.wav");
        Symbol colin = new Symbol("Colin" , categoryPeople,"https://s3-eu-west-1.amazonaws.com/i-choose-symbols/people_symbols/colin.jpg", "https://s3-eu-west-1.amazonaws.com/i-choose-sound-files/colin.wav");
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

        DBHelper.addTimetableToUser(schoolDay, vicky);
        DBHelper.addTimetableToUser(relaxDay, vicky);
        DBHelper.addTimetableToUser(funDay, digory);

        DBHelper.addSymbolToTimetable(schoolDay, shower);
        DBHelper.addSymbolToTimetable(schoolDay, getDressed);
        DBHelper.addSymbolToTimetable(schoolDay, breakfast);
        DBHelper.addSymbolToTimetable(schoolDay, brushTeeth);
        DBHelper.addSymbolToTimetable(schoolDay, car);
        DBHelper.addSymbolToTimetable(schoolDay, school);
        DBHelper.addSymbolToTimetable(schoolDay, car);
        DBHelper.addSymbolToTimetable(schoolDay, home);
        DBHelper.addSymbolToTimetable(schoolDay, dinner);
        DBHelper.addSymbolToTimetable(schoolDay, play);
        DBHelper.addSymbolToTimetable(schoolDay, brushTeeth);
        DBHelper.addSymbolToTimetable(schoolDay, bed);

        DBHelper.addSymbolToTimetable(relaxDay, breakfast);
        DBHelper.addSymbolToTimetable(relaxDay, brushTeeth);
        DBHelper.addSymbolToTimetable(relaxDay, play);
        DBHelper.addSymbolToTimetable(relaxDay, lunch);
        DBHelper.addSymbolToTimetable(relaxDay, play);
        DBHelper.addSymbolToTimetable(relaxDay, dinner);
        DBHelper.addSymbolToTimetable(relaxDay, television);
        DBHelper.addSymbolToTimetable(relaxDay, bath);
        DBHelper.addSymbolToTimetable(relaxDay, brushTeeth);
        DBHelper.addSymbolToTimetable(relaxDay, bed);

        DBHelper.addSymbolToTimetable(funDay, breakfast);
        DBHelper.addSymbolToTimetable(funDay, zoo);
        DBHelper.addSymbolToTimetable(funDay, lunch);
        DBHelper.addSymbolToTimetable(funDay, softPlay);
        DBHelper.addSymbolToTimetable(funDay, dinner);
        DBHelper.addSymbolToTimetable(funDay, television);

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
