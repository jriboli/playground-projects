package DemoTestNG;

import org.testng.annotations.*;

@Test(groups = "e2e")
public class Test002_ConfigAnnotations {
    @BeforeMethod
    public void setup() {
        System.out.println("    (4) Execute before each Test Method");
    }

    @AfterMethod
    public void teardown() {
        System.out.println("    (4) Execute after each Test Method");
    }

    public void test3_fakeProduct() {
        System.out.println("     (5) Test Method 3: Fake Product");
    }

    public void test4_fakeEmail() {
        System.out.println("     (5) Test Method 4: Fake Email");
    }

    @BeforeClass
    public void classSetup() {
        System.out.println("   (3) Execute before class - ConfigAnnotations_2");
    }

    @AfterClass
    public void classTeardown() {
        System.out.println("   (3) Execute after class - ConfigAnnotations_2");
    }
}

