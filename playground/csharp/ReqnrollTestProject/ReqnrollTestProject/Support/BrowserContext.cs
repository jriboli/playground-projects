using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace ReqnrollTestProject.Support
{
    public class BrowserContext
    {
        public IPage Page { get; private set; }

        public async Task LaunchBrowserAsync()
        {
            var playwright = await Playwright.CreateAsync();
            var browser = await playwright.Chromium.LaunchAsync(new() { Headless = true });
            var context = await browser.NewContextAsync();
            Page = await context.NewPageAsync();
        }

        public async Task DisposeAsync()
        {
            await Page.Context.CloseAsync();
            // await Page.Browser.CloseAsync();
            await Page.CloseAsync();
        }
    }
}
