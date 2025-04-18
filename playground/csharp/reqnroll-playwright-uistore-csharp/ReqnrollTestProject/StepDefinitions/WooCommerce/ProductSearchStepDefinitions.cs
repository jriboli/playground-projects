using Microsoft.Playwright;
using NUnit.Framework;
using ReqnrollTestProject.Support;
using ReqnrollTestProject.Support.Pages.WooCommerce;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.StepDefinitions.WooCommerce
{
    [Binding]
    public class ProductSearchSteps
    {
        private readonly HomePage _homePage;
        private readonly IPage _page;

        public ProductSearchSteps(HomePage homePage, BrowserContext context)
        {
            _homePage = homePage;
            _page = context.Page;
        }

        [Given("I am on the homepage")]
        public async Task GivenIAmOnTheHomepage()
        {
            await _page.GotoAsync("https://yourstore.com");
        }

        [When("I search for {string}")]
        public async Task WhenISearchFor(string keyword)
        {
            //await _homePage.SearchProduct(keyword);
        }

        [Then("I should see search results")]
        public async Task ThenIShouldSeeSearchResults()
        {
            Assert.That(await _page.Locator(".woocommerce-result-count").IsVisibleAsync(), Is.True);
        }
    }

}
