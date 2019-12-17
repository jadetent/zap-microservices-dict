package com.zap.contadigital.comprovantes.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

public class TemplateBuilder {
    private VelocityEngine velocityEngine;
    private VelocityContext context = new VelocityContext();
    private String template = "templates/comprovante.html";

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setParametro(String parametro, Object valor) {
        context.put(parametro, valor);
    }

    public String getConteudo(String conteudo) throws Exception {
        RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
        StringReader sr = new StringReader(conteudo);
        SimpleNode sn = rs.parse(sr, "");
        Template t = new Template();
        t.setRuntimeServices(rs);
        t.setData(sn);
        t.initDocument();
        StringWriter sw = new StringWriter();
        t.merge(context, sw);
        return sw.toString();
    }

    public String getConteudo() throws Exception {
        velocityEngine = velocityEngine();
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate(template, "UTF-8", context, stringWriter);
        String conteudo = stringWriter.toString();
        return conteudo;
    }

    private VelocityEngine velocityEngine() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        return velocityEngine;
    }
}
