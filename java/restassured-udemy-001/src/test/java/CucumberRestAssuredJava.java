import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import framework.playloads.bookstore.User;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberRestAssuredJava {
    private RequestSpecification req;
    private Response res;

    @Given("I am an Authorized User")
    public void i_am_an_authorized_user() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        req = RestAssured.given().log().all();

        User existingUser = new User();
        existingUser.setUserName("rocket99");
        existingUser.setPassword("P@ssword99");

        res = req.headers("content-type", "application/json").body(existingUser).post("/account/v1/authorized");
        String resBody = res.getBody().asString();
        System.out.println("Response: " + resBody);

        throw new io.cucumber.java.PendingException();
    }
    @Given("A list of books")
    public void a_list_of_books() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I add a book to list")
    public void i_add_a_book_to_list() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The book is added")
    public void the_book_is_added() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
