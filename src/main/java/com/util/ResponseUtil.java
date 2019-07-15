package com.util;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServletResponse帮助类
 */
@Log4j2
public final class ResponseUtil {
    /**
     * 发送文本。使用UTF-8编码。
     * @param response HttpServletResponse
     * @param text 发送的字符串
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * 发送json。使用UTF-8编码。
     * @param response HttpServletResponse
     * @param text 发送的字符串
     */
    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", text);
    }

    /**
     * 发送xml。使用UTF-8编码。
     * @param response HttpServletResponse
     * @param text 发送的字符串
     */
    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        PrintWriter pWriter = null;

        try {
            pWriter = response.getWriter();
            pWriter.write(text);
            pWriter.flush();
            pWriter.close();
        }
        catch(IOException e) {
            // log.error(e.getMessage(), e);
        }
        finally {
            if(pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }
    }
}
