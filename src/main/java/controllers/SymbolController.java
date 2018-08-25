package controllers;

import db.DBHelper;
import db.Seeds;
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
        get("/symbols", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/symbols/index.vtl");
            List<Symbol> symbols = DBHelper.getAllUniqueSymbols();
            model.put("symbols", symbols);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW BEFORE ADDING TO TIMETABLE
        get("/symbols/add", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            Map<String, Object> model = new HashMap<>();
            List<Symbol> symbols = DBHelper.getAllUniqueSymbols();
            model.put("symbols", symbols);
            model.put("template", "templates/symbols/add.vtl");
            model.put("timetableID", timetableID);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  ADD TO TIMETABLE
        post("/symbols/add/:id", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            Symbol symbolCopy = new Symbol(symbol.getName(), symbol.getCategory(), symbol.getImageUrl());
            DBHelper.save(symbolCopy);
            List<Symbol> symbols = DBHelper.orderSymbolsByRank(timetable);
            Symbol currentFinalSymbol = symbols.get(symbols.size()-1);
            DBHelper.associateTimetableWithSymbol(timetable, symbolCopy);

            // This is for ensuring that the symbol ranking of the new symbol is always above the last symbol in the timetable.
            // TODO: Make this into a nicer looking method
            if(symbols.size() >= 2){
                while(symbolCopy.getRankWithinTimetable() <= currentFinalSymbol.getRankWithinTimetable()){
                    DBHelper.increaseRankingOfSymbolInTimetable(symbolCopy);
                }
            }
            res.redirect("/timetables/"+timetableID+"/show_symbols");
            return null;
        });

        //  REMOVE FROM TIMETABLE
        post("/symbols/remove_from_timetable/:id", (req, res) -> {
            int timetableID = Integer.parseInt(req.queryParams("timetable_id"));
            int symbolID = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
           // SymbolRank symbolRank = DBHelper.getSymbolRankForThisSymbolForThisTimetable(symbol, timetable);
           // DBHelper.delete(symbolRank);
            DBHelper.delete(symbol);
            DBHelper.save(timetable);
            Map<String, Object> model = new HashMap<>();
            res.redirect("/timetables/"+timetableID+"/show_symbols");
            return null;
        });

        //  CREATE
        post("/symbols", (req, res) -> {
            String name = req.queryParams("name");
            String imageURL = req.queryParams("imgUrl");
            int categoryID = Integer.parseInt(req.queryParams("category"));
            SymbolCategory category = DBHelper.find(categoryID, SymbolCategory.class);
            Symbol symbol = new Symbol(name, category, imageURL);
            DBHelper.save(symbol);
            res.redirect("/symbols");
            return null;
        });

        //  NEW
        get("/symbols/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/symbols/create.vtl");
            List<SymbolCategory> symbolCategories = DBHelper.getAll(SymbolCategory.class);
            model.put("symbolCategories", symbolCategories);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW
        get("/symbols/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/symbols/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            model.put("symbol", symbol);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT
        get("/symbols/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            List<SymbolCategory> categories = DBHelper.getAll(SymbolCategory.class);

            Map<String, Object> model = new HashMap<>();
            model.put("categories", categories);
            model.put("template", "templates/symbols/edit.vtl");
            model.put("symbol", symbol);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/symbols/:id", (req, res) -> {
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
            res.redirect("/symbols");
            return null;
        });

        //  DESTROY
        post("/symbols/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Symbol symbol = DBHelper.find(id, Symbol.class);
            DBHelper.delete(symbol);
            res.redirect("/symbols");
            return null;
        });
    }
}
