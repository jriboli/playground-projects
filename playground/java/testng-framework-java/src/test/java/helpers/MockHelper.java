package helpers;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;

import java.util.HashMap;
import java.util.Map;

import static helpers.WooCommerceAPIHelper.*;

public class MockHelper {

    // 0. Simulate network failure
    public static void failAddCoupon(Page page) {
        page.route("**/index.php?rest_route=/wc/store/v1/batch**", Route::abort);
    }

    // 1. Mock invalid coupon code
    public static void mockInvalidCouponCode(Page page) {
        page.route("**/index.php?rest_route=/wc/store/v1/batch**", route -> {
            System.out.println("REQUEST: " + route.request().postData());
            String coupon = extractCouponCodeFrom(route.request().postData());
            String nonce = extractNonceFrom(route.request().postData());

            System.out.println("COUPON: " + coupon);
            System.out.println("NONCE: " + nonce);

            String mockResponse = getMockResponse("mock-coupon-response.json", Map.of(
                    "COUPON_CODE", coupon,
                    "NONCE", nonce));

            route.fulfill(new Route.FulfillOptions()
                    .setStatus(207)
                    .setContentType("application/json")
                    .setBody(mockResponse));
        });
    }

    // 1. Mock successful response
    public static void mockSuccessfulCart(Page page, String mockJson) {
        page.route("**/cart", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockJson));
        });
    }

    // 2. Simulate network failure
    public static void failCartRequest(Page page) {
        page.route("**/cart", Route::abort);
    }

    // 3. Slow down the API response
    public static void delayCartResponse(Page page, String mockJson, int delayInMs) {
        page.route("**/cart", route -> {
            try {
                Thread.sleep(delayInMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockJson));
        });
    }

    // 4. Return empty response
    public static void emptyCartResponse(Page page) {
        page.route("**/cart", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody("[]")); // Empty JSON array
        });
    }

    // 5. Return error response
    public static void errorCartResponse(Page page, int errorCode) {
        page.route("**/cart", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(errorCode)
                    .setContentType("application/json")
                    .setBody("{\"error\": \"Simulated failure.\"}"));
        });
    }
}
