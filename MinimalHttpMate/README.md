# HttpMate Minimal Example

This tutorial creates a web service with a single end-point. We write a use-case that provides the date and time of a scheduled corporate event. This minimal example demonstrates the code structure allowed by HttpMate.

## Test run

Load the `MinimalHttpMate` project in your IDE, IntelliJ or Eclipse. Run the `src/main/java/com/envimate/demo/Server.java` entry point, which starts a local HTTP server on port 1234. Make a request with `curl`.

```sh
curl http://localhost:1234/api/next-event
```

You'll get a JSON formatted response like below.

```json
{
    "currentTime":"2018-11-06T09:31:07.24512",
    "timeZone":"Europe/Berlin"
}
```

Let's look at the code.

## Use-Case 

envimate promotes an architecture designed around use-cases, free of protocol code. In this example, we define a `NextEvent` use-case, located in the file `usecases/NextEvent.java`.

```java
public final class NextEvent {
    public NextEventResponse process() {
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        final String date = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);

        return new NextEventResponse(date, now.getZone().toString());
    }
}
```

`NextEvent` works in the business object domain. The `process` function does not deal with protocol specifics. HttpMate handles the integration with HTTP.  It could also be invoked directly by another use-case or a unit test. Staying purely in the business domain keeps this code flexible.

In this minimal example, we format the system time as the response. A real-world application would probably make a database request.

The return of the function is a `NextEventResponse` object.

```java
public class NextEventResponse {
    public final String currentTime;
    public final String timeZone;

    public NextEventResponse(String currentTime, String timeZone) {
        this.currentTime = currentTime;
        this.timeZone = timeZone;
    }
}
```

The public fields are the data returned to the caller.

We're still in the business object domain in this class. We can write test cases for the use-case without worrying about the HTTP aspect.


## HttpMate Integration

We use `HttpMate.servingTheUseCase` to add an HTTP endpoint for our use case.

```java
public static HttpMate create() {
    final Gson gson = new Gson();
    return HttpMate
            .aSimpleHttpMateInstanceWithSecureDefaults()
            .servingTheUseCase(NextEvent.class)
            .forRequestPath("api/next-event")
            .andRequestMethod(HttpRequestMethod.GET)
            .mappingRequestsToUseCaseParametersUsing(gson::fromJson)
            .serializingResponseObjectsUsing(gson::toJson);
}
```

We have only a single endpoint. We can add more by calling `servingTheUseCase` multiple times, each with a different use-case.

The additional lines define the HTTP action and how to serialize data. The library is designed to allow assisted completion of this setup in an IDE. You don't need to remember these names.

We're using a simple JSON serializer. It works, but only for something quick and dirty. In the next tutorial, we'll add arguments to our use-case, and show the limitations with the basic JSON serializer.


## Code dependencies

When you wish to add HttpMate to your project you'll need to add the `com.envimate.httpmate` dependency, typically as a Maven dependency. To use the GSON serializer, as in this example, also add `com.google.code.gson`. Look at the example `pom.xml` file.
