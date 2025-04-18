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
        public HomePage(Support.Hooks hooks) : base(hooks) { }

        private ILocator LoginButton => Page.Locator("a[aria-label='Login']");
        private ILocator ShopButton => Page.Locator("a[normalize-space(text())='Shop']");
        private ILocator CartButton => Page.GetByRole(AriaRole.Navigation, new() { Name = "Cart" });
        private ILocator CheckoutButton => Page.GetByRole(AriaRole.Navigation, new() { Name = "Checkout" });
        private ILocator MyAccountButton => Page.GetByRole(AriaRole.Navigation, new() { Name = "My account" });

        public async Task<LoginPage> GoToLogin()
        {
            await LoginButton.ClickAsync();
            return new LoginPage(Page);
        }

        public async Task<StorePage> GoToShop()
        {
            await ShopButton.ClickAsync();
            return new StorePage(Page);
        }

        public async Task<CartPage> GoToCart()
        {
            await CartButton.ClickAsync();
            return new CartPage(Page);
        }

        public async Task<CheckoutPage> GoToCheckout()
        {
            await CheckoutButton.ClickAsync();
            return new CheckoutPage(Page);
        }

        public async Task<MyAccountPage> GoToMyAccount()
        {
            await MyAccountButton.ClickAsync();
            return new MyAccountPage(Page);
        }
    }
}
