using ReqnrollTestProject.Support.Pages.DuckDuckGo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.StepDefinitions.DuckDuckGo
{
    [Binding]
    public class SearchResultSteps
    {
        private readonly DuckDuckGoSearchResultsPage _searchResultsPage;

        public SearchResultSteps(DuckDuckGoSearchResultsPage searchResultsPage)
        {
            _searchResultsPage = searchResultsPage;
        }

        [Then(@"the search results show '(.*)' as the first result with link '(.*)'")]
        public async Task ThenTheSearchResultsShowAsTheFirstResult(string expectedResult, string expectedLink)
        {
            //Assert the page content
            await _searchResultsPage.AssertPageContent(expectedResult);

            //Assert the first search result (hence the index of 0)
            await _searchResultsPage.AssertSearchResultAtIndex(expectedResult, 0, expectedLink);
        }
    }
}
