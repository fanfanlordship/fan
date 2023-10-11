package org.fan.cloud.auth.service;

import org.fan.cloud.auth.entity.RequestArgs;

import java.util.Map;

public interface LoginMethodService {

    Map<String, String> login(RequestArgs args);

    Map<String, String> refreshToken(String refreshToken);
}
