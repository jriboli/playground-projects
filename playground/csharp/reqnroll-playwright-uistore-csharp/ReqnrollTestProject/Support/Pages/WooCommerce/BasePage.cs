using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    public class BasePage
    {
        protected readonly IPage Page;
        private readonly string _baseUrl;

        public BasePage(Support.Hooks hooks)
        {
            Page = hooks.User;
            _baseUrl = Environment.GetEnvironmentVariable("BASE_URL") ?? throw new InvalidOperationException("BASE_URL not set in environment variables.");
        }

        public async Task NavigateToAsync(string relativeUrl)
        {
            var fullUrl = $"{_baseUrl}/{relativeUrl.TrimStart('/')}";
            await Page.GotoAsync(fullUrl);
        }
    }
}
