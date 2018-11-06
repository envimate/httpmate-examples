package com.envimate.demo;

import com.envimate.demo.infra.HttpMateIntegration;
import com.envimate.httpmate.convenience.endpoints.PureJavaEndpoint;

public final class Server {
    private Server() {
    }

    public static void main(final String[] args) {
        PureJavaEndpoint.pureJavaEndpointFor(HttpMateIntegration.create())
            .listeningOnThePort(1234);
        System.out.println("Running on 1234");
    }
}
