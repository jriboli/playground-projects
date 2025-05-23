﻿using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support
{
    [Binding]
    public sealed class Hooks
    {
        private readonly ScenarioContext _scenarioContext;
        private readonly BrowserContext _context;
        public IPage User { get; private set; } = null!; // -> We'll call this property in the tests

        public Hooks(ScenarioContext scenarioContext, BrowserContext context)
        {
            _scenarioContext = scenarioContext;
            _context = context;
        }        

        [BeforeScenario] // -> Notice how we're doing these steps before each scenario
        public async Task RegisterSingleInstancePractitioner()
        {
            //Initialise Playwright
            var playwright = await Playwright.CreateAsync();
            //Initialise a browser - 'Chromium' can be changed to 'Firefox' or 'Webkit'
            var browser = await playwright.Chromium.LaunchAsync(new BrowserTypeLaunchOptions
            {
                Headless = false // -> Use this option to be able to see your test running
            });
            //Setup a browser context
            var context = await browser.NewContextAsync();

            //Initialise a page on the browser context.
            User = await context.NewPageAsync();
        }

        [AfterScenario]
        public async Task AfterScenario()
        {
            await _context.DisposeAsync();
        }
    }
}
