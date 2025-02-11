package petstore.factory;

import petstore.enums.PetCategory;
import petstore.enums.PetStatus;
import petstore.enums.PetTag;
import petstore.pojos.Pet;

import java.util.Random;

public class PetFactory {

    private static final Random random = new Random();

    public static Pet createPet(int id, PetCategory category, String name, PetTag[] tags, PetStatus status) {
        return new Pet(id, category, name, tags, status);
    }

    // Method to generate a random pet
    public static Pet createRandomPet() {
        int id = random.nextInt(1000);
        PetCategory category = getRandomCategory();
        String name = getRandomName();
        PetTag[] tags = getRandomTags();
        PetStatus status = getRandomStatus();

        return new Pet(id, category, name, tags, status);
    }

    // Utility method for random generation
    private static PetCategory getRandomCategory() {
        PetCategory[] categories = PetCategory.values();
        int randomIndex = random.nextInt(categories.length);
        return categories[randomIndex];
    }

    private static String getRandomName() {
        String[] names = {"Buddy", "Rover", "Charlie", "Max", "Hunter", "Shadow", "Drax", "Lucy", "Bella"};
        return names[random.nextInt(names.length)];
    }

    private static PetTag[] getRandomTags() {
        PetTag[] tags = PetTag.values();
        int numOfTages = random.nextInt(tags.length);
        PetTag[] selectedTags = new PetTag[numOfTages];
        for(int i = 0; i < numOfTages; i++) {
            int selectedTag = random.nextInt(tags.length);
            PetTag tag = tags[selectedTag];
            selectedTags[i] = tag;
        }
        return selectedTags;
    }

    private static PetStatus getRandomStatus() {
        PetStatus[] statuses = PetStatus.values();
        int randomIndex = random.nextInt(statuses.length);
        return statuses[randomIndex];
    }
}
