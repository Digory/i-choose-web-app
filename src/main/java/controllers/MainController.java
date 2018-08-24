package controllers;

import db.DBHelper;
import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import models.Symbol;
import models.SymbolCategory;

import java.util.*;

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

            //  SHOW
            get("/symbols/:id", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                model.put("template", "templates/show.vtl");
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
                model.put("template", "templates/edit.vtl");
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
