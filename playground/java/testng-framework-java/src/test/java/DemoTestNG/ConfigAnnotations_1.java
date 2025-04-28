package DemoTestNG;

import org.testng.annotations.*;

public class ConfigAnnotations_1 {

    @BeforeMethod
    public void setup() {
        System.out.println("    (4) Execute before each Test Method");
    }

    @AfterMethod
    public void teardown() {
        System.out.println("    (4) Execute after each Test Method");
    }

    @Test(groups = "smoke")
    public void test1_fakeButton() {
        System.out.println("     (5) Test Method 1: Fake Button");
    }

    @Test(groups= {"smoke", "regression"})
    public void test2_fakeTxtBox() {
        System.out.println("     (5) Test Method 2: Fake TextBox");
    }

    @BeforeClass(alwaysRun = true)
    public void classSetup() {
        System.out.println("   (3) Execute before class - ConfigAnnotations_1");
    }

    @AfterClass
    public void classTeardown() {
        System.out.println("   (3) Execute after class - ConfigAnnotations_1");
    }

    @BeforeTest
    public void testSetup() {
        System.out.println("  (2) Execute before test");
    }

    @AfterTest
    public void testTeardown() {
        System.out.println("  (2) Execute after test");
    }

    @BeforeGroups(groups = {"smoke"})
    public void groupSetup() {
        System.out.println(" (1) Execute before group - Smoke");
    }

    @AfterGroups(groups = {"smoke"})
    public void groupTeardown() {
        System.out.println(" (1) Execute after group - Smoke");
    }
}
