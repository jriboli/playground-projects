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
        private ILocator ShopButton => Page.Locator("a[normalize-space(text())='Shop']");

        public async Task<LoginPage> GoToLogin()
        {
            await LoginButton.ClickAsync();
            return new LoginPage(Page);
        }

        public async Task<ProductPage> GoToShop()
        {
            await ShopButton.ClickAsync();
            return new ProductPage(Page);
        }
    }
}
