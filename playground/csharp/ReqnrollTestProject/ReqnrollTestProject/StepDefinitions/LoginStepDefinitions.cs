using System;
using System.Threading.Tasks;
using Microsoft.Playwright;
using Reqnroll;
using ReqnrollTestProject.Pages;

namespace ReqnrollTestProject.StepDefinitions
{
    [Binding]
    public class LoginStepDefinitions
    {
        private readonly HomePage _homepage;
        private readonly IPage _page;

        LoginPage _loginPage;

        public LoginStepDefinitions(HomePage homepage, IPage page)
        {
            _homepage = homepage;
            _page = page;
        }

        [Given("I click the login link")]
        public async Task GivenIClickTheLoginLink()
        {
            _loginPage = await _homepage.GoToLogin();
        }

        [When("I enter username as {string} and password as {string}")]
        public void WhenIEnterUsernameAsAndPasswordAs(string user, string pass)
        {
            _loginPage.LoginAsync(user, pass);
        }

        [When("I click login button")]
        public void WhenIClickLoginButton()
        {
            //_loginPage.;
        }

        [Then("I should be logged in")]
        public void ThenIShouldBeLoggedIn()
        {
            throw new PendingStepException();
        }
    }
}
