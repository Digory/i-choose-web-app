package db;
import models.Symbol;
import models.SymbolCategory;
import models.Timetable;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
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

    public static void addSymbolToTimetable(Timetable timetable, Symbol symbol){
        timetable.addSymbol(symbol);
        //symbol.setTimetable(timetable);
        save(timetable);

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

    public static List<Symbol> searchForSymbol(String keyword){
        List<Symbol> allSymbols = getAllUniqueSymbols();
        List<Symbol> results = new ArrayList<>();
        for(Symbol symbol : allSymbols){
            if(symbol.getName().toLowerCase().equals(keyword.toLowerCase()) ||
                    symbol.getCategory().getDescriptor().toLowerCase().equals(keyword.toLowerCase())){
                results.add(symbol);
            }
        }
        return results;
    }


}
