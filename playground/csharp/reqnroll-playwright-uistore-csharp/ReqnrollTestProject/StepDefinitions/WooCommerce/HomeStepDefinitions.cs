using System;
using System.Threading.Tasks;
using Microsoft.Playwright;
using NUnit.Framework;
using Reqnroll;
using ReqnrollTestProject.Support.Pages.WooCommerce;

namespace ReqnrollTestProject.StepDefinitions.WooCommerce
{
    [Binding]
    public class HomeStepDefinitions
    {
        private readonly HomePage _homepage;
        private readonly IPage _page;
        private StorePage _storePage;

        public HomeStepDefinitions(HomePage homepage, Support.Hooks hooks)
        {
            _homepage = homepage;
            _page = hooks.User;
        }

        [Given("I am on the HomePage")]
        public async Task GivenIAmOnTheHomePage()
        {
            // Do nothing
        }

        [When("I click the Store button")]
        public async Task WhenIClickOnTheStoreButton()
        {
            _storePage = await _homepage.GoToShop();
        }

        [Then("I should be on the Store Page")]
        public async Task ThenIShouldBeLoggedIn()
        {
            _storePage.CaptureScreenShot();
            Assert.That(_storePage.IsPageValid());
        }
    }
}
