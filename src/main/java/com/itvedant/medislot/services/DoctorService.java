
package com.itvedant.medislot.services;

import com.itvedant.medislot.models.Doctor;
import com.itvedant.medislot.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(int id, Doctor doctor) {
        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setName(doctor.getName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setMobile(doctor.getMobile());
        existingDoctor.setConsultationFee(doctor.getConsultationFee());

        return doctorRepository.save(existingDoctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void deleteDoctor(int id) {
        getDoctorById(id);
        doctorRepository.deleteById(id);
    }
}
