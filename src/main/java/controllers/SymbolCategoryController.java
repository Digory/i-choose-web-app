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
        get("/symbol-categories", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/symbolcategories/index.vtl");
            List<Symbol> symbolCategories = DBHelper.getAllUniqueSymbols();
            model.put("symbolCategories", symbolCategories);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW
        get("/symbol-categories/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/symbolcategories/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE
        post("/symbol-categories", (req, res) -> {
            String descriptor = req.queryParams("descriptor");
            SymbolCategory symbolCategory = new SymbolCategory(descriptor);
            DBHelper.save(symbolCategory);
            res.redirect("/symbol-categories");
            return null;
        });

    }
}
