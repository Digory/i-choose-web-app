package controllers;

import db.DBHelper;
import db.Seeds;
import models.Symbol;
import models.SymbolCategory;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();
//
//         public ManagersController() {
//            setupEndPoints();
//        }
//
//        private void setupEndPoints () {
//            //  INDEX
//            get("/managers", (req, res) -> {
//                Map<String, Object> model = new HashMap<>();
//                model.put("template", "templates/managers/index.vtl");
//                List<Manager> managers = DBHelper.getAll(Manager.class);
//                model.put("managers", managers);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  CREATE
//            post("/managers", (req, res) -> {
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
//            //  NEW
//            get("/managers/new", (req, res) -> {
//                Map<String, Object> model = new HashMap<>();
//                model.put("template", "templates/managers/create.vtl");
//                List<Department> departments = DBHelper.getAll(Department.class);
//                model.put("departments", departments);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  SHOW
//            get("/managers/:id", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                Map<String, Object> model = new HashMap<>();
//                model.put("template", "templates/managers/show.vtl");
//                model.put("manager", manager);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  EDIT  get /managers/:id/edit
//            get("/managers/:id/edit", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                Map<String, Object> model = new HashMap<>();
//                List<Department> departments = DBHelper.getAll(Department.class);
//                model.put("departments", departments);
//                model.put("template", "templates/managers/edit.vtl");
//                model.put("manager", manager);
//                return new ModelAndView(model, "templates/layout.vtl");
//            }, new VelocityTemplateEngine());
//
//            //  UPDATE  post /managers/:id
//            post("/managers/:id", (req, res) -> {
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
//            //  DESTROY  post /managers/:id/delete
//            post("/managers/:id/delete", (req, res) -> {
//                Manager manager = DBHelper.find(Integer.parseInt(req.params("id")), Manager.class);
//                DBHelper.delete(manager);
//                res.redirect("/managers");
//                return null;
//            });
//
//
//        }
    }
}
