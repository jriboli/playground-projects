using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    public class CartPage : BasePage
    {
        public CartPage(IPage page) : base(page)
        {
        }

        private ILocator cartMessage => Page.GetByRole(AriaRole.Heading, new() { Name = "Your cart is empty" });
        private ILocator cartBlock => Page.Locator("div[data-block-name='woocommerce/cart']");

        public async Task<bool> IsCartEmpty()
        {
            return await cartMessage.IsVisibleAsync();
        }



    }
}
