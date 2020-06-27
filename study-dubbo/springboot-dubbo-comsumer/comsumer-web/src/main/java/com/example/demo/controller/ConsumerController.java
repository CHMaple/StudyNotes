/*
 * *****************************************************
 * *****************************************************
 * Copyright (C), 2018-2020, panda-fa.com
 * FileName: com.example.demo.controller.ConsumerController
 * Author:   chenhao
 * Date:     2020/6/27 19:41
 * *****************************************************
 * *****************************************************
 */
package com.example.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maple.dubbo.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description>
 *
 * @author: chenhao
 * @date: 2020/6/27 19:41
 */
@RestController
public class ConsumerController {

	@Reference
	ProviderService providerService;

	@GetMapping("to-upper")
	public String sayHello(@RequestParam(name = "name") String name) {
		return providerService.toUpper(name);
	}

}