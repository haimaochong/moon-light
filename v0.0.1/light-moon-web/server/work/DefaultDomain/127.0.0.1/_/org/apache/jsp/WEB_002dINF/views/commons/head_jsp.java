package org.apache.jsp.WEB_002dINF.views.commons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class head_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write("<div class=\"lhh-top mb-20\">\r\n");
      out.write("    <div class=\"top-tip\">\r\n");
      out.write("        <div class=\"top-tip-content\">\r\n");
      out.write("                <span class=\"top-tip-left\">\r\n");
      out.write("                    <span class=\"top-tip-operator\">帮助中心</span> - <span class=\"top-tip-operator\">新手指南</span> <span>客服电话：18059833445</span>\r\n");
      out.write("                </span>\r\n");
      out.write("            <span class=\"top-tip-right\">\r\n");
      out.write("                    <span>欢迎访问测试系统，请选择</span> <span class=\"top-tip-operator\">免费注册</span> - <span class=\"top-tip-operator\">登录</span>\r\n");
      out.write("                </span>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"top-title\">\r\n");
      out.write("        <div class=\"top-title-content\">\r\n");
      out.write("            <div class=\"top-title-logo\"></div>\r\n");
      out.write("            <div class=\"top-title-menus\">\r\n");
      out.write("                <div class=\"top-title-menus-home selected-menu\">首页</div>\r\n");
      out.write("                <div class=\"top-title-menus-steady\">稳健专区</div>\r\n");
      out.write("                <div class=\"top-title-menus-hight\">高返专区</div>\r\n");
      out.write("                <div class=\"top-title-menus-second\">复投专区</div>\r\n");
      out.write("                <div class=\"top-title-menus-guide\">新手指引</div>\r\n");
      out.write("                <div class=\"top-title-menus-relation\">联系我们</div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"top-title-person-center\">\r\n");
      out.write("                <input class=\"top-title-person-center-btn\" type=\"button\" value=\"个人中心\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>");
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
}
