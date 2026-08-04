# GitHub Copilot SDK with Spring Boot

A minimal REST API using the official GitHub Copilot SDK for Java with Spring Boot 4.1.

## Prerequisites

- JDK 25 (Java 17 or later is supported by the SDK)
- GitHub Copilot CLI 1.0.55-5 or later, installed and authenticated

Verify/update the CLI before starting the application:

```powershell
copilot --version
copilot update
copilot
```

## Run in Powershell

```powershell
.\mvnw.cmd spring-boot:run
```

Send a chat request:

```powershell
Invoke-RestMethod -Method Post `
  -Uri http://localhost:8080/api/copilot/chat `
  -ContentType application/json `
  -Body '{"prompt":"Explain Java records in one sentence."}'
```

The endpoint rejects Copilot permission requests by default, preventing prompts from authorizing tool actions.

## Configuration

```properties
# Optional; when omitted, the Copilot CLI chooses its default model.
github.copilot.model=gpt-5.4

# Optional path when copilot is not available on PATH.
github.copilot.cli-path=C:/path/to/copilot.exe

github.copilot.timeout=60s
```

Run tests with `./mvnw test` (macOS/Linux) or `.\mvnw.cmd test` (Windows).

## License

Licensed under the [MIT License](LICENSE).