
package com.itvedant.medislot.repositories;

import com.itvedant.medislot.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
