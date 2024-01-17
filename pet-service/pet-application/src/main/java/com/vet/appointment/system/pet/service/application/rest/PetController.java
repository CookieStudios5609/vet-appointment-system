package com.vet.appointment.system.pet.service.application.rest;

import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetCommand;
import com.vet.appointment.system.pet.service.domain.dto.create.CreatePetResponse;
import com.vet.appointment.system.pet.service.domain.ports.input.PetApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    private final PetApplicationService petApplicationService;

    public PetController(PetApplicationService petApplicationService) {
        this.petApplicationService = petApplicationService;
    }

    @GetMapping("/api/test")
    public String test() {
        return "Hello world!";
    }

    @PostMapping("/api/pet")
    public ResponseEntity<CreatePetResponse> createPetRequest(@RequestBody @Valid CreatePetCommand createPetCommand) {
        CreatePetResponse createPetResponse = petApplicationService.createPet(createPetCommand);

        return ResponseEntity.ok(createPetResponse);
    }
}
