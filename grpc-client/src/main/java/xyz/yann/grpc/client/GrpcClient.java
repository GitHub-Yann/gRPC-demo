package xyz.yann.grpc.client;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import xyz.yann.grpc.proto.DemoServiceGrpc;
import xyz.yann.grpc.proto.MyRequest;
import xyz.yann.grpc.proto.MyResponse;

public class GrpcClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcClient.class);

    public GrpcClient(){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("grpc-demo-server-svc.gateway.svc.cluster.local", 9822)
                                        .usePlaintext().defaultLoadBalancingPolicy("round_robin").build();
            
        new Thread(()->{
            pullServiceInstance(managedChannel);
        },"GrpcClient").start();
    }

     private void pullServiceInstance(ManagedChannel managedChannel) {
        MyRequest req;
        Iterator<MyResponse> respIter;
        MyResponse resp;
        while(true) {
            try {
                req = MyRequest.newBuilder().setUuid("xxxxxxxxxxxxx").build();
                respIter = DemoServiceGrpc.newBlockingStub(managedChannel).sayHello(req);
                while(respIter.hasNext()) {
					resp = respIter.next();
                    LOGGER.info("resp:{}",resp.getResp());
                }
                Thread.sleep(2000);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
