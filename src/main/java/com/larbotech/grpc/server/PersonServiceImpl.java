package com.larbotech.grpc.server;

import com.larbotech.grpc.protos.PersonServiceGrpc.PersonServiceImplBase;
import com.larbotech.grpc.protos.PersonResponse;
import com.larbotech.grpc.protos.Person;
/**
 * PersonServiceImpl
 */
public class PersonServiceImpl extends PersonServiceImplBase{

    @Override
    public void hello(com.larbotech.grpc.protos.PersonRequest request,
        io.grpc.stub.StreamObserver<com.larbotech.grpc.protos.PersonResponse> responseObserver) {

        System.out.println("Request received from client:\n" + request);

        String names = request.getPeopleList().stream()
                .map(Person::getName)    // map
                .reduce("", (a, b) -> a + "|" + b);   

        String greeting = new StringBuilder().append("Hello, ")
            .append(String.valueOf(request.getPeopleCount()))
            .append(" ")
            .append(names)
            .toString();

            PersonResponse response = PersonResponse.newBuilder()
            .setGreeting(greeting)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}