package controllers;

import db.DBHelper;
import models.Symbol;
import models.SymbolCategory;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class SymbolCategoryController {


    public SymbolCategoryController(){

        //  INDEX
        get("/admin/symbol-categories", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbolcategories/index.vtl");
            List<Symbol> symbolCategories = DBHelper.getAll(SymbolCategory.class);
            model.put("symbolCategories", symbolCategories);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW
        get("/admin/symbol-categories/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbolcategories/create.vtl");
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW
        get("/admin/symbol-categories/:id/symbols", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            SymbolCategory symbolCategory = DBHelper.find(id, SymbolCategory.class);
            List<Symbol> symbols = symbolCategory.getSymbols();
            model.put("template", "templates/admin/symbolcategories/show.vtl");
            model.put("symbolCategory", symbolCategory);
            model.put("symbols", symbols);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE
        post("/admin/symbol-categories", (req, res) -> {
            String icon = req.queryParams("icon");
            String descriptor = req.queryParams("descriptor");
            SymbolCategory symbolCategory = new SymbolCategory(icon, descriptor);
            DBHelper.save(symbolCategory);
            res.redirect("/admin/symbol-categories");
            return null;
        });

        //  EDIT
        get("/admin/symbol-categories/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            SymbolCategory symbolCategory = DBHelper.find(id, SymbolCategory.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/symbolcategories/edit.vtl");
            model.put("symbolCategory", symbolCategory);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/admin/symbol-categories/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            SymbolCategory symbolCategory = DBHelper.find(id, SymbolCategory.class);
            String descriptor = req.queryParams("descriptor");
            symbolCategory.setDescriptor(descriptor);
            DBHelper.save(symbolCategory);
            res.redirect("/admin/symbol-categories");
            return null;
        }, new VelocityTemplateEngine());

        post("/admin/symbol-categories/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            SymbolCategory symbolCategory = DBHelper.find(id, SymbolCategory.class);
            for (Symbol symbol : symbolCategory.getSymbols()){
                symbol.setCategory(null);
                DBHelper.save(symbol);
            }
            DBHelper.delete(symbolCategory);
            res.redirect("/admin/symbol-categories");
            return null;
        });
    }
}
