package xyz.yann.grpc.server.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.stub.StreamObserver;
import xyz.yann.grpc.proto.DemoServiceGrpc;
import xyz.yann.grpc.proto.MyRequest;
import xyz.yann.grpc.proto.MyResponse;

public class DemoImpl extends DemoServiceGrpc.DemoServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoImpl.class);

    private String podName;

    public DemoImpl() {}

    public DemoImpl(String podName) {
        this.podName = podName;
    }

    @Override
    public void sayHello(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        AtomicInteger count = new AtomicInteger(0);
        while(true){
            String msg = "["+podName+"] Hello " + request.getUuid() + " " + count.incrementAndGet();
            LOGGER.info(msg);
            responseObserver.onNext(MyResponse.newBuilder().setResp(msg).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count.get() >= 5){
                responseObserver.onCompleted();
                break;
            }
        }
    }
}
