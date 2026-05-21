package com.healthcare.webapp.feign;

import com.healthcare.webapp.model.PatientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient-service")
public interface PatientClient {

    @GetMapping("/patients")
    List<PatientModel> getAllPatients();

    // Nouvelle plomberie : Envoi du modèle pour création
    @PostMapping("/patients")
    PatientModel createPatient(@RequestBody PatientModel patient);
}
