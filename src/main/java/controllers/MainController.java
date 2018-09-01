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

import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import static spark.Spark.get;

public class MainController {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args){

        port(getHerokuAssignedPort());

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

        get("/mysentences", (req,res) -> {
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("template", "templates/user/mysentences.vtl");
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/help", (req,res) -> {
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("template", "templates/user/help.vtl");
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
