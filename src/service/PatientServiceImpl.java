package service;

import db.Database;
import model.Hospital;
import model.Patient;
import service.PatientService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {
    private List<Hospital> hospitals = Database.getHospitals();


    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .ifPresent(hospital -> hospital.getPatients().addAll(patients));
        return "Patients added successfully to Hospital";
    }

    @Override
    public Patient getPatientById(Long id) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.toMap(Patient::getAge, patient -> patient));

    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        Comparator<Patient> comparator=Comparator.comparing(Patient::getAge);
        if (ascOrDesc.toLowerCase().equals("desc")){
            comparator=comparator.reversed();
        }
        return hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .sorted(comparator)
                .toList();
    }
}
