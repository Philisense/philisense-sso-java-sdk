package net.renfei.philisense.sso.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.philisense.sso.sdk.model.Oauth2Token;
import net.renfei.philisense.sso.sdk.model.Oauth2UserInfo;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 单点登陆
 *
 * @author renfei
 */
public final class SingleSignOn {
    private static final Logger logger = LoggerFactory.getLogger(SingleSignOn.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     * 登陆端点
     */
    public static final String OAUTH2_LOGIN_ENDPOINT = "http://oauth-api.philisensedev.com/site/login?redirect_url=/oauth2/authorize";
    /**
     * Token获取端点
     */
    public static final String OAUTH2_TOKEN_ENDPOINT = "http://oauth-api.philisensedev.com/oauth2/token";
    /**
     * 获取用户信息端点
     */
    public static final String OAUTH2_USERINFO_ENDPOINT = "http://oauth-api.philisensedev.com/oauth/userinfo";
    /**
     * CODE授权类型
     */
    public static final String GRANT_TYPE_CODE = "authorization_code";
    /**
     * 刷新TOKEN类型
     */
    public static final String GRANT_TYPE_REFRESH = "refresh_token";
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 客户端秘钥
     */
    private String clientSecret;
    /**
     * 重定向URL
     */
    private String redirectUri;
    /**
     * 状态值
     */
    private String state;

    private final RequestConfig requestConfig;

    /**
     * 禁止直接实例化
     */
    private SingleSignOn() {
        this.requestConfig = RequestConfig.DEFAULT;
    }

    /**
     * 实例化单点登陆对象
     *
     * @param clientId     客户端ID
     * @param clientSecret 客户端秘钥
     * @param redirectUri  重定向地址
     * @param state        状态值
     */
    public SingleSignOn(String clientId, String clientSecret, String redirectUri, String state) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.state = state;
        requestConfig = RequestConfig.DEFAULT;
    }

    public String getLogInUrl() {
        return OAUTH2_LOGIN_ENDPOINT
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&state=" + state;
    }

    /**
     * 使用Code换取Token
     *
     * @param code
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws SingleSignOnException
     */
    public Oauth2Token getToken(String code) throws IOException, ParseException, SingleSignOnException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(OAUTH2_TOKEN_ENDPOINT);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", GRANT_TYPE_CODE));
            parameters.add(new BasicNameValuePair("code", code));
            parameters.add(new BasicNameValuePair("client_id", clientId));
            parameters.add(new BasicNameValuePair("client_secret", clientSecret));
            parameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getCode() == 200) {
                logger.info(responseString);
                return string2Obj(responseString, Oauth2Token.class);
            } else {
                logger.error(responseString);
                throw new SingleSignOnException(responseString);
            }
        }
    }

    public Oauth2UserInfo getUserInfo(String accessToken) throws IOException, ParseException, SingleSignOnException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(OAUTH2_USERINFO_ENDPOINT + "?access_token=" + accessToken);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getCode() == 200) {
                logger.info(responseString);
                return string2Obj(responseString, Oauth2UserInfo.class);
            } else {
                logger.error(responseString);
                throw new SingleSignOnException(responseString);
            }
        }
    }

    /**
     * 字符串转换为自定义对象
     *
     * @param str   要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    private static <T> T string2Obj(String str, Class<T> clazz) {
        if (str == null || str.length() < 1 || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            logger.warn("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }
}
