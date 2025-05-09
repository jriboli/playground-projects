# Contributing Guidelines

## Naming Conventions
- **Tests**: `test_<feature>_<scenario>.py`
- **Pages**: `PascalCase` classes inside page modules
- **API Clients**: `PascalCase` classes like `UserAPI`
- **Variables**: Use `snake_case`
- **Functions/Methods**: Use `snake_case`

## Patterns
- Use Page Object Model (POM) for web/mobile UI
- Use Fluent Interface for web interactions and API requests
- Use `pytest` for running and organizing tests

## Commits
Follow conventional commits:
- `feat`: for new features
- `fix`: for bug fixes
- `test`: for test-related changes
- `docs`: for documentation