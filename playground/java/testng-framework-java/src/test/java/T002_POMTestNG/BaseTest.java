package T002_POMTestNG;

import helpers.AuthHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {

    @BeforeClass
    public void setupWithStorage() {

    }

    @Test
    public void setupRocketState() {
        AuthHelper.saveLoginState("Rocket", "l3iBmkszkYuxlA(IfkzUjNO&", "resources/state/rocket-state.json");
        AuthHelper.saveLoginState("groot", "TV*PLJSmj!x8fx$AtuxRgHwf", "resources/state/groot-state.json");
    }
}
