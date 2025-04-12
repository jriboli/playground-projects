using Microsoft.Playwright;
using ReqnrollTestProject.Pages.DuckDuckGo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.StepDefinitions.DuckDuckGo
{
    [Binding]
    public class SearchSteps
    {
        private readonly IPage _user;
        private readonly DuckDuckGoHomePage _duckDuckGoHomePage;

        public SearchSteps(Hooks.Hooks hooks, DuckDuckGoHomePage duckDuckGoHomePage)
        {
            _user = hooks.User;
            _duckDuckGoHomePage = duckDuckGoHomePage;
        }

        [Given(@"the user is on the DuckDuckGo homepage")]
        public async Task GivenThenUserIsOnTheDuckDuckGoHomepage()
        {
            // Go to the DuckDuckGo homepage
            await _user.GotoAsync("https://duckduckgo.com/");

            // Assert the page
            await _duckDuckGoHomePage.AssertPageContent();

            // Notes for process: 

            /*
             * So the DuckDuckGoHomePage instance that gets injected already has the correct IPage (browser tab) instance, because:
             * 1. Hooks creates the IPage browser context.
             * 2. The DI container injects that same IPage into your page objects.
             * 3. When you call AssertPageContent, it uses that already-open IPage.
             * 
             * It’s not magic — it’s dependency injection.
             * In your DI setup (likely in Startup.cs, Program.cs, or SpecFlow/ReqNroll config), you're telling it to:
             * Inject shared objects like IPage from Hooks
             * Auto-wire PageObjects like DuckDuckGoHomePage using that IPage
             */
        }

        [When(@"the user searches for '(.*)'")]
        public async Task WhenTheUserSearchesFor(string searchTerm)
        {
            // Type the search term and press enter
            await _duckDuckGoHomePage.SearchAndEnter(searchTerm);
        }
    }
}
