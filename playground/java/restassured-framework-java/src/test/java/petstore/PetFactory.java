package petstore;

import petstore.pojos.Pet;
import petstore.pojos.PetCategory;
import petstore.pojos.PetTag;

import java.util.Random;

public class PetFactory {

    private static final Random random = new Random();

    public static Pet createPet(int id, PetCategory category, String name, String[] photoUrls, PetTag[] tags, String status) {
        return new Pet(id, category, name, photoUrls, tags, status);
    }

    // Method to generate a random pet
    public static Pet createRandomPet() {
        int id = random.nextInt(1000);
        PetCategory category = getRandomCategory();
        String name = getRandomName();
        String[] photoUrls = new String[] {"https://example.com/pet"+id+".jpg"};
        PetTag[] tags = getRandomTags();
        String status = getRandomStatus();

        return new Pet(id, category, name, photoUrls, tags, status);
    }

    // Utility method for random generation
    private static PetCategory getRandomCategory() {
        String[] categories = new String[] {"Dog", "Cat", "Bird", "Fish"};
        PetCategory category = new PetCategory();
        int categoryId = random.nextInt(categories.length);
        category.setId(categoryId);
        category.setName(categories[categoryId]);
        return category;
    }

    private static String getRandomName() {
        String[] names = {"Buddy", "Rover", "Charlie", "Max", "Hunter", "Shadow", "Drax", "Lucy", "Bella"};
        return names[random.nextInt(names.length)];
    }

    private static PetTag[] getRandomTags() {
        String[] tags = new String[] {"Friendly", "Trained", "Vaccinated", "Microchipped"};
        int numOfTages = random.nextInt(tags.length);
        PetTag[] selectedTags = new PetTag[numOfTages];
        for(int i = 0; i < numOfTages; i++) {
            int selectedTag = random.nextInt(tags.length);
            PetTag tag = new PetTag();
            tag.setId(selectedTag);
            tag.setName(tags[selectedTag]);
            selectedTags[i] = tag;
        }
        return selectedTags;
    }

    private static String getRandomStatus() {
        String[] statuses = {"Available", "Pending", "Sold"};
        return statuses[random.nextInt(statuses.length)];
    }
}
