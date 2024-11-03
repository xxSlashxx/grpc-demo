package de.slash.productsservice.system;

import io.grpc.stub.StreamObserver;

import java.util.function.Function;

public class StreamObserverUtility {

    private StreamObserverUtility() {
    }

    public static <Target, Source> StreamObserver<Source> proxyStream(
            StreamObserver<Target> target,
            Function<Source, Target> handler
    ) {
        return new StreamObserver<Source>() {
            @Override
            public void onNext(Source value) {
                final Target targetValue = handler.apply(value);
                target.onNext(targetValue);
            }

            @Override
            public void onError(Throwable t) {
                target.onError(t);
            }

            @Override
            public void onCompleted() {
                target.onCompleted();
            }
        };
    }
}
