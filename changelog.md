### Project initiation and analysis

1. **Start the project**:
    - Create a new Git repository and run the application using `mvn spring-boot:run`.
    - Open [http://localhost:8080](http://localhost:8080) to test the UI and get familiar with the functions.

2. **Main analysis**:
    - Examine the main components of the project: entities, repositories, services, and controllers, understanding how they interact and support the functionality of the application.

3. **Resource package analysis**:
    - Check the `resources` directory including `data.sql`, `schema.sql`, `application.yml` and the `static` folder to understand how data, configuration and static resources are managed.

4. **Add recording**:
    - Improved `EventController` and `EventService` by adding logs to make tracking and debugging easier.

-

### Debugging

1. **Edit: Add review**
    - **Problem**: The `updateEvent` endpoint (PUT `/api/events/{id}`) is broken.
    - **Solution**:
        - Use new functions in `EventService` to manage event updates
        - Take advantage of JPA's auto-update mechanism via `EventRepository` (no need to change repository).

2. **Edit: Delete button**
    - **Problem**: Annotation `@Transactional(readOnly = true)` in the storage class prevents write operations from being modified (such as deleting events).
    - **Solution**:
        - Delete comments `@Transactional(readOnly = true)` in the corresponding delete method.
        - Ensure consistent data updates on the front end by dealing with invalid caches.

-

### New features

1. **Search Question**
    - **Description**: Show activity only when there is at least one group member whose name matches the specified pattern.
    - **Implementation**: .
        - Use Java `Stream` to iterate the list, 
        - Use Java `filter`
        - Use Java `AnyMatch` condition to apply changes
        - Save new data to `EventDTO` & `BandDTO`
        - Save result in collection -> List
**PS: !Best practice is to use MemberDTO also, in this case we do not apply any changes in Member so i'll not use memeberDTO**


### CI/CD BONUSES

1. **Github action workflow**
  - **Implementation**: .
      - create new github action workflow to test, build & deploy the application package to github package