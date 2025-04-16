using Microsoft.Playwright;
using ReqnrollTestProject.Support.Pages.WooCommerce;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.StepDefinitions.WooCommerce
{
    [Binding]
    public class CartStepDefinitions
    {
        private readonly HomePage _homepage;
        private readonly IPage _page;

        CartPage _cartPage;

        public CartStepDefinitions(HomePage homepage, IPage page)
        {
            _homepage = homepage;
            _page = page;
        }

        [When("The user navigates to the cart")]
        public async Task WhenUserNavigatesToTheCart()
        {
            _cartPage = await _homepage.GoToCart();
        }

        [When("The user adds an item to the cart")]
        public async Task WhenTheUserAddsAnItemToTheCart()
        {

        }

        [When("I dont add any items to cart")]
        public void WhenIDontAddItemsToCart()
        {
            // This is intentionally doing nothing
            // For now
        }

        [Then("The cart should contain the item")]
        public void ThenCartShouldContainTheItem()
        {
            Assert.That(_cartPage.IsCartEmpty(), Is.True);
        }
    }
}
