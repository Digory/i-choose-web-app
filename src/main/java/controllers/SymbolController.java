package controllers;

import db.DBHelper;
//import models.SymbolRank;
import models.Timetable;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import models.Symbol;
import models.SymbolCategory;

import java.util.*;

public class SymbolController {

    public SymbolController() {

        //  INDEX
        get("/admin/symbols", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbols/index.vtl");
            List<Symbol> symbols = DBHelper.getAll(Symbol.class);
            model.put("symbols", symbols);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH PAGE
        get("/symbols/search", (req, res) -> {
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            List<Timetable> allUserTimetables = DBHelper.getUniqueTimetablesForUser(user);
            List<SymbolCategory> categories = DBHelper.getAllCategoriesExceptBlank();
            Map<String, Object> model = new HashMap<>();
            List<Symbol> topThreeSymbols = DBHelper.findTopThreeMostUsedSymbols(user);
            Symbol symbol1 = topThreeSymbols.get(0);
            Symbol symbol2 = topThreeSymbols.get(1);
            Symbol symbol3 = topThreeSymbols.get(2);
            model.put("symbol1", symbol1);
            model.put("symbol2", symbol2);
            model.put("symbol3", symbol3);
            model.put("user", user);
            model.put("categories", categories);
            model.put("allUserTimetables", allUserTimetables);
            model.put("template", "templates/user/symbols/search.vtl");
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH RESULTS - POST
        post("/symbols/search_results", (req, res) -> {
            String searchQuery = req.queryParams("search");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            List<Timetable> allUserTimetables = DBHelper.getUniqueTimetablesForUser(user);
            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
            Map<String, Object> model = new HashMap<>();
            model.put("searchQuery", searchQuery);
            model.put("user", user);
            model.put("allUserTimetables", allUserTimetables);
            model.put("results", searchResults);
            model.put("template", "templates/user/symbols/search_results.vtl");
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH RESULTS - CHILD VIEW
        post("/symbols/search_results/child_view", (req, res) -> {
            String searchQuery = req.queryParams("search");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
            Map<String, Object> model = new HashMap<>();
            model.put("searchQuery", searchQuery);
            model.put("user", user);
            model.put("results", searchResults);
            model.put("template", "templates/child/symbols/search_results.vtl");
            return new ModelAndView(model, "templates/child/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH RESULTS - GET
        get("/symbols/search_results", (req, res) -> {
            String searchQuery = req.queryParams("searchQuery");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            List<Timetable> allUserTimetables = DBHelper.getUniqueTimetablesForUser(user);
            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
            Map<String, Object> model = new HashMap<>();
            model.put("searchQuery", searchQuery);
            model.put("user", user);
            model.put("allUserTimetables", allUserTimetables);
            model.put("results", searchResults);
            model.put("template", "templates/user/symbols/search_results.vtl");
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH RESULTS - GET - CHILD'S VIEW
        get("/symbols/search_results/child_view", (req, res) -> {
            String searchQuery = req.queryParams("searchQuery");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
            Map<String, Object> model = new HashMap<>();
            model.put("searchQuery", searchQuery);
            model.put("user", user);
            model.put("results", searchResults);
            model.put("template", "templates/child/symbols/search_results.vtl");
            return new ModelAndView(model, "templates/child/layout.vtl");
        }, new VelocityTemplateEngine());
//
//        //  SHOW CATEGORY
//        get("/symbols/category", (req, res) -> {
//            int userID = Integer.parseInt(req.queryParams("user_id"));
//            User user = DBHelper.find(userID, User.class);
//            String searchQuery = req.queryParams("searchQuery");
//            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
//            List<Timetable> allUserTimetables = DBHelper.getUniqueTimetablesForUser(user);
//            Map<String, Object> model = new HashMap<>();
//            model.put("user", user);
//            model.put("allUserTimetables", allUserTimetables);
//            model.put("results", searchResults);
//            model.put("searchQuery", searchQuery);
//            model.put("template", "templates/user/symbols/search_results.vtl");
//            return new ModelAndView(model, "templates/user/layout.vtl");
//        }, new VelocityTemplateEngine());

        //  ADD TO TIMETABLE
        post("/symbols/:id/add", (req, res) -> {
            String searchQuery = req.queryParams("searchQuery");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            User user = DBHelper.find(userID, User.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            DBHelper.addSymbolToTimetable(timetable, symbol);
            if (searchQuery.length() != 0){
                res.redirect("/symbols/search_results?user_id=" + userID + "&searchQuery=" + searchQuery);
            }
            else{
                res.redirect("/users/"+ userID + "/view_symbol?symbol_id=" + symbolID);
            }
            return null;
        });

        //  REMOVE FROM TIMETABLE
        post("/symbols/remove_from_timetable/:id", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            int userID = Integer.parseInt(req.queryParams("user_id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            int position = Integer.parseInt(req.queryParams("symbol_position"));
            position -= 1;
            timetable.removeSymbolAtPosition(position);
            DBHelper.save(timetable);
            Map<String, Object> model = new HashMap<>();
            res.redirect("/timetables/"+timetableID+"/show_symbols?user_id="+userID);
            return null;
        });

        //  NEW
        get("/admin/symbols/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbols/create.vtl");
            List<SymbolCategory> symbolCategories = DBHelper.getAll(SymbolCategory.class);
            model.put("symbolCategories", symbolCategories);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  MOVE SYMBOL UP TIMETABLE
        get("/symbols/move_up", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int position = Integer.parseInt(req.queryParams("symbol_position"));
            int userID = Integer.parseInt(req.queryParams("user_id"));
            position -= 1;
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            timetable.moveSymbolAtThisPositionUpByOne(position);
            DBHelper.save(timetable);
            res.redirect("/timetables/"+timetableID+"/show_symbols?user_id="+userID);
            return null;
        });

        //  MOVE SYMBOL DOWN TIMETABLE
        get("/symbols/move_down", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int position = Integer.parseInt(req.queryParams("symbol_position"));
            int userID = Integer.parseInt(req.queryParams("user_id"));
            position -= 1;
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            timetable.moveSymbolAtThisPositionDownByOne(position);
            DBHelper.save(timetable);
            res.redirect("/timetables/"+timetableID+"/show_symbols?user_id="+userID);
            return null;
        });

        //  CREATE
        post("/admin/symbols", (req, res) -> {
            String name = req.queryParams("name");
            String imageURL = req.queryParams("imgUrl");
            String soundUrl = req.queryParams("soundUrl");
            int categoryID = Integer.parseInt(req.queryParams("category"));
            SymbolCategory category = DBHelper.find(categoryID, SymbolCategory.class);
            Symbol symbol = new Symbol(name, category, imageURL, soundUrl);
            DBHelper.save(symbol);
            res.redirect("/admin/symbols");
            return null;
        });

        //  SHOW
        get("/admin/symbols/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbols/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            model.put("symbol", symbol);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT
        get("/admin/symbols/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            List<SymbolCategory> categories = DBHelper.getAll(SymbolCategory.class);

            Map<String, Object> model = new HashMap<>();
            model.put("categories", categories);
            model.put("template", "templates/admin/symbols/edit.vtl");
            model.put("symbol", symbol);

            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/admin/symbols/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            int categoryId = Integer.parseInt(req.queryParams("category"));
            SymbolCategory category = DBHelper.find(categoryId, SymbolCategory.class);
            String name = req.queryParams("name");
            String imgUrl = req.queryParams("imgUrl");

            symbol.setName(name);
            symbol.setImageUrl(imgUrl);
            symbol.setCategory(category);
            DBHelper.save(symbol);
            res.redirect("/admin/symbols");
            return null;
        });

        //  DESTROY
        post("/admin/symbols/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            DBHelper.setBlankAllOccurrencesOfThisSymbol(symbol);
            res.redirect("/admin/symbols");
            return null;
        });
    }
}
