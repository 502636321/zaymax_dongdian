package com.zaymax.dongdian.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.BaseAccessToken;
import com.zaymax.dongdian.domain.SysProperties;
import com.zaymax.dongdian.repository.BaseAccessTokenRepository;
import com.zaymax.dongdian.repository.SysPropertiesRepository;
import com.zaymax.dongdian.service.WXService;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 微信服务接口
 * Created by huiquan on 2017/3/8.
 */
@Service
public class WXServiceImp implements WXService {
    public static final Logger LOGGER = LoggerFactory.getLogger(WXServiceImp.class);
    public static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    @Autowired
    private SysPropertiesRepository propertiesRepository;

    @Autowired
    private BaseAccessTokenRepository accessTokenRepository;

    @Override
    public BaseAccessToken getAccessToken() {
        Optional<SysProperties> propertiesOptional = propertiesRepository.findTopByDeletedFalseOrderByLastModifiedDateDesc();
        if (propertiesOptional.isPresent()) {
            try {
                List<NameValuePair> valuePairs = Lists.newArrayList(
                        new BasicNameValuePair("grant_type", "client_credential"),
                        new BasicNameValuePair("appid", propertiesOptional.get().getWxAppId()),
                        new BasicNameValuePair("secret", propertiesOptional.get().getWxAppSecret())
                );
                HttpGet httpGet = new HttpGet(URL_ACCESS_TOKEN + "?" + EntityUtils.toString(new UrlEncodedFormEntity(valuePairs, Consts.UTF_8)));
                return HttpClients.createDefault().execute(httpGet, new AbstractResponseHandler<BaseAccessToken>() {
                    @Override
                    public BaseAccessToken handleEntity(HttpEntity httpEntity) throws IOException {
                        BaseAccessToken accessToken = (new ObjectMapper()).readValue(EntityUtils.toString(httpEntity), BaseAccessToken.class);
                        if (accessToken != null) {
                            return accessTokenRepository.save(accessToken);
                        }
                        return null;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
