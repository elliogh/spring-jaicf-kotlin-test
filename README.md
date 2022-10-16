# JAICF Spring Kotlin knowledge test Bot

- [Spring](https://spring.io)
- [Docker](https://docker.com)

# Development

To make it work just create `local.application.yml` in `resources` folder with required options:

```yaml
bot:
  accessToken: <your production JAICP token>
```

# Docker

## Build and run

1. Build project with `stage` gradle task
2. Run `docker build -t app .`
3. Run `docker run -p 8080:8080 -t app`

#How to use
1. Go to https://app.jaicp.com
2. Create Jaicf project
3. Channels -> add chanel -> ChatWidget
4. Settings -> copy API-token
5. Paste to `local.application.yml`
6. Run application
