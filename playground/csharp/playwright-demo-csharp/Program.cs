using System.Diagnostics.CodeAnalysis;
using Microsoft.Playwright;
using static Microsoft.Playwright.Assertions;

using var playwright = await Playwright.CreateAsync();
//await using var browser = await playwright.Chromium.LaunchAsync();
/*
    Default Paywright runs in headless mode. Also can use SlowMo to 
    slow down execution for debugging purposes. 
*/
await using var browser = await playwright.Chromium.LaunchAsync(new()
{
    Headless = false,
    SlowMo = 50
});
var page = await browser.NewPageAsync();
await page.GotoAsync("https://playwright.dev/dotnet");
await page.ScreenshotAsync(new ()
{
    Path = "screenshot.png"
});
/*
    Using Assertions:
    Plywrights web-first assertions, will automatically retry until 
    condition is met, or timeout is reached
*/
SetDefaultExpectTimeout(10_000);
await Expect(page.GetByRole(AriaRole.Link, new() { Name = "Get started" })).ToBeVisibleAsync();