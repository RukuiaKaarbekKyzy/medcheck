package service;

import db.Database;
import model.Department;
import model.Doctor;
import model.Hospital;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorServiceImpl implements DoctorService{
    private List<Hospital> hospitals = Database.getHospitals();
    @Override
    public Doctor findDoctorById(Long id) {
       return hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId().equals(departmentId)))
                .findFirst()
                .ifPresent(department -> {
                    List<Doctor> doctorsToAdd = hospitals.stream()
                            .flatMap(hospital -> hospital.getDoctors().stream())
                            .filter(doctor -> doctorsId.contains(doctor.getId()))
                            .collect(Collectors.toList());

                    department.getDoctors().addAll(doctorsToAdd);
                });

        return "Doctors assigned to the department successfully.";
    }


    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDoctors)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId().equals(id))
                .findFirst()
                .map(Department::getDoctors)
                .orElseGet(Collections::emptyList);
    }
}
