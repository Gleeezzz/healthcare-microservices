package com.healthcare.webapp.controller;

import com.healthcare.webapp.feign.PatientClient;
import com.healthcare.webapp.feign.UserClient;
import com.healthcare.webapp.model.PatientModel;
import com.healthcare.webapp.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private PatientClient patientClient;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserModel user) {

        user.setRole("MEDECIN");
        userClient.create(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        // La page d'accueil affiche simplement le tableau de bord avec les actions rapides
        return "home";
    }

    @GetMapping("/patients")
    public String listPatients(Model model) {
        try {
            // Appel au microservice patient-service via Feign
            List<PatientModel> patients = patientClient.getAllPatients();
            model.addAttribute("patients", patients);
        } catch (Exception e) {
            // En cas de panne du microservice, liste vide pour éviter le crash
            model.addAttribute("patients", new java.util.ArrayList<>());
            model.addAttribute("errorService", "Impossible de récupérer la liste des patients pour le moment.");
        }
        return "patients"; // Redirige vers le nouveau template patients.html
    }

    // 1. Afficher le formulaire d'ajout
    @GetMapping("/patients/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new PatientModel());
        return "add-patient"; // Le nom de notre futur fichier HTML
    }

    // 2. Traiter les données envoyées par le formulaire
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute("patient") PatientModel patient, Model model) {
        try {
            // On envoie les données au microservice via Feign
            patientClient.createPatient(patient);
            // Si ça marche, on redirige vers la liste des patients avec un message de succès
            return "redirect:/patients";
        } catch (Exception e) {
            // Si le microservice rejette la demande ou est en panne
            model.addAttribute("error", "Erreur lors de la création du patient. Veuillez vérifier les informations.");
            return "add-patient"; // On reste sur le formulaire pour ne pas perdre les saisies
        }
    }
}