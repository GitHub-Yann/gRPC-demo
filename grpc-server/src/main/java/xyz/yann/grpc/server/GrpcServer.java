package xyz.yann.grpc.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import xyz.yann.grpc.server.impl.DemoImpl;

public class GrpcServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcServer.class);
    
    private Server server;

    public GrpcServer(){}
    
    public GrpcServer(String podName){
        try {
            init(podName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void start(String podName) throws IOException {
        this.server=ServerBuilder.forPort(9822)
                .addService(new DemoImpl(podName))
                .build().start();
        LOGGER.info("server started.");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            LOGGER.info("close jvm");
            GrpcServer.this.stop();
        }));
        LOGGER.info("shut down hook added");
    }
    
    private void stop() {
        if(null!=this.server) {
            LOGGER.info("server stop");
            this.server.shutdown();
        }
    }
    private void awaitTermination() throws InterruptedException {
        if(null!=this.server) {
            LOGGER.info("server await termination");
            this.server.awaitTermination();
//            this.server.awaitTermination(3000,TimeUnit.MILLISECONDS);
        }
    }
    
    public void init(String podName) throws IOException, InterruptedException {
        GrpcServer gServer = new GrpcServer();
        gServer.start(podName);
//        gServer.awaitTermination();//如果这行不加，服务会立马结束
    }
}
