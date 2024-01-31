package service;

import db.Database;
import model.Department;
import model.Hospital;
import service.DepartmentService;

import java.util.Collections;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private List<Hospital> hospitals = Database.getHospitals();
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDepartments)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getDepartmentName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
