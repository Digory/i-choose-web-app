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


        // ADMIN ROUTES

        //  INDEX
        get("/admin/timetables", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/index.vtl");
            List<Timetable> timetables = DBHelper.getAll(Timetable.class);
            model.put("timetables", timetables);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        // SHOW SYMBOLS IN TIMETABLE
        get("/admin/timetables/:id/show_symbols", (req, res) ->{
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            List<Symbol> symbols = timetable.getSymbols();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/show_symbols.vtl");
            model.put("symbols", symbols);
            model.put("timetable", timetable);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE
        post("/admin/timetables", (req, res) -> {
            String name = req.queryParams("name");
            int userID = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.find(userID, User.class);
            Timetable timetable = new Timetable(name);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/admin/timetables");
            return null;
        });

        //  NEW
        get("/admin/timetables/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/create.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  SHOW
        get("/admin/timetables/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            model.put("timetable", timetable);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT
        get("/admin/timetables/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            List<User> users = DBHelper.getAll(User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("users", users);
            model.put("template", "templates/admin/timetables/edit.vtl");
            model.put("timetable", timetable);

            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE
        post("/admin/timetables/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            int userId = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.find(userId, User.class);
            String name = req.queryParams("name");
            timetable.setName(name);
            timetable.setUser(user);
            DBHelper.save(timetable);
            res.redirect("/admin/timetables");
            return null;
        });

        //  DESTROY
        post("/admin/timetables/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            DBHelper.delete(timetable);
            res.redirect("/admin/timetables");
            return null;
        });

        // USER ROUTES

        //  NEW FOR USER
        get("/timetables/new_for_user", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/create_via_user.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE FOR USER
        post("/timetables/create_for_user", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("user_id"));
            String timetableName = req.queryParams("name");
            User user = DBHelper.find(id, User.class);
            Timetable timetable = new Timetable(timetableName);
            DBHelper.save(timetable);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/users/" + id);
            return null;
        });

    }
}
