using Microsoft.Playwright;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support.Pages.WooCommerce
{
    public class StorePage : BasePage
    {
        // Constructor
        public StorePage(IPage page) : base(page) { }

        // Locators
        private ILocator ProductResults => Page.Locator("div[@data-block-name='woocommerce/product-collection']");
        private ILocator ProductSorter => Page.Locator("orderby");


        // Actions
        public async Task<ProductDetailsPage> SelectProduct(int position)
        {
            ILocator ProductImage = Page.Locator("(img[@data-testid='product-image'])[1]");
            await ProductImage.ClickAsync();
            return new ProductDetailsPage(Page);
        }

        public async void AddProductToCart(int position)
        {
            ILocator ProductAddToCartButton = Page.Locator("(button[@data-wp-on--click='actions.addCartItem'])[1]");
            await ProductAddToCartButton.ClickAsync();
        }

        public async void ClickViewCart()
        {
            ILocator CartBadge = Page.Locator("wc-block-mini-cart__quantity-badge");
            await CartBadge.ClickAsync();

            ILocator CartBlock = Page.Locator("wp-block-woocommerce-empty-mini-cart-contents-block");
        }

        public async void CheckItemInCart()
        {
             ILocator CartBlock = Page.Locator("wp-block-woocommerce-empty-mini-cart-contents-block");
        }

        public bool IsPageValid() 
        {
            return true;
        }

        public async void CaptureScreenShot()
        {
            await Page.ScreenshotAsync(new() { Path = "StorePage_Base.png" });
        }
    }
}
