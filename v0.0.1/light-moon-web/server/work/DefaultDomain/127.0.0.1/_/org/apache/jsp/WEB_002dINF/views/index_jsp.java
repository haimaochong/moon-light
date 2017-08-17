package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/views/commons/resources.jsp");
    _jspx_dependants.add("/WEB-INF/views/commons/taglibs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    ");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fset_005f3(_jspx_page_context))
        return;

    String path = request.getContextPath();

      out.write("<script language=\"javascript\">\r\n");
      out.write("    var BASE_PATH = \"");
      out.print(path);
      out.write("\";\r\n");
      out.write("</script>");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mimeBase}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/styles/lib.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vendorsBase}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/require.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("    require.config({\r\n");
      out.write("        // RequireJS 通过一个相对的路径 baseUrl来加载所有代码。baseUrl通常被设置成data-main属性指定脚本的同级目录。\r\n");
      out.write("        baseUrl: BASE_PATH + \"/assets/vendors\",\r\n");
      out.write("\r\n");
      out.write("        paths: {\r\n");
      out.write("            \"jquery\": \"jquery.min\"\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mimeBase}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/styles/index.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${scripts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/index/index.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "commons/head.jsp", out, false);
      out.write("<div class=\"lhh-search\">\r\n");
      out.write("        <div class=\"search-title\">\r\n");
      out.write("            <div class=\"f-left\">全部分类</div>\r\n");
      out.write("            <div class=\"f-right\">\r\n");
      out.write("                <input type=\"button\" value=\"收起筛选\" class=\"search-up-btn\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"lihh-clear\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"search-content\">\r\n");
      out.write("            <div class=\"search-item\">\r\n");
      out.write("                <div class=\"search-name-div f-left\">平台名称 : </div>\r\n");
      out.write("                <div class=\"search-all-div f-left\"><div class=\"search-menu search-selected-menu\">不限</div></div>\r\n");
      out.write("                <div class=\"search-content-div f-left\">\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><div class=\"search-menu\">恒利网</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">学信贷</div></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"search-content-more\">\r\n");
      out.write("                <div class=\"f-left\">[更多]</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"search-item\">\r\n");
      out.write("                <div class=\"search-name-div f-left\">平台背景 : </div>\r\n");
      out.write("                <div class=\"search-all-div f-left\"><div class=\"search-menu search-selected-menu\">不限</div></div>\r\n");
      out.write("                <div class=\"search-content-div f-left\">\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><div class=\"search-menu\">国企背景</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">民营系</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">有风投</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">线下业务</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">1年以上</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">上市背景</div></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"search-item\">\r\n");
      out.write("                <div class=\"search-name-div f-left\">投标次数 : </div>\r\n");
      out.write("                <div class=\"search-all-div f-left\"><div class=\"search-menu search-selected-menu\">不限</div></div>\r\n");
      out.write("                <div class=\"search-content-div f-left\">\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li><div class=\"search-menu\">仅限首投</div></li>\r\n");
      out.write("                        <li><div class=\"search-menu\">可复投</div></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"lihh-clear\"></div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"search-result mt-15\">\r\n");
      out.write("        <div class=\"search-order-type\">\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li class=\"selected-menu\"><div class=\"search-type\">按热度</div></li>\r\n");
      out.write("                <li><div class=\"search-type\">投标数</div></li>\r\n");
      out.write("                <li><div class=\"search-type\">创建时间</div></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"lihh-clear\"></div>\r\n");
      out.write("        <div class=\"search-result-items\">\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"result-tr\" class=\"hide\">\r\n");
      out.write("        <div class=\"search-result-tr\">\r\n");
      out.write("            <div class=\"search-result-item\">\r\n");
      out.write("                <div class=\"result-ico\">\r\n");
      out.write("                    <div class=\"result-ico-content\">\r\n");
      out.write("                            <span class=\"result-ico-img\">\r\n");
      out.write("                                <img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mimeBase}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/images/test.png\">\r\n");
      out.write("                            </span>\r\n");
      out.write("                        <div class=\"lihh-clear\"></div>\r\n");
      out.write("                        <span class=\"result-type\">\r\n");
      out.write("                                %type%\r\n");
      out.write("                        </span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"result-item-content\">\r\n");
      out.write("                    <table>\r\n");
      out.write("                        <tr class=\"result-item-head\">\r\n");
      out.write("                            <td width=\"23%\">\r\n");
      out.write("                                起投金额\r\n");
      out.write("                            </td>\r\n");
      out.write("                            <td width=\"23%\">\r\n");
      out.write("                                投资周期\r\n");
      out.write("                            </td>\r\n");
      out.write("                            <td width=\"23%\">\r\n");
      out.write("                                预计年华\r\n");
      out.write("                            </td>\r\n");
      out.write("                            <td rowspan=\"2\" width=\"31%\">\r\n");
      out.write("                                已有%num%人参与\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr class=\"result-item-body\">\r\n");
      out.write("                            <td>\r\n");
      out.write("                                %minCount%\r\n");
      out.write("                            </td>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                %date%\r\n");
      out.write("                            </td>\r\n");
      out.write("                            <td class=\"lhh-c-ori\">\r\n");
      out.write("                                %a%%\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                    </table>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div>\r\n");
      out.write("                    <input class=\"result-item-btn\" type=\"button\" value=\"我要投资\" />\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/views/commons/taglibs.jsp(5,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("version");
    // /WEB-INF/views/commons/taglibs.jsp(5,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/commons/taglibs.jsp(5,0) 'v0.0.1'",_el_expressionfactory.createValueExpression("v0.0.1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent(null);
    // /WEB-INF/views/commons/taglibs.jsp(7,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("mimeBase");
    // /WEB-INF/views/commons/taglibs.jsp(7,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/commons/taglibs.jsp(7,0) '/assets'",_el_expressionfactory.createValueExpression("/assets",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /WEB-INF/views/commons/taglibs.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("scripts");
    // /WEB-INF/views/commons/taglibs.jsp(8,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/commons/taglibs.jsp(8,0) '/assets/scripts'",_el_expressionfactory.createValueExpression("/assets/scripts",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f3.setParent(null);
    // /WEB-INF/views/commons/taglibs.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setVar("vendorsBase");
    // /WEB-INF/views/commons/taglibs.jsp(9,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/commons/taglibs.jsp(9,0) '/assets/vendors'",_el_expressionfactory.createValueExpression("/assets/vendors",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f3 = _jspx_th_c_005fset_005f3.doStartTag();
    if (_jspx_th_c_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
    return false;
  }
}
