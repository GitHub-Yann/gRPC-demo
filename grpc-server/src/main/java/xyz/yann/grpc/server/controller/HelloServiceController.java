package xyz.yann.grpc.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.yann.grpc.common.BaseResponse;

@RestController
public class HelloServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceController.class);

//	@Autowired
//	private DiscoveryClient client;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

//	@Autowired
//	private Registration registration;

	@Value("${deployed.pod.name:default}") 
	private String podName;
	
	@RequestMapping(value = "/testhealthcheck", method = RequestMethod.GET)
	public BaseResponse health() {
		return BaseResponse.success(null);
	}
	
	@RequestMapping(value = "/api/demo/test/get", method = RequestMethod.GET)
	public BaseResponse testGet() {
		return BaseResponse.success("["+podName+"]Hello world");
	}
	
}
