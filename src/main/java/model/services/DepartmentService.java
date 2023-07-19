package model.services;

import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll() {
    try {
        return dao.findAll();
    } catch (DbException e) {
        System.out.println("The department table is empty");
        return new ArrayList<>(); // Returns an empty array.
    }
}

}