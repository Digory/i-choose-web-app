package controllers;

import db.DBHelper;
import db.Seeds;
import models.SymbolCategory;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.SparkBase.staticFileLocation;

import static spark.Spark.get;

public class MainController {

    public static void main(String[] args){



        Seeds.seedData();

        staticFileLocation("/public");

        SymbolController symbolController = new SymbolController();
        TimetableController timetableController = new TimetableController();
        SymbolCategoryController symbolCategoryController = new SymbolCategoryController();
        UserController userController = new UserController();

        get("/", (req, res) -> {

            List categories = DBHelper.getAll(SymbolCategory.class);
            Map<String, Object> model = new HashMap<>();
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            model.put("template", "templates/login.vtl");
            model.put("categories", categories);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/admin", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/index.vtl");
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
