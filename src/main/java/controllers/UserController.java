package controllers;

import db.DBHelper;
import models.Symbol;
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
        get("/users", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/users/index.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW
    get("/users/new", (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("template", "templates/users/create.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    //  SHOW
    get("/users/:id", (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        int id = Integer.parseInt(req.params(":id"));
        User user = DBHelper.find(id, User.class);
        List<Timetable> timetables = DBHelper.getUniqueTimetablesForUser(user);
        model.put("timetables", timetables);
        model.put("template", "templates/users/show.vtl");
        model.put("user", user);
        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    //  CREATE
    post("/users", (req, res) -> {
        String name = req.queryParams("name");
        User user = new User(name);
        DBHelper.save(user);
        res.redirect("/users");
        return null;
    });

    //  EDIT
    get("/users/:id/edit", (req, res) -> {
        int id = Integer.parseInt(req.params(":id"));
        User user = DBHelper.find(id, User.class);
        Map<String, Object> model = new HashMap<>();
        model.put("template", "templates/users/edit.vtl");
        model.put("user", user);
        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    //  UPDATE
    post("/users/:id", (req, res) -> {
        int id = Integer.parseInt(req.params(":id"));
        User user = DBHelper.find(id, User.class);
        String name = req.queryParams("name");
        user.setName(name);
        DBHelper.save(user);
        res.redirect("/users");
        return null;
    }, new VelocityTemplateEngine());

    post("/users/:id/delete", (req, res) -> {
        int id = Integer.parseInt(req.params(":id"));
        User user = DBHelper.find(id, User.class);
        DBHelper.delete(user);
        res.redirect("/users");
        return null;
    });
    }
}
