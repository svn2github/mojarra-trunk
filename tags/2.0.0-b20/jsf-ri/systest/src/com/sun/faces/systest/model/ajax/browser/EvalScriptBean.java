package com.sun.faces.systest.model.ajax.browser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.FacesException;

@ManagedBean
@RequestScoped
@SuppressWarnings("unused")
public class EvalScriptBean {

    public String doEval() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startEval();
                writer.write("var marker = true; checkPass();");
                writer.endEval();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }


    public String doInline() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startUpdate("updated");
                writer.startElement("div", null);
                writer.writeAttribute("id","updated","id");
                writer.startElement("script",null);
                writer.writeAttribute("type","text/javascript","type");
                writer.write("var marker = true; checkPass();");
                writer.endElement("script");
                writer.endElement("div");
                writer.endUpdate();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    public String doUpdateRootAllEval() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startUpdate("javax.faces.ViewRoot");
                writer.startElement("html",null);
                writer.startElement("head",null);
                writer.startElement("title",null);
                writer.writeText("PASSED",null);
                writer.endElement("title");
                writer.endElement("head");
                writer.startElement("body",null);
                writer.startElement("span",null);
                writer.writeAttribute("id","target","id");
                writer.endElement("span");
                writer.startElement("script",null);
                writer.writeAttribute("type","text/javascript","type");
                writer.write("var marker = true; checkPass();");
                writer.endElement("script");
                writer.endElement("body");
                writer.endElement("html");
                writer.endUpdate();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

        public String doUpdateRootSimpleEval() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startUpdate("javax.faces.ViewRoot");
                writer.startElement("span",null);
                writer.writeAttribute("id","target","id");
                writer.endElement("span");
                writer.startElement("script",null);
                writer.writeAttribute("type","text/javascript","type");
                writer.write("var marker = true; checkPass();");
                writer.endElement("script");
                writer.endUpdate();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    public String doUpdateBodyTagEval() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startUpdate("javax.faces.ViewBody");
                writer.startElement("body",null);
                writer.startElement("span",null);
                writer.writeAttribute("id","target","id");
                writer.endElement("span");
                writer.startElement("script",null);
                writer.writeAttribute("type","text/javascript","type");
                writer.write("var marker = true; checkPass();");
                writer.endElement("script");
                writer.endElement("body");
                writer.endUpdate();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    public String insertBeforeEval() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        if (ctx.getPartialViewContext().isAjaxRequest()) {
            try {
                extContext.setResponseContentType("text/xml");
                extContext.addResponseHeader("Cache-Control", "no-cache");
                PartialResponseWriter writer =
                      ctx.getPartialViewContext().getPartialResponseWriter();
                writer.startDocument();
                writer.startInsertBefore("target");
                writer.writeAttribute("id", "target2", "id");
                writer.startElement("script",null);
                writer.writeAttribute("type","text/javascript","type");
                writer.write("var marker = true; checkPass();");
                writer.endElement("script");
                writer.endInsert();
                writer.endDocument();
                writer.flush();
                ctx.responseComplete();
            } catch (Exception e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

}
