/*
 * *****************************************************
 * *****************************************************
 * Copyright (C), 2018-2020, panda-fa.com
 * FileName: com.maple.dubbo.service.ProviderServiceImpl
 * Author:   chenhao
 * Date:     2020/6/27 19:27
 * *****************************************************
 * *****************************************************
 */
package com.maple.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.maple.dubbo.ProviderService;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author: chenhao
 * @date: 2020/6/27 19:27
 */
@Component
@Service(interfaceClass = ProviderService.class)
public class ProviderServiceImpl implements ProviderService {

	@Override
	public String toUpper(String name) {
		System.out.println("来了来了");
		return null != name ? name.toUpperCase() : null;
	}
}