<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="taglibs.jsp"%>

<link href="${mimeBase}/styles/lib.css" rel="stylesheet" type="text/css" />

<script src="${vendorsBase}/require.js?version=${version}"></script>
<script language="javascript">
    require.config({
        // RequireJS 通过一个相对的路径 baseUrl来加载所有代码。baseUrl通常被设置成data-main属性指定脚本的同级目录。
        baseUrl: BASE_PATH + "/assets/vendors",

        paths: {
            "jquery": "jquery.min"
        }
    });

</script>