using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReqnrollTestProject.Support
{
    [Binding]
    public sealed class Hooks
    {
        private readonly ScenarioContext _scenarioContext;
        private readonly BrowserContext _context;

        public Hooks(ScenarioContext scenarioContext, BrowserContext context)
        {
            _scenarioContext = scenarioContext;
            _context = context;
        }

        [BeforeScenario]
        public async Task BeforeScenario()
        {
            await _context.LaunchBrowserAsync();
        }

        [AfterScenario]
        public async Task AfterScenario()
        {
            await _context.DisposeAsync();
        }
    }
}
