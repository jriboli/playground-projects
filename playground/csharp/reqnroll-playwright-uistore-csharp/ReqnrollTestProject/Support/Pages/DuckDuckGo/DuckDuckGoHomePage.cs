using Microsoft.Playwright;
using FluentAssertions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReqnrollTestProject.Utilities;

namespace ReqnrollTestProject.Support.Pages.DuckDuckGo
{
    // Follow this patter with POMs 
    // - Constructors
    // - Selectors
    // - Actions and assertions

    public class DuckDuckGoHomePage
    {
        private readonly IPage _user;

        public DuckDuckGoHomePage(Support.Hooks hooks)
        {
            _user = hooks.User;
        }

        private ILocator SearchInput;
        //private ILocator SearchInput => _user.Locator("input[id='searchbox_input']");
        //private async Task<ILocator> SearchInput => await LocatorHelper.FindFirstVisibleLocatorAsync(_user, "input[id='searchbox_input']");
        private ILocator SearchButton => _user.Locator("button[type='submit']");

        public async void LoadPageLocators()
        {
            SearchInput = await LocatorHelper.FindFirstVisibleLocatorAsync(_user, "input[id='searchbox_input']");
        }

        public async Task AssertPageContent()
        {
            // Wait for the search input to appear first(implicitly waits for the page)
            await SearchInput.WaitForAsync(new LocatorWaitForOptions { State = WaitForSelectorState.Visible });

            //Assert that the correct URL has been reached
            _user.Url.Should().Be("https://duckduckgo.com/");

            //Assert that the search input is visible
            var searchInputVisibility = await SearchInput.IsVisibleAsync();
            searchInputVisibility.Should().BeTrue();

            // //Assert that the search button is visible
            var searchBtnVisibility = await SearchButton.IsVisibleAsync();
            searchBtnVisibility.Should().BeTrue();
        }

        public async Task SearchAndEnter(string searchTerm)
        {
            //Type the search term into the search input
            await SearchInput.FillAsync(searchTerm);

            //Assert that the search input has the text entered
            var searchInputInnerText = await SearchInput.InputValueAsync();
            searchInputInnerText.Should().Be(searchTerm);

            //Click the search button to submit the search
            await SearchButton.ClickAsync();
        }
    }
}
