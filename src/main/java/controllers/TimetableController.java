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

public class TimetableController {

    public TimetableController() {

        //  INDEX
        get("/timetables", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/index.vtl");
            List<Timetable> timetables = DBHelper.getAll(Timetable.class);
            model.put("timetables", timetables);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // SHOW SYMBOLS IN TIMETABLE
        get("/timetables/:id/show_symbols", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            List<Symbol> symbols = timetable.getSymbols();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/show_symbols.vtl");
            model.put("symbols", symbols);
            model.put("timetable", timetable);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE
        post("/timetables", (req, res) -> {
            String name = req.queryParams("name");
            int userID = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.find(userID, User.class);
            Timetable timetable = new Timetable(name);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/timetables");
            return null;
        });

        //  NEW
        get("/timetables/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/create.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW
        get("/timetables/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/timetables/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            model.put("timetable", timetable);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT
        get("/timetables/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            List<User> users = DBHelper.getAll(User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("users", users);
            model.put("template", "templates/timetables/edit.vtl");
            model.put("timetable", timetable);

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/timetables/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            int userId = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.find(userId, User.class);
            String name = req.queryParams("name");
            timetable.setName(name);
            timetable.setUser(user);
            DBHelper.save(timetable);
            res.redirect("/timetables");
            return null;
        });

        //  DESTROY
        post("/timetables/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            DBHelper.delete(timetable);
            res.redirect("/timetables");
            return null;
        });

    }
}
