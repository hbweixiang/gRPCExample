package com.wly.gRpc_helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

public class gRpcHelloWorldClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forTarget("127.0.0.1:50051")
                .usePlaintext()
                .build();
        try {

            GreeterGrpc.GreeterBlockingStub greeterBlockingStub = GreeterGrpc.newBlockingStub(managedChannel);

            HelloRequest.Builder builder = HelloRequest.newBuilder();

            builder.setName("GRPC");

            HelloRequest helloRequest = builder.build();

            HelloReply helloReply = greeterBlockingStub.sayHello(helloRequest);

            System.out.println(helloReply);

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            managedChannel.shutdown();
        }
    }
}
