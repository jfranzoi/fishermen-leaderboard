# Fishermen Leaderboard

Sample web-application to solve the "fishermen leaderboard" coding problem.

To run it, execute:

```
./build-and-start.sh
```

This would build and start both `api` and `ui` components, as docker containers.

## Requirements

`ui` component is a **NextJS** front-end application. `api` component is a **Java 11** web application, based on **Spring Boot** and built with **maven**.

## Notes

See [TODO](TODO.md) to follow development steps.

As we have two APIs to be consumed and correlated (`fishermen` and `recollections`), this would not be possible in real-time. So, the main goal of `api` component is preparing ready-to-use content, for `ui` component. This is achieved by in-memory pre loading content, and schedule a "refresh" on a given interval. See:
* [`RefreshingStorage`](fishermen-leaderboard-api/src/main/java/my/projects/fishermenleaderboard/api/schedule/RefreshingStorage.java) and its `@Scheduled` annotation
* `application.schedule.refreshing-storage.minutes` in [application.properties](fishermen-leaderboard-api/src/main/resources/application.properties) configuration file.

APIs integration is using an *API-KEY*, which is not exposed to clients. Both API-KEY and base URL can be configured in [application.properties](fishermen-leaderboard-api/src/main/resources/application.properties) as `application.ogyre.api-key` and `application.ogyre.base-uri`.