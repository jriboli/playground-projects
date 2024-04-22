package com.helldivers;

import com.helldivers.entity.Stratagem;
import com.helldivers.repository.StratagemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// https://examples.javacodegeeks.com/testing-spring-data-jpa-with-datajpatest/
// properties: This attribute allows you to specify additional properties to be used in configuring the application context.
@DataJpaTest(properties = {"spring.jpa.hibernate.ddl-auto=create", "spring.datasource.initialization-mode=never"})
public class JPAExampleTest {
    /***
     * Note: Had to use JUnit as TestNG was not working well with Dependency Injection
     */

    @Autowired
    private StratagemRepository stmRepo;

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(stmRepo);
    }
    @Test
    public void addStratagem() {
        Stratagem stratagem = new Stratagem("Reinforce", "A replacement for a fallen comrade.", "up,down,right,left,up", "Special", "Mission", -1, 0, 6, null);
        Stratagem savedStratagem = stmRepo.save(stratagem);
        System.out.println("Saved: " + savedStratagem);

        List<Stratagem> findByCategory = stmRepo.findByCategory(stratagem.getCategory());
        assertTrue(findByCategory.contains(stratagem));
    }
}
