package DemoTestNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ConfigAnnotations_3 {

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, ITestResult result) {
        Annotation[] groups = method.getAnnotations();

//        if (groups != null) {
//            for (String group : groups) {
//                if (group.equals("sanity")) {
//                    System.out.println("Setting up for SANITY group test");
//                    // setup sanity-specific data
//                } else if (group.equals("regression")) {
//                    System.out.println("Setting up for REGRESSION group test");
//                    // setup regression-specific data
//                }
//            }
//        }
    }

    @Test(groups = {"sanity"})
    public void testAddItemToCart() {
        System.out.println("Running SANITY test");
    }

    @Test(groups = {"regression"})
    public void testRemoveItemFromCart() {
        System.out.println("Running REGRESSION test");
    }
}
