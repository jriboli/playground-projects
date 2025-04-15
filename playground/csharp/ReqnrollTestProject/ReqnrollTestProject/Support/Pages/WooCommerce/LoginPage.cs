using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    public class LoginPage : BasePage
    {
        public LoginPage(IPage page) : base(page) { }

        private ILocator Username => Page.Locator("#username");
        private ILocator Password => Page.Locator("#password");
        private ILocator Submit => Page.Locator("button[type='submit']");


        public async Task<HomePage> LoginAsync(string user, string pass)
        {
            await Username.FillAsync(user);
            await Password.FillAsync(pass);
            await Submit.ClickAsync();
            return new HomePage(Page); // redirect to HomePage after login
        }
    }
}
