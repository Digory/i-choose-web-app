package controllers;

import db.DBHelper;
import db.Seeds;
import models.Symbol;
import models.SymbolCategory;
import models.Timetable;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class TimetableController {

    public TimetableController() {

        //  INDEX
        get("/timetables", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/index.vtl");
            List<Timetable> timetables = DBHelper.getAll(Timetable.class);
            model.put("timetables", timetables);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // SHOW ASSOCIATED SYMBOLS
        get("/timetables/:id/show_symbols", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            List<Symbol> symbols = DBHelper.getAllSymbolsForTimetable(timetable);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/show_symbols.vtl");
            model.put("symbols", symbols);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
//
//        //  CREATE
//        post("/symbols", (req, res) -> {
//            String name = req.queryParams("name");
//            String imageURL = req.queryParams("imgUrl");
//            int categoryID = Integer.parseInt(req.queryParams("category"));
//            SymbolCategory category = DBHelper.find(categoryID, SymbolCategory.class);
//            Symbol symbol = new Symbol(name, category, imageURL);
//            DBHelper.save(symbol);
//            res.redirect("/symbols");
//            return null;
//        });
//
//        //  NEW
//        get("/symbols/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("template", "templates/symbols/create.vtl");
//            List<SymbolCategory> symbolCategories = DBHelper.getAll(SymbolCategory.class);
//            model.put("symbolCategories", symbolCategories);
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());
//
//        //  SHOW
//        get("/symbols/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("template", "templates/symbols/show.vtl");
//            int id = Integer.parseInt(req.params("id"));
//            Symbol symbol = DBHelper.find(id, Symbol.class);
//            model.put("symbol", symbol);
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());
//
//        //  EDIT
//        get("/symbols/:id/edit", (req, res) -> {
//            int id = Integer.parseInt(req.params(":id"));
//            Symbol symbol = DBHelper.find(id, Symbol.class);
//            List<SymbolCategory> categories = DBHelper.getAll(SymbolCategory.class);
//
//            Map<String, Object> model = new HashMap<>();
//            model.put("categories", categories);
//            model.put("template", "templates/symbols/edit.vtl");
//            model.put("symbol", symbol);
//
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());
//
//        //  UPDATE
//        post("/symbols/:id", (req, res) -> {
//            int id = Integer.parseInt(req.params(":id"));
//            Symbol symbol = DBHelper.find(id, Symbol.class);
//            int categoryId = Integer.parseInt(req.queryParams("category"));
//            SymbolCategory category = DBHelper.find(categoryId, SymbolCategory.class);
//            String name = req.queryParams("name");
//            String imgUrl = req.queryParams("imgUrl");
//
//            symbol.setName(name);
//            symbol.setImageUrl(imgUrl);
//            symbol.setCategory(category);
//            DBHelper.save(symbol);
//            res.redirect("/symbols");
//            return null;
//        });
//
//        //  DESTROY
//        post("/symbols/:id/delete", (req, res) -> {
//            int id = Integer.parseInt(req.params(":id"));
//            Symbol symbol = DBHelper.find(id, Symbol.class);
//            DBHelper.delete(symbol);
//            res.redirect("/symbols");
//            return null;
//        });

    }
}
