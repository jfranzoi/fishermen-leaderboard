## TODO

* (DONE) spike: next.js
* (DONE) spike: exam APIs (fisherman, recollections)
* (DONE) spike: async cached data
* (DONE) board: 5 fishermen, at first
* (DONE) board: fishermen name
* (DONE) board: ranking, sorted by amount, descending
* (DONE) board: "load more", to load next 5
* (DONE) board: last month collected amount (kg)
* (WIP) fisherman detail, recollections: date, amount (kg)
* (TODO) fisherman detail: last 10 recollections
* (DONE) pictures (from octet-stream): fishermen picture @ board
* (TODO) pictures (from octet-stream): recollection picture @ fisherman detail
* (DONE) NFR, security: API key not accessible to clients
* (TODO) NFR, usability: desktop and mobile


## Notes

* cannot rely on real-time API calls, as it takes too long (approx 7 sec)
* images are octet-streams, pictures should be scripted (client or server side)
* async cached data: @Scheduled with Spring Boot as backend, in-memory storage

## Questions

* recollections: what's the difference between `kg` and `kg_availables`?
* ranking, by last month recollected amount: last 30 days or in current month of the year?