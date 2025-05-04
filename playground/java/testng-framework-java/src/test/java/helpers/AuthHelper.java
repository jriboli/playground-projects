package helpers;

import com.microsoft.playwright.*;
import com.woocommerce.pages.LoginPage;

import java.nio.file.Paths;
import java.nio.file.Path;

public class AuthHelper {

    public static BrowserContext createContextWithState(Playwright playwright, Browser browser, String stateFileName) {
        Path statePath = Paths.get("resources/state/" + stateFileName);  // Store state files here
        return browser.newContext(new Browser.NewContextOptions().setStorageStatePath(statePath));
    }

    public static void saveLoginState(String username, String password, String stateFilePath) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("http://localhost:9090/?page_id=9");
            LoginPage lp = new LoginPage(page);
            lp.setUsername(username);
            lp.setPassword(password);
            lp.clickLogin();
            page.waitForURL("**/?page_id=9");

            // Save storage state for this user
            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get(stateFilePath)));

            System.out.println("✅ Login state saved.");
        }
    }
}
