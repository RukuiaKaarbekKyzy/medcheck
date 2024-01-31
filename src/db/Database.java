package db;

import model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Hospital> hospitals = new ArrayList<>();
    public static List<Hospital> getHospitals() {
        return hospitals;
    }

}

