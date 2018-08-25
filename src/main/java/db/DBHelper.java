package db;
import models.Symbol;
import models.SymbolCategory;
//import models.SymbolRank;
import models.Timetable;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

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

    public static void associateTimetableWithSymbol(Timetable timetable, Symbol symbol){
        symbol.setTimetable(timetable);
        save(symbol);

//        SymbolRank symbolRank = new SymbolRank(symbol, timetable);
//        save(symbolRank);
    }

    public static List<Symbol> getAllSymbolsForTimetable(Timetable timetable){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Symbol> results = null;
        try {
            Criteria cr = session.createCriteria(Symbol.class);
            cr.add(Restrictions.eq("timetable.id", timetable.getId()));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

//    public static SymbolRank getSymbolRankForThisSymbolForThisTimetable(Symbol symbol, Timetable timetable){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Criteria cr = session.createCriteria(SymbolRank.class);
//        cr.add(Restrictions.eq("symbol", symbol));
//        cr.add(Restrictions.eq("timetable", timetable));
//        return getUnique(cr);
//    }

//    public static boolean doesTimetableContainSymbolAlready(Symbol symbol, Timetable timetable){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Criteria cr = session.createCriteria(Symbol.class);
//        cr.createAlias("timetables", "timetable");
//        cr.add(Restrictions.eq("timetable.id", timetable.getId()));
//        if(cr.list().size() == 0){
//            return false;
//        }
//        return true;
//    }

//    public static void increaseRankingOfSymbolInTimetable(Symbol symbol, Timetable timetable){
//        session = HibernateUtil.getSessionFactory().openSession();
//        Criteria cr = session.createCriteria(SymbolRank.class);
//        cr.add(Restrictions.eq("symbol.id", symbol.getId()));
//        cr.add(Restrictions.eq("timetable.id", timetable.getId()));
//        SymbolRank symbolRank = (SymbolRank) cr.uniqueResult();
//        symbolRank.increaseRank();
//        save(symbolRank);
//    }

    public static void increaseRankingOfSymbolInTimetable(Symbol symbol){
        symbol.increaseRankWithinTimetable();
        save(symbol);
    }

    public static void decreaseRankingOfSymbolInTimetable(Symbol symbol){
        symbol.decreaseRankWithinTimetable();
        save(symbol);
    }


    public static List<Symbol> orderSymbolsByRank(Timetable timetable){
        List<Symbol> results = getAllSymbolsForTimetable(timetable);
        Collections.sort(results, new Comparator<Symbol>() {
            @Override
            public int compare(Symbol symbol1, Symbol symbol2) {
                //return getRankOfSymbol(symbol1, timetable) - getRankOfSymbol(symbol2, timetable);
                return symbol1.getRankWithinTimetable() - symbol2.getRankWithinTimetable();
            }
        });
        return results;
    }

//    public static int getRankOfSymbol(Symbol symbol, Timetable timetable){
//        session = HibernateUtil.getSessionFactory().openSession();
//        int result = 0;
//        try {
//            Criteria cr = session.createCriteria(SymbolRank.class);
//            cr.add(Restrictions.eq("symbol.id", symbol.getId()));
//            cr.add(Restrictions.eq("timetable.id", timetable.getId()));
//            SymbolRank symbolRank = (SymbolRank) cr.uniqueResult();
//            result = symbolRank.getRank();
//        } catch(HibernateException ex){
//            ex.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return result;
//    }

    public static List<Symbol> getAllUniqueSymbols(){
        List<Symbol> allSymbols = getAll(Symbol.class);
        List<Symbol> filteredSymbols = new ArrayList<>();
        for(Symbol symbol : allSymbols){
            if(!doesThisListOfSymbolsContainOneWithThisName(symbol.getName(), filteredSymbols)){
                filteredSymbols.add(symbol);
            }
        }
        return filteredSymbols;
    }

    public static boolean doesThisListOfSymbolsContainOneWithThisName(String name, List<Symbol> symbols){
        for(Symbol symbol : symbols){
            if(symbol.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void moveSymbolUpTimetable(Symbol symbolToMove, Timetable timetable){
        List<Symbol> symbolsOrderedByRank = orderSymbolsByRank(timetable);
        int currentPosition = 0;
        for(int i = 0; i < symbolsOrderedByRank.size(); i++){
            if(symbolsOrderedByRank.get(i).getId() == symbolToMove.getId()){
                currentPosition = i;
            }
        }
        if(currentPosition == 0){
            return;
        }
        Symbol theSymbolAbove = symbolsOrderedByRank.get(currentPosition-1);
        theSymbolAbove.increaseRankWithinTimetable();
        save(theSymbolAbove);
        symbolToMove.decreaseRankWithinTimetable();
        save(symbolToMove);
    }

    public static void moveSymbolDownTimetable(Symbol symbolToMove, Timetable timetable){
        List<Symbol> symbolsOrderedByRank = orderSymbolsByRank(timetable);
        int currentPosition = symbolsOrderedByRank.size()-1;
        for(int i = 0; i < symbolsOrderedByRank.size(); i++){
            if(symbolsOrderedByRank.get(i).getId() == symbolToMove.getId()){
                currentPosition = i;
            }
        }
        if(currentPosition == symbolsOrderedByRank.size()-1){
            return;
        }
        Symbol theSymbolBelow = symbolsOrderedByRank.get(currentPosition+1);
        theSymbolBelow.decreaseRankWithinTimetable();
        save(theSymbolBelow);
        symbolToMove.increaseRankWithinTimetable();
        save(symbolToMove);
    }


}
