namespace playwright_nunit_csharp;

[TestFixture]
public class DuckDuckGoTests
{
    private PlaywrightDriver _driver;

    [SetUp]
    public async Task Setup()
    {
        _driver = new PlaywrightDriver();
        await _driver.InitializeAsync();
    }

    [TearDown]
    public async Task TearDown()
    {
        await _driver.CleanupAsync();
    }

    [Test]
    public async Task DuckDuckGo_Search_ReturnsResults()
    {
        await _driver.Page.GotoAsync("https://duckduckgo.com/");
        await _driver.Page.FillAsync("input[name='q']", "Playwright C#");
        await _driver.Page.PressAsync("input[name='q']", "Enter");

        await _driver.Page.WaitForSelectorAsync("#links");
        Assert.That(await _driver.Page.InnerTextAsync("#links"), Does.Contain("playwright.dev"));
    }
}
