package controllers;

import db.DBHelper;
import models.Symbol;
import models.SymbolCategory;
import models.Timetable;
import models.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class UserController {

    public UserController() {

        //  INDEX FOR ADMIN
        get("/admin/users", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/index.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW FOR ADMIN
        get("/admin/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/create.vtl");
            return new ModelAndView(model, "/templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW FOR USER
        get("/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/create.vtl");
            return new ModelAndView(model, "/templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  INDEX FOR USER
        get("/users/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            List<Timetable> timetables = DBHelper.getUniqueTimetablesForUser(user);
            List<SymbolCategory> categories = DBHelper.getAllCategoriesExceptBlank();
            model.put("timetables", timetables);
            model.put("categories", categories);
            model.put("template", "templates/user/index.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE FOR ADMIN
        post("/admin/users", (req, res) -> {
            String name = req.queryParams("name");
            User user = new User(name);
            DBHelper.save(user);
            res.redirect("/admin/users");
            return null;
        });

        //  CREATE FOR USER
        post("/users", (req, res) -> {
            String name = req.queryParams("name");
            User user = new User(name);
            DBHelper.save(user);
            res.redirect("/");
            return null;
        });

        //  EDIT FOR ADMIN
        get("/admin/users/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/edit.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT FOR USER
        get("/users/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/edit.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE FOR ADMIN
        post("/admin/users/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            String name = req.queryParams("name");
            user.setName(name);
            DBHelper.save(user);
            res.redirect("/admin/users");
            return null;
        }, new VelocityTemplateEngine());

        //  UPDATE FOR USER
        post("/users/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            String name = req.queryParams("name");
            user.setName(name);
            DBHelper.save(user);
            res.redirect("/users/"+user.getId());
            return null;
        }, new VelocityTemplateEngine());

        //  DELETE FOR ADMIN
        post("/admin/users/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            DBHelper.deleteUser(user);
            res.redirect("/admin/users");
            return null;
        });

        //  DELETE FOR USER
        post("/users/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            DBHelper.deleteUser(user);
            res.redirect("/");
            return null;
        });

        //  SHOW ALL TIMETABLES ATTACHED TO USER
        get("/users/:id/show_all_timetables", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            List<Timetable> userTimetables = DBHelper.getUniqueTimetablesForUser(user);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/timetables/all_timetables.vtl");
            model.put("user", user);
            model.put("userTimetables", userTimetables);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW TIMETABLES BEFORE ADDING SYMBOL
        get("/users/:id/add_symbol", (req, res) -> {
            int user_id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(user_id, User.class);
            int symbolID = Integer.parseInt(req.queryParams("symbol_id"));
            Symbol symbol = DBHelper.find(symbolID, Symbol.class);
            List<Timetable> userTimetables = DBHelper.getUniqueTimetablesForUser(user);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/timetables/add_symbol.vtl");
            model.put("user", user);
            model.put("userTimetables", userTimetables);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
