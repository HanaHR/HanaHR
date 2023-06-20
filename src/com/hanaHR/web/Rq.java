package com.hanaHR.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse res;
    public Rq(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset-utf-8");
    }

    public String getParam(String paramName) {
        return req.getParameter(paramName);
    }

    public int getIntParam(String paramName, int defaultValue) {
        String value = req.getParameter(paramName);

        if(value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void appendBody(String str) {
        try{
            res.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
