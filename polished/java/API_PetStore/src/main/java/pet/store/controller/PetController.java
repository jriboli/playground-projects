package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pet.store.model.PetData;
import pet.store.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Slf4j
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public List<PetData> findAllPets() {
        log.info("Pulling all pet information");

        return petService.findAllPets();
    }

    @GetMapping("/{petId}")
    public PetData findPetById(@PathVariable Long petId) {
        log.info("Finding pet with ID {}", petId);

        return petService.findPet(petId);
    }

    @PostMapping()
    public PetData savePet(@RequestBody PetData petData) {
        log.info("Adding new Pet");

        return petService.savePet(petData);
    }

    @PutMapping("/{petId}")
    public PetData updatePet(@PathVariable Long petId, @RequestBody PetData petData) {
        log.info("Updating pet Status");

        return petService.updatePetStatus(petId, petData.getStatus());
    }
}
