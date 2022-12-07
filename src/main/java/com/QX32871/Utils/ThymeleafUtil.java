package com.QX32871.Utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;

//Thymeleaf工具类，用于渲染静态页面给前端，实现前后端分离，单独做出一个工具类提高了代码的复用性,便于维护
public class ThymeleafUtil {
    private static final TemplateEngine engine;

    static {
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        engine.setTemplateResolver(r);
        r.setCharacterEncoding("UTF-8");
    }

    //这个方法用于渲染页面
    public static void process(String template, IContext context, Writer writer) {
        engine.process(template, context, writer);
    }
}
