package com.healthcare.webapp.feign;

import com.healthcare.webapp.model.PatientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Le nom "patient-service" correspond au nom sous lequel le microservice s'enregistre dans Consul
@FeignClient(name = "patient-service")
public interface PatientClient {

    // Cette route doit correspondre à celle définie dans le Controller de ton patient-service
    @GetMapping("/patients")
    List<PatientModel> getAllPatients();
}
