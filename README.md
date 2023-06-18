# Fishermen Leaderboard

Sample web-application to solve the "fishermen leaderboard" coding problem.

To run it, execute:

```
docker-compose up
```

Then, open your browser and browse to [http://localhost/](http://localhost/).

## Requirements

`fishermen-leaderboard` is composed of 3 components, built and started as **docker** containers:

* `ui`, which is a **NextJS** front-end application, binding on port `3000`
* `api`, which is a **Java 11** web application, based on **Spring Boot** and built with **maven**, binding on port `8080`
* `proxy`, which is an **Nginx** instance, acting as a reverse proxy to other components (`/api `for `api`, fallback to `ui` on all other locations), binding on port `80`

Links between containers are through host ports in order to be fully supported on Docker Desktop (eg: on MacOS).

## Notes

See [TODO](TODO.md) to follow development steps.

As we have two APIs to be consumed and correlated (`fishermen` and `recollections`), this would not be possible in real-time. So, the main goal of `api` component is preparing ready-to-use content, for `ui` component. This is achieved by in-memory pre loading content, and a scheduled "refresh" on a given interval (1h by default). See:

* [`RefreshingStorage`](fishermen-leaderboard-api/src/main/java/my/projects/fishermenleaderboard/api/schedule/RefreshingStorage.java) and its `@Scheduled` annotation
* `application.schedule.refreshing-storage.minutes` in [application.properties](fishermen-leaderboard-api/src/main/resources/application.properties) configuration file.

APIs integration is using an *API-KEY*, which is not exposed to clients. Both API-KEY and base URL can be configured in [application.properties](fishermen-leaderboard-api/src/main/resources/application.properties) as `application.ogyre.api-key` and `application.ogyre.base-uri`.

`ui` component is basically a NextJS starter application, reusing its default template. Decoupling from data fetching and presentation is achieved by leveraging on [useFishermen.tsx](fishermen-leaderboard-ui/src/hooks/useFishermen.tsx) custom hook. Please note: no configuration is available at the moment for its base URL, as it's hard-coded in the hook code (and being set to work on its docker-based setup).

Two reusable `ui` components are availbale in [components](fishermen-leaderboard-ui/src/components/) folder, to present `FishermanCard` on homepage, and `RecollectionDetails` on detail page.

## Deployment

In order to test the end-to-end build and deployment setup, few additional resources have been prepared:

* [build.sh](build.sh), building and publishing docker images to [Github Packages](https://github.com/users/jfranzoi/packages?repo_name=fishermen-leaderboard)
* [deploy.sh](deploy.sh), deploying to an `AWS` `ECS` cluster, with [yoke](https://github.com/GuccioGucci/yoke). Please, see [`deployment`](deployment) folder for related resources

Feel free to provide any feedback!