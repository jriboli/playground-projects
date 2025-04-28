package DemoTestNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class ConfigAnnotations_3 {

    @BeforeMethod
    public void setup(Method method, ITestResult result) {
        String[] groups = method.getAnnotations(Test.class).groups();

        if(groups != null) {
            for (String group : groups) {

            }
        }
    }
}
