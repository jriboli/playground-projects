import { chromium, test } from "@playwright/test";

test("Login test demo", async () => {
  const browser = await chromium.launch({
    headless: false,
  });
  const context = await browser.newContext();
  const page = await context.newPage();

  await page.goto("https://ecommerce-playground.lambdatest.io/");
  await page.hover("//*[@id='widget-navbar-217834']/ul/li[6]/a/div/span");
  //await page.click("text=Login");
  await page.click("'Login'"); // Use of single quotes
});
