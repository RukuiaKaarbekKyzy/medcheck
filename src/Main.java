import db.Database;
import model.*;
import service.DepartmentServiceImpl;
import service.DoctorServiceImpl;
import service.HospitalServiceImpl;
import service.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HospitalServiceImpl hospitalService=new HospitalServiceImpl();
        DepartmentServiceImpl departmentService=new DepartmentServiceImpl();
        DoctorServiceImpl doctorService=new DoctorServiceImpl();
        PatientServiceImpl patientService=new PatientServiceImpl();

        int choice;
        Scanner scanner=new Scanner(System.in);

        do {
            System.out.println("1.Add Hospital");
            System.out.println("2.findHospitalById");
            System.out.println("3.getAllHospital");
            System.out.println("4.getAllPatientFromHospital");
            System.out.println("5.deleteHospitalById");
            System.out.println("6.getAllHospitalByAddress");
            System.out.println("7.add Department");
            System.out.println("8.removeById Department");
            System.out.println("9.updateById Department");
            System.out.println("10.getAllDepartmentByHospital");
            System.out.println("11.findDepartmentByName");
            System.out.println("12.findDoctorById");
            System.out.println("13.assignDoctorToDepartment");
            System.out.println("14.getAllDoctorsByHospitalId");
            System.out.println("15.getAllDoctorsByDepartmentId");
            System.out.println("16.addPatientsToHospital");
            System.out.println("17.getPatientById");
            System.out.println("18.getPatientByAge");
            System.out.println("19.sortPatientsByAge");
            System.out.println("20.add Doctor");
            System.out.println("21.removeById Doctor");
            System.out.println("22.updateById Doctor");
            System.out.println("0. exit");





            System.out.println("Enter your choice:");
            choice= scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("enter hospital name:");
                    String hospitalName= scanner.nextLine();
                    System.out.println("enter hospital address:");
                    String address= scanner.nextLine();
                    Hospital hospital=new Hospital(hospitalName,address,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
                    System.out.println(hospitalService.addHospital(hospital));
                    break;
                case 2:
                    System.out.println("Enter hospital id to find(hospital):");
                    Long id= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(hospitalService.findHospitalById(id));
                    break;
                case 3:
                    System.out.println(hospitalService.getAllHospital());
                    break;
                case 4:
                    System.out.println("get All Patient From Hospital number of id:");
                    Long id1= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(hospitalService.getAllPatientFromHospital(id1));
                    break;
                case 5:
                    System.out.println("enter hospital id to delete:");
                    Long id2= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(hospitalService.deleteHospitalById(id2));
                    break;
                case 6:
                    System.out.println("enter hospital  Address");
                    String adress1= scanner.nextLine();
                    System.out.println(hospitalService.getAllHospitalByAddress(adress1));
                    break;
                case 7:
                    System.out.println("Enter hospital id to add department:");
                    Long hospitalId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Enter department name:");
                    String departmentName = scanner.nextLine();
                   Department department=new Department(departmentName,new ArrayList<>());
                    System.out.println(hospitalService.add(hospitalId, department));
                    break;
                case 8:
                    System.out.println("enter department id to remove:");
                    Long id3= scanner.nextLong();
                    scanner.nextLine();
                    hospitalService.removeById(id3);
                    break;
                case 9:
                    System.out.println("Enter department id to update:");
                    Long departmentId = scanner.nextLong();
                    scanner.nextLine();
                    // Collecting details for the updated Department
                    System.out.println("Enter updated department name:");
                    String updatedDepartmentName = scanner.nextLine();
                    Department department1=new Department(updatedDepartmentName,new ArrayList<>());
                    System.out.println(hospitalService.updateById(departmentId, department1));
                    break;
                case 10:
                    System.out.println("enter hospital id to get All Department:");
                    Long id4= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(departmentService.getAllDepartmentByHospital(id4));
                    break;
                case 11:
                    System.out.println("Enter department name :");
                    String departmentName1= scanner.nextLine();
                    System.out.println(departmentService.findDepartmentByName(departmentName1));
                    break;
                case 12:
                    System.out.println("enter doctor id :");
                    Long idDoctor= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(doctorService.findDoctorById(idDoctor));
                    break;
                case 13:
                    System.out.println("Enter department id:");
                    Long departmentId1 = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Enter number of doctors to assign:");
                    int numberOfDoctors = scanner.nextInt();
                    scanner.nextLine();

                    List<Long> doctorsId = new ArrayList<>();
                    for (int i = 0; i < numberOfDoctors; i++) {
                        System.out.println("Enter doctor id:");
                        doctorsId.add(scanner.nextLong());
                        scanner.nextLine();
                    }

                    System.out.println(doctorService.assignDoctorToDepartment(departmentId1, doctorsId));
                    break;

                case 14:
                    System.out.println("enter hospital id to get All Doctors:");
                    Long id5= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(doctorService.getAllDoctorsByHospitalId(id5));
                    break;
                case 15:
                    System.out.println("enter Department id to get All Doctors:");
                    Long id6= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(doctorService.getAllDoctorsByDepartmentId(id6));
                    break;
                case 16:
                    System.out.println("enter hospital id:");
                    Long id7= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("enter patient first name:");
                    String firstName= scanner.nextLine();
                    System.out.println("enter patient last name:");
                    String lastName=scanner.nextLine();
                    System.out.println("enter patient age:");
                    int age= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter patient gender:");
                    Gender gender=Gender.valueOf(scanner.nextLine().toUpperCase());
                    List<Patient>patientList=new ArrayList<>();
                    Patient patient=new Patient(firstName,lastName,age,gender);
                    patientList.add(patient);

                    System.out.println(patientService.addPatientsToHospital(id7, patientList));
                    break;
                case 17:
                    System.out.println("enter patient id:");
                    Long idPatient= scanner.nextLong();
                    scanner.nextLine();
                    System.out.println(patientService.getPatientById(idPatient));
                    break;
                case 18:
                    System.out.println(patientService.getPatientByAge());
                    break;
                case 19:
                    System.out.println("enter sortPatientsByAge through the ('desc' or 'asc') :");
                    String userAnsw=scanner.nextLine();
                    System.out.println(patientService.sortPatientsByAge(userAnsw));
                    break;
                case 20:
                    System.out.println("Enter hospital id to add doctor:");
                    Long hospitalId1 = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("enter doctor first name:");
                    String firstName1= scanner.nextLine();
                    System.out.println("enter doctor last name:");
                    String lastName1=scanner.nextLine();
                    System.out.println("enter doctor age:");
                    int age1= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter doctor gender:");
                    Gender gender1=Gender.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("enter experience Year:");
                    int experienceYear= scanner.nextInt();
                    scanner.nextLine();
                    Doctor doctor=new Doctor(firstName1,lastName1,gender1,experienceYear);
                    System.out.println(hospitalService.add(hospitalId1, doctor));

                    break;
                case 21:
                    System.out.println("enter doctor id to remove:");
                    Long idDoctor1= scanner.nextLong();
                    scanner.nextLine();
                    hospitalService.removeDoctorById(idDoctor1);
                    break;
                case 22:
                    System.out.println("Enter doctor id to update:");
                    Long doctorId = scanner.nextLong();
                    scanner.nextLine();
                    // Collecting details for the updated Department
                    System.out.println("Enter experience Year:");
                    int newExperienceYear= scanner.nextInt();
                    Doctor newDoctor=new Doctor();
                    newDoctor.setExperienceYear(newExperienceYear);
                            System.out.println(hospitalService.updateById(doctorId, newDoctor));
                    break;
                case 0:
                    break;
            }


        }while (choice!=0);

    }
}