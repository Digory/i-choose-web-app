package controllers;

import db.DBHelper;
import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import models.Symbol;
import models.SymbolCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();

            //  INDEX
            get("/symbols", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                model.put("template", "templates/index.vtl");
                List<Symbol> symbols = DBHelper.getAll(Symbol.class);
                model.put("symbols", symbols);
                return new ModelAndView(model, "templates/layout.vtl");
            }, new VelocityTemplateEngine());

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
                model.put("template", "templates/create.vtl");
                List<SymbolCategory> symbolCategories = DBHelper.getAll(SymbolCategory.class);
                model.put("symbolCategories", symbolCategories);
                return new ModelAndView(model, "templates/layout.vtl");
            }, new VelocityTemplateEngine());

//            //  SHOW
//            get("/symbols/:id", (req, res) -> {
//            }, new VelocityTemplateEngine());
//
//            //  EDIT
//            get("/symbols/:id/edit", (req, res) -> {
//            }, new VelocityTemplateEngine());
//
//            //  UPDATE
//            post("/symbols/:id", (req, res) -> {
//                return null;
//            });
//
//            //  DESTROY
//            post("/symbols/:id/delete", (req, res) -> {
//                return null;
//            });
//
//

    }
}
