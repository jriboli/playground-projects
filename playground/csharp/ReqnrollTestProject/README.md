This is mostly for documenting discovers, thoughts, or ideas being had will building this project

## Design Thoughts

| Concern | Belongs in | 
| ---- | ---- | 
| UI elements locators and actions | `PageObject.cs` |
| Business logic (flows) | `Flow.cs` or `Steps.cs` | 
| Data setup/config | `Fixtures`, `.env`, `TestData.cs` | 

#### Why? 
- Keeps `PageObject.cs` simple (just UI interactions)
- Flow classes express real user behavior (not technical steps)
- Easy to resuse in tests

#### Notes:
- Return the next page from actions
- Use assertion helpers (keep tests clean and focused)