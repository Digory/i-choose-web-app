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

//            //  CREATE
//            post("/symbols", (req, res) -> {
//                String firstName = req.queryParams("firstName");
//                String lastName = req.queryParams("lastName");
//                int salary = Integer.parseInt(req.queryParams("salary"));
//                double budget = Double.parseDouble(req.queryParams("budget"));
//                int departmentID = Integer.parseInt(req.queryParams("department"));
//                Department department = DBHelper.find(departmentID, Department.class);
//                Manager manager = new Manager(firstName, lastName, salary, department, budget);
//                DBHelper.save(manager);
//                res.redirect("/managers");
//                return null;
//            });
//
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
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                Map<String, Object> model = new HashMap<>();
//                model.put("template", "templates/managers/show.vtl");
//                model.put("manager", manager);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  EDIT
//            get("/symbols/:id/edit", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                Map<String, Object> model = new HashMap<>();
//                List<Department> departments = DBHelper.getAll(Department.class);
//                model.put("departments", departments);
//                model.put("template", "templates/managers/edit.vtl");
//                model.put("manager", manager);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  UPDATE
//            post("/symbols/:id", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                String firstName = req.queryParams("firstName");
//                String lastName = req.queryParams("lastName");
//                int salary = Integer.parseInt(req.queryParams("salary"));
//                double budget = Double.parseDouble(req.queryParams("budget"));
//                int departmentID = Integer.parseInt(req.queryParams("department"));
//                Department department = DBHelper.find(departmentID, Department.class);
//
//                manager.setFirstName(firstName);
//                manager.setLastName(lastName);
//                manager.setSalary(salary);
//                manager.setBudget(budget);
//                manager.setDepartment(department);
//
//                DBHelper.save(manager);
//
//                res.redirect("/managers/" + req.params("id"));
//                return null;
//            });
//
//            //  DESTROY
//            post("/symbols/:id/delete", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                DBHelper.delete(manager);
//                res.redirect("/managers");
//                return null;
//            });
//
//

    }
}
