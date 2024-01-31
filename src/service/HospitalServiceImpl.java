package service;

import db.Database;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalServiceImpl implements HospitalService {
    private List<Hospital> hospitals = Database.getHospitals();


    @Override
    public String addHospital(Hospital hospital) {
        hospitals.add(hospital);
        return "Hospital added successfully";
    }

    @Override
    public Hospital findHospitalById(Long id) {
       return hospitals.stream()
               .filter(hospital -> hospital.getId().equals(id))
               .findFirst()
               .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return findHospitalById(id).getPatients();
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean removed=hospitals.removeIf(hospital -> hospital.getId().equals(id));
        if (removed){
            return "Hospital deleted successfully";
        }else {
            return "Hospital is not found";
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitals.stream()
                .filter(hospital -> hospital.getAddress().equals(address))
                .collect(Collectors.toMap(Hospital::getHospitalName,hospital -> hospital));

    }

    @Override
    public String add(Long id, Department department) {
        Hospital hospital=findHospitalById(id);
        if (hospital!=null){
            hospital.getDepartments().add(department);
            return "Department added to hospital successfully";
        }else{
            return "Hospital is not found";
        }
    }

    @Override
    public void removeById(Long id) {
        hospitals.forEach(hospital -> {
            hospital.getDepartments().removeIf(department -> department.getId().equals(id));
        });
    }

    @Override
    public String updateById(Long id, Department department) {
         hospitals.stream()
                 .flatMap(hospital -> hospital.getDepartments().stream())
                 .filter(department1 -> department1.getId().equals(id))
                 .findFirst()
                 .ifPresent(department1 ->{
                         department1.setDepartmentName(department.getDepartmentName());
                         department1.setDoctors(department.getDoctors());

    });
         return "Department updated successfully";
    }

//doctors
    @Override
    public String add(Long id, Doctor doctor) {
        Hospital hospital=findHospitalById(id);
        if (hospital!=null){
            hospital.getDoctors().add(doctor);
            return "Doctors added to hospital successfully";
        }else{
            return "Hospital is not found";
        }
    }

    @Override
    public void removeDoctorById(Long id) {
        hospitals.forEach(hospital -> {
            hospital.getDoctors().removeIf(doctor -> doctor.getId().equals(id));
        });
    }

    @Override
    public String updateById(Long id,Doctor doctor ) {
        hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor1 -> doctor1.getId().equals(id))
                .findFirst()
                .ifPresent(doctor1 ->{
                    doctor1.setExperienceYear(doctor.getExperienceYear());

                });
        return "Doctor updated successfully";
    }
}
