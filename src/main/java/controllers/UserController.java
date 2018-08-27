package controllers;

import db.DBHelper;
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

        //  INDEX
        get("/admin/users", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/index.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW
        get("/admin/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/create.vtl");
            return new ModelAndView(model, "/templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW TO USER
        get("/users/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            List<Timetable> timetables = DBHelper.getUniqueTimetablesForUser(user);
            model.put("timetables", timetables);
            model.put("template", "templates/admin/users/show.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE
        post("/admin/users", (req, res) -> {
            String name = req.queryParams("name");
            User user = new User(name);
            DBHelper.save(user);
            res.redirect("/admin/users");
            return null;
        });

        //  EDIT
        get("/admin/users/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/users/edit.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/admin/users/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            String name = req.queryParams("name");
            user.setName(name);
            DBHelper.save(user);
            res.redirect("/admin/users");
            return null;
        }, new VelocityTemplateEngine());

        post("/admin/users/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            User user = DBHelper.find(id, User.class);
            DBHelper.deleteUser(user);
            res.redirect("/admin/users");
            return null;
        });
    }
}
