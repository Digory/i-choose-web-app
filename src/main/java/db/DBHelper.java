package db;
import models.Symbol;
import models.SymbolCategory;
import models.Timetable;
import models.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.sql.Time;
import java.util.*;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;
    private static SymbolCategory blank_category;

    public static void createBlankCategory(){
        blank_category = new SymbolCategory( "fas fa-utensils", "Deleted by admin");
        save(blank_category);
    }

    public static void save(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            ;
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) criteria.uniqueResult();
            ;
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> void deleteAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void delete(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getList(cr);
    }


    public static <T> T find(int id, Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        return getUnique(cr);
    }

    public static List<Symbol> findSymbolsInThisCategory(SymbolCategory category){
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Symbol.class);
        cr.add(Restrictions.eq("category", category));
        return getList(cr);
    }

    public static void addSymbolToTimetable(Timetable timetable, Symbol symbol){
        timetable.addSymbol(symbol);
        save(timetable);
    }

    public static void addTimetableToUser(Timetable timetable, User user){
        timetable.setUser(user);
        save(timetable);
    }

    public static List<Symbol> getSortedAlphabetically(List<Symbol> listToSort){
        Collections.sort(listToSort, new Comparator<Symbol>() {
                    @Override
                    public int compare(Symbol symbol1, Symbol symbol2) {
                            return symbol1.getName().compareTo(symbol2.getName());
                    }
    });
    return listToSort;
    }

    // TODO: split this into smaller methods or just do the whole method in a shorter way somehow

    public static List<Symbol> findTopThreeMostUsedSymbols(User user){

        // First, we have to get all the symbols attached to a user.

        List<Symbol> allSymbolsAttachedToUser = new ArrayList<>();

        List<Timetable> allUniqueTimetables = getUniqueTimetablesForUser(user);
        for(Timetable timetable : allUniqueTimetables){
            for(Symbol symbol : timetable.getSymbols()){
                allSymbolsAttachedToUser.add(symbol);
            }
        }

        // Then, because symbols may be duplicated, we make a Set of the symbols
        // so we only have one of each type.

        Set<Symbol> allUniqueSymbolsSet = new HashSet<Symbol>();
        for (Symbol symbol : allSymbolsAttachedToUser) {
            allUniqueSymbolsSet.add(symbol);
        }

        // For the Collections.sort method, we have to turn that Set back into a List.

        List<Symbol> allUniqueSymbols = new ArrayList<>();

        for(Symbol symbol : allUniqueSymbolsSet){
            allUniqueSymbols.add(symbol);
        }

        // The if statement deals with the bug of a user not having at least 3 symbols.

        if(allUniqueSymbols.size() <= 3){
            Symbol placeholder1 = new Symbol("", blank_category, "https://s3-eu-west-1.amazonaws.com/ichoose-resources/default-placeholder.png", "https://s3-eu-west-1.amazonaws.com/ichoose-resources/piano2-CoolEdit.mp3");
            int i;
            int allUniqueSymbolsSize = allUniqueSymbols.size();
            for (i=0; i < (3 - allUniqueSymbolsSize); i++){
                allUniqueSymbols.add(placeholder1);
            }
        }

        // This sorts the symbols in order of how frequently they occur in the original List.
        // If symbols have the same frequency of occurence, they are sorted alphabetically.

        Collections.sort(allUniqueSymbols, new Comparator<Symbol>() {
            @Override
            public int compare(Symbol symbol1, Symbol symbol2) {
                int symbol1Frequency = Collections.frequency(allSymbolsAttachedToUser, symbol1);
                int symbol2Frequency = Collections.frequency(allSymbolsAttachedToUser, symbol2);
                if (symbol1Frequency != symbol2Frequency){
                    return symbol2Frequency - symbol1Frequency;
                }
                else {
                    return symbol1.getName().compareTo(symbol2.getName());
                }
            }
        });

        // Then we get the top 3 and return.

        List<Symbol> top3Symbols = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            top3Symbols.add(allUniqueSymbols.get(i));
        }
        return top3Symbols;
    }

//    public static List<Symbol> getAllSymbolsForTimetable(Timetable timetable){
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<Symbol> results = null;
//        try {
//            Criteria cr = session.createCriteria(Symbol.class);
//            cr.add(Restrictions.eq("timetable.id", timetable.getId()));
//            results = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return results;
//    }


