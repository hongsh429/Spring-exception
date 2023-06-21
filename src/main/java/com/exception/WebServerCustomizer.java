package com.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/*이건 그냥 springboot에서 이렇게 하라고 해서 그냥 이렇게 해야하는거다*/
//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        //오류페이지 만들기
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
                                            /* 이렇게하면 자식까지도 이리로 보낸다.*/
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        //오류 페이지 등록
        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);

    }
}
