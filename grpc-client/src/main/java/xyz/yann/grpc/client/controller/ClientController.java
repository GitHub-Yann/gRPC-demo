package xyz.yann.grpc.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.yann.grpc.common.BaseResponse;

@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(value = "/api/demo/test/get", method = RequestMethod.GET)
	public BaseResponse testGet() {
		return BaseResponse.success("Hello world");
	}
}
