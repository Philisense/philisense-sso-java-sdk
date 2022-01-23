package net.renfei.philisense.sso.sdk;

import net.renfei.philisense.sso.sdk.model.Oauth2Token;
import net.renfei.philisense.sso.sdk.model.Oauth2UserInfo;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

/**
 * @author renfei
 */
public class SingleSignOnTests {
    public static void main(String[] args) throws IOException, ParseException, SingleSignOnException {
        SingleSignOn singleSignOn = new SingleSignOn(
                "clientId",
                "clientSecret",
                "http://demo.philisensedev.com/callback",
                "state");
        System.out.println("访问 LoginUrl，去登陆：" + singleSignOn.getLogInUrl());
        System.out.println("");
        /*************************************************
         * 访问 LoginUrl，去登陆，然后拿到 Code，继续下面的测试
         *************************************************/
        Oauth2Token oauth2Token = singleSignOn.getToken("b3226e65ce38ac61af0c1ac7fb2d1257d1031910");
        System.out.println("");
        System.out.println("拿到Token：" + oauth2Token);
        Oauth2UserInfo oauth2UserInfo = singleSignOn.getUserInfo(oauth2Token.getAccess_token());
        System.out.println("");
        System.out.println("拿到UserInfo：" + oauth2UserInfo);
    }
}
