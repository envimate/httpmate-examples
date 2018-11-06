package com.envimate.demo.infra;

import com.envimate.demo.usecases.NextEvent;
import com.envimate.httpmate.HttpMate;
import com.envimate.httpmate.request.HttpRequestMethod;
import com.google.gson.Gson;

public class HttpMateIntegration {
    private HttpMateIntegration() {

    }

    public static HttpMate create() {
        final Gson gson = new Gson();
        return HttpMate
                .aSimpleHttpMateInstanceWithSecureDefaults()
                .servingTheUseCase(NextEvent.class)
                .forRequestPath("api/next-event")
                .andRequestMethod(HttpRequestMethod.GET)
                .mappingRequestsToUseCaseParametersUsing(gson::fromJson)
                .serializingResponseObjectsUsing(gson::toJson)
                ;
    }
}
