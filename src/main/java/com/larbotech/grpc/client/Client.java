package com.larbotech.grpc.client;

import com.larbotech.grpc.protos.Person;
import com.larbotech.grpc.protos.PersonRequest;
import com.larbotech.grpc.protos.PersonResponse;
import com.larbotech.grpc.protos.PersonServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

            PersonServiceGrpc.PersonServiceBlockingStub stub 
          = PersonServiceGrpc.newBlockingStub(channel);

          Person p1 = Person.newBuilder().setName("larbo").build();
          Person p2 = Person.newBuilder().setName("toure").build();
          PersonResponse personResponse = stub.hello(PersonRequest.newBuilder()
            .addPeople(p1)
            .addPeople(p2)
            .build());

        System.out.println("Response received from server:\n" + personResponse);

        channel.shutdown();
    }
}