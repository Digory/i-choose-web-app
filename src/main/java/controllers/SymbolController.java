package controllers;

import db.DBHelper;
//import models.SymbolRank;
import models.Timetable;
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
            model.put("template", "templates/symbols/index.vtl");
            List<Symbol> symbols = DBHelper.getAll(Symbol.class);
            model.put("symbols", symbols);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SEARCH BY KEYWORD
        post("/symbols/search_results", (req, res) -> {
            String searchQuery = req.queryParams("search");
            List<Symbol> searchResults = DBHelper.searchForSymbol(searchQuery);
            Map<String, Object> model = new HashMap<>();
            model.put("results", searchResults);
            model.put("template", "templates/search_results.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW CATEGORY
        get("symbols/category", (req, res) -> {
            String categoryName = req.queryParams("category");
            List<Symbol> searchResults = DBHelper.searchForSymbol(categoryName);
            Map<String, Object> model = new HashMap<>();
            model.put("results", searchResults);
            model.put("template", "templates/search_results.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW BEFORE ADDING TO TIMETABLE
        get("/admin/symbols/add", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            Map<String, Object> model = new HashMap<>();
            List<Symbol> symbols = DBHelper.getAll(Symbol.class);
            model.put("symbols", symbols);
            model.put("template", "templates/admin/symbols/add.vtl");
            model.put("timetableID", timetableID);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  ADD TO TIMETABLE
        post("/symbols/add/:id", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            DBHelper.addSymbolToTimetable(timetable, symbol);
            res.redirect("/timetables/"+timetableID+"/show_symbols");
            return null;
        });

        //  REMOVE FROM TIMETABLE
        post("/symbols/remove_from_timetable/:id", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            int position = Integer.parseInt(req.queryParams("symbol_position"));
            position -= 1;
            timetable.removeSymbolAtPosition(position);
            DBHelper.save(timetable);
            Map<String, Object> model = new HashMap<>();
            res.redirect("/timetables/"+timetableID+"/show_symbols");
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
            position -= 1;
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            timetable.moveSymbolAtThisPositionUpByOne(position);
            DBHelper.save(timetable);
            res.redirect("/timetables/"+timetableID+"/show_symbols");
            return null;
        });

        //  MOVE SYMBOL DOWN TIMETABLE
        get("/symbols/move_down", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int position = Integer.parseInt(req.queryParams("symbol_position"));
            position -= 1;
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            timetable.moveSymbolAtThisPositionDownByOne(position);
            DBHelper.save(timetable);
            res.redirect("/timetables/"+timetableID+"/show_symbols");
            return null;
        });

        //  CREATE
        post("/admin/symbols", (req, res) -> {
            String name = req.queryParams("name");
            String imageURL = req.queryParams("imgUrl");
            int categoryID = Integer.parseInt(req.queryParams("category"));
            SymbolCategory category = DBHelper.find(categoryID, SymbolCategory.class);
            Symbol symbol = new Symbol(name, category, imageURL);
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
            DBHelper.delete(symbol);
            res.redirect("/admin/symbols");
            return null;
        });
    }
}
