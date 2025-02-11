package pet.store.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.store.dao.PetDao;
import pet.store.entity.Pet;
import pet.store.entity.Store;
import pet.store.enums.PetStatus;
import pet.store.model.PetData;
import pet.store.model.StoreEmployeeData;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class PetService {

    private PetDao petDao;

    public PetService(PetDao petDao) {
        this.petDao = petDao;
    }

    public List<PetData> findAllPets() {
        List<Pet> pets = petDao.findAll();
        List<PetData> results = new ArrayList<>();

        pets.forEach(pet -> results.add(new PetData(pet)));
        return results;
    }

    public PetData findPet(Long petId) {
        Pet pet = findOrCreatePet(petId);
        return new PetData(pet);
    }

    public PetData savePet(PetData petData) {
        Long petId = petData.getPetId();
        Pet pet = findOrCreatePet(petId);

        setFieldsInPet(pet, petData);
        return new PetData(petDao.save(pet));
    }

    public PetData updatePetStatus(Long petId, PetStatus status) {

        // TBD

        return null;
    }

    private void setFieldsInPet(Pet pet, PetData petData) {
        pet.setPetId(petData.getPetId());
        pet.setName(petData.getName());
        pet.setTags(petData.getTags());
        pet.setStatus(petData.getStatus());
        pet.setCategory(petData.getCategory());
    }

    private Pet findOrCreatePet(Long petId) {
        Pet pet;
        if(Objects.isNull(petId)) {
            pet = new Pet();
        }
        else {
            pet = findPetById(petId);
        }

        return pet;
    }

    private Pet findPetById(Long petId) {
        return petDao.findById(petId).orElseThrow(() -> new NoSuchElementException("Pet with ID=" + petId + " was not found."));
    }
}
