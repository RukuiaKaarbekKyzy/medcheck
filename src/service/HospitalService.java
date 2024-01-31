package service;

import dao.HospitalDao;
import model.Department;
import model.Doctor;

import javax.swing.text.html.parser.Entity;

public interface HospitalService extends HospitalDao, GenericService<Department> {

    //doctors
    String add(Long id, Doctor doctor);

    void removeDoctorById(Long id);

    String updateById(Long id, Doctor doctor);
}
