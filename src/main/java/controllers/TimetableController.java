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


        //  CREATE FOR USER
        post("/admin/timetables/create_for_user", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("user_id"));
            String timetableName = req.queryParams("name");
            User user = DBHelper.find(id, User.class);
            Timetable timetable = new Timetable(timetableName);
            DBHelper.save(timetable);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/admin/users/" + id);
            return null;
        });

        //  INDEX FOR ADMIN
        get("/admin/timetables", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/index.vtl");
            List<Timetable> timetables = DBHelper.getAll(Timetable.class);
            model.put("timetables", timetables);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        // SHOW SYMBOLS IN TIMETABLE TO USER (CARER)
        get("/timetables/:id/show_symbols", (req, res) ->{
            int timetableId = Integer.parseInt(req.params("id"));
            int userId = Integer.parseInt(req.queryParams("user_id"));
            Timetable timetable = DBHelper.find(timetableId, Timetable.class);
            User user = DBHelper.find(userId, User.class);
            List<Symbol> symbols = timetable.getSymbols();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/timetables/show_details.vtl");
            model.put("symbols", symbols);
            model.put("timetable", timetable);
            model.put("user", user);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

//        // SHOW SYMBOLS IN TIMETABLE TO CHILD BEFORE LOCK
//        get("/timetables/:id/show_symbols/child_view", (req, res) ->{
//            int timetableId = Integer.parseInt(req.params("id"));
//            int userId = Integer.parseInt(req.queryParams("user_id"));
//            Timetable timetable = DBHelper.find(timetableId, Timetable.class);
//            User user = DBHelper.find(userId, User.class);
//            List<Symbol> symbols = timetable.getSymbols();
//            Map<String, Object> model = new HashMap<>();
//            model.put("template", "templates/user/timetables/child_view.vtl");
//            model.put("symbols", symbols);
//            model.put("timetable", timetable);
//            model.put("user", user);
//            return new ModelAndView(model, "templates/user/layout.vtl");
//        }, new VelocityTemplateEngine());

        // SHOW SYMBOLS IN TIMETABLE TO CHILD AFTER LOCK
        get("/timetables/:id/show_symbols/child_view_locked", (req, res) ->{
            int timetableId = Integer.parseInt(req.params("id"));
            int userId = Integer.parseInt(req.queryParams("user_id"));
            Timetable timetable = DBHelper.find(timetableId, Timetable.class);
            User user = DBHelper.find(userId, User.class);
            List<Symbol> symbols = timetable.getSymbols();
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/child/timetables/show_details.vtl");
            model.put("symbols", symbols);
            model.put("timetable", timetable);
            model.put("user", user);
            return new ModelAndView(model, "templates/child/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CHECK TIMETABLE UNLOCK CODE
        post("/timetables/:id/show_symbols", (req, res) -> {
            String unlockCode = req.queryParams("code");
            int timetableID = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(timetableID, Timetable.class);
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            if(user.getTimetableUnlockCode().equals(unlockCode)){
                res.redirect("/timetables/"+timetableID+"/show_symbols?user_id="+userID);
            }
            else{
                res.redirect("/timetables/"+timetableID+"/show_symbols/child_view_locked?user_id="+userID);
            }
            return null;
        });

        //  CREATE FOR ADMIN
        post("/admin/timetables", (req, res) -> {
            String name = req.queryParams("name");
            int userID = Integer.parseInt(req.queryParams("user"));
            User user = DBHelper.find(userID, User.class);
            Timetable timetable = new Timetable(name);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/admin/timetables");
            return null;
        });

        //  NEW FOR ADMIN
        get("/admin/timetables/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/create.vtl");
            List<User> users = DBHelper.getAll(User.class);
            model.put("users", users);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  NEW FOR USER
        get("/timetables/new_for_user", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(id, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/user/timetables/create_via_user.vtl");
            model.put("user", user);
            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  CREATE FOR USER
        post("/timetables", (req, res) -> {
            String name = req.queryParams("name");
            int userID = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userID, User.class);
            Timetable timetable = new Timetable(name);
            DBHelper.addTimetableToUser(timetable, user);
            res.redirect("/users/"+user.getId()+"/show_all_timetables");
            return null;
        });

        //  SHOW FOR ADMIN
        get("/admin/timetables/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/admin/timetables/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            model.put("timetable", timetable);
            return new ModelAndView(model, "templates/admin/layout.vtl");
        }, new VelocityTemplateEngine());

        //  EDIT FOR ADMIN
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

        //  EDIT FOR USER
        get("/timetables/:id/edit", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            int userId = Integer.parseInt(req.queryParams("user_id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            User user = DBHelper.find(userId, User.class);
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            model.put("template", "templates/user/timetables/edit.vtl");
            model.put("timetable", timetable);

            return new ModelAndView(model, "templates/user/layout.vtl");
        }, new VelocityTemplateEngine());

        //  UPDATE FOR ADMIN
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

        //  UPDATE FOR USER
        post("/timetables/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            int userId = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userId, User.class);
            String name = req.queryParams("name");
            timetable.setName(name);
            DBHelper.save(timetable);
            res.redirect("/users/"+user.getId());
            return null;
        });

        //  DESTROY FOR ADMIN
        post("/admin/timetables/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            DBHelper.delete(timetable);
            res.redirect("/admin/timetables");
            return null;
        });

        //  DESTROY FOR USER
        post("/timetables/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Timetable timetable = DBHelper.find(id, Timetable.class);
            int userId = Integer.parseInt(req.queryParams("user_id"));
            User user = DBHelper.find(userId, User.class);
            DBHelper.delete(timetable);
            res.redirect("/users/"+user.getId());
            return null;
        });



    }
}
