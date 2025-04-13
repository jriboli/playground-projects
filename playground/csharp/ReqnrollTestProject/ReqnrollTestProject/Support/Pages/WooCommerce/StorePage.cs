using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    internal class StorePage : BasePage
    {
        // Constructor
        public StorePage(IPage page) : base(page) { }

        // Locators
        private ILocator ProductResults => Page.Locator("div[@data-block-name='woocommerce/product-collection']");
        private ILocator ProductSorter => Page.Locator("orderby");


        // Actions
        public async Task<ProductPage> SelectProduct(int position)
        {
            ILocator ProductImage = Page.Locator("(img[@data-testid='product-image'])[1]");
            await ProductImage.ClickAsync();
            return new ProductPage(Page);
        }

        public async void AddProductToCart(int position)
        {

        }
    }
}
