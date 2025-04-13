using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Utilities
{
    public static class LocatorHelper
    {
        public static async Task<ILocator> FindFirstVisibleLocatorAsync(IPage page, params string[] selectors)
        {
            foreach (var selector in selectors)
            {
                var locator = page.Locator(selector);
                if (await locator.IsVisibleAsync())
                {
                    return locator;
                }
            }

            throw new Exception("None of the provided selectors matched a visible element.");
        }
    }
}
