using Microsoft.Playwright;

namespace playwright_nunit_csharp;

public class Tests
{
    [SetUp]
    public void Setup()
    {
    }

    [Test]
    public async Task LoginTC_01()
    {
        var playwright = await Playwright.CreateAsync();
        var browser = await playwright.Chromium.LaunchAsync(new BrowserTypeLaunchOptions
        {
            Headless = false, 
            SlowMo = 50,
            Timeout = 80000
        });
        var context = await browser.NewContextAsync();
        var page = await context.NewPageAsync();

        await page.SetViewportSizeAsync(1920, 1080);
        await page.GotoAsync("https://duckduckgo.com");
        await page.FillAsync("input[name='q']", "Playwright C#");
        await page.PressAsync("input[name='q']", "Enter");

        await page.CloseAsync();

    }
}
