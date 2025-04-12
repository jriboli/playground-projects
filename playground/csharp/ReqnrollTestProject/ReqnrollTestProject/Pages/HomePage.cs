using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Pages
{
    public class HomePage : BasePage
    {
        public HomePage(IPage page) : base(page) { }

        private ILocator SearchInput => Page.Locator("#woocommerce-product-search-field-0");
        private ILocator SearchButton => Page.Locator("button[type='submit']");
        private ILocator LoginButton => Page.Locator("a[aria-label='Login']");

        public async Task SearchProduct(string productName)
        {
            await SearchInput.FillAsync(productName);
            await SearchButton.ClickAsync();
        }

        public async Task<LoginPage> GoToLogin()
        {
            await LoginButton.ClickAsync();
            return new LoginPage(Page);
        }
    }
}