//    public static List<Symbol> getAllUniqueSymbols(){
//        List<Symbol> allSymbols = getAll(Symbol.class);
//        List<Symbol> filteredSymbols = new ArrayList<>();
//        for(Symbol symbol : allSymbols){
//            if(!doesThisListOfSymbolsContainOneWithThisName(symbol.getName(), filteredSymbols)){
//                filteredSymbols.add(symbol);
//            }
//        }
//        return filteredSymbols;
//    }
//
//    public static boolean doesThisListOfSymbolsContainOneWithThisName(String name, List<Symbol> symbols){
//        for(Symbol symbol : symbols){
//            if(symbol.getName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static List<Timetable> getUniqueTimetablesForUser(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Timetable.class);
        cr.add(Restrictions.eq("user.id", user.getId()));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getList(cr);
    }

    public static void setBlankAllOccurrencesOfThisSymbol(Symbol symbol){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Timetable> allTimetables = getAll(Timetable.class);

        for(Timetable timetable : allTimetables){
            if(timetable.getSymbols() != null){
            for(Symbol each_symbol : timetable.getSymbols()){
                if(symbol.getName().equals(each_symbol.getName())){
                    each_symbol.setCategory(blank_category);
                    each_symbol.setImageUrl("https://s3-eu-west-1.amazonaws.com/ichoose-resources/default-placeholder.png");
                    save(each_symbol);
                }
            }}
        }
        symbol.setCategory(blank_category);
        symbol.setImageUrl("https://s3-eu-west-1.amazonaws.com/ichoose-resources/default-placeholder.png");
        save(symbol);
    }


    public static void deleteUser(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Timetable.class);
        cr.add(Restrictions.eq("user.id", user.getId()));
        List<Timetable> timetablesAttachedToUser = getList(cr);
        for(Timetable timetable : timetablesAttachedToUser){
            timetable.setSymbols(null);
            save(timetable);
            save(user);
        }
        delete(user);
    }
//
//    public static List<Timetable> getUniqueTimetablesForUser(User user){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Criteria cr = session.createCriteria(Timetable.class);
//        cr.add(Restrictions.eq("user.id", user.getId()));
//        List<Timetable> timetables = getList(cr);
//        List<Timetable> filteredTimetables = new ArrayList<>();
//        for(Timetable timetable : timetables){
//            if(!doesThisListOfTimetablesContainOneWithThisName(timetable.getName(), filteredTimetables)){
//                filteredTimetables.add(timetable);
//            }
//        }
//        return filteredTimetables;
//    }
//
//    public static boolean doesThisListOfTimetablesContainOneWithThisName(String name, List<Timetable> timetables){
//        for(Timetable timetable : timetables){
//            if(timetable.getName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static List<Symbol> searchForSymbol(String keyword){
        List<Symbol> allSymbols = getAll(Symbol.class);
        List<Symbol> toBeRemoved = new ArrayList<>();
        for(Symbol symbol : allSymbols){
            if(symbol.getCategory().getDescriptor().equals(blank_category.getDescriptor())){
                toBeRemoved.add(symbol);
            }
        }
        allSymbols.removeAll(toBeRemoved);
        if(keyword.isEmpty()){
            return allSymbols;
        }
        List<Symbol> results = new ArrayList<>();
        for(Symbol symbol : allSymbols){
            if(symbol.getName().toLowerCase().equals(keyword.toLowerCase()) ||
                    symbol.getCategory().getDescriptor().toLowerCase().equals(keyword.toLowerCase())){
                results.add(symbol);
            }
        }
        getSortedAlphabetically(results);
        return results;
    }

    public static List<SymbolCategory> getAllCategoriesExceptBlank(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<SymbolCategory> allSymbolCategories = getAll(SymbolCategory.class);
        List<SymbolCategory> toBeRemoved = new ArrayList<>();
        for(SymbolCategory category : allSymbolCategories){
            if(category.getDescriptor().equals(blank_category.getDescriptor())){
                toBeRemoved.add(category);
            }
        }
        allSymbolCategories.removeAll(toBeRemoved);
        return allSymbolCategories;
    }


}
