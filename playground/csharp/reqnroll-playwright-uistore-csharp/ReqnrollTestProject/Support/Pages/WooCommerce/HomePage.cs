using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    public class HomePage : BasePage
    {
        public HomePage(IPage page) : base(page) { }

        private ILocator LoginButton => Page.Locator("a[aria-label='Login']");
        private ILocator StoreButton => Page.Locator("a[normalize-space(text())='Shop']");
        private ILocator CartButton => Page.GetByRole(AriaRole.Navigation, new() { Name = "Cart" });

        public async Task<LoginPage> GoToLogin()
        {
            await LoginButton.ClickAsync();
            return new LoginPage(Page);
        }

        public async Task<StorePage> GoToShop()
        {
            await StoreButton.ClickAsync();
            return new StorePage(Page);
        }

        public async Task<CartPage> GoToCart()
        {
            await CartButton.ClickAsync();
            return new CartPage(Page);
        }
    }
}
