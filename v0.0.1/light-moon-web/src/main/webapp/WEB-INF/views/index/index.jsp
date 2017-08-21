<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/index.css" rel="stylesheet" type="text/css" />
    <script src="${scripts}/index/index.js?version=${version}"></script>
</head>

<body>
    <jsp:include page="../commons/head.jsp"></jsp:include>
    <jsp:include page="search.jsp"></jsp:include>
    <div class="search-result mt-15">
        <div class="search-order-type">
            <ul>
                <li class="selected-menu"><div class="search-type">按热度</div></li>
                <li><div class="search-type">投标数</div></li>
                <li><div class="search-type">创建时间</div></li>
            </ul>
        </div>
        <div class="lihh-clear"></div>
        <div class="search-result-items">
        </div>
    </div>

    <div id="result-tr" class="hide">
        <div class="search-result-tr">
            <div class="search-result-item">
                <div class="result-ico">
                    <div class="result-ico-content">
                            <span class="result-ico-img">
                                <img src="${mimeBase}/images/test.png">
                            </span>
                        <div class="lihh-clear"></div>
                        <span class="result-type">
                                %type%
                        </span>
                    </div>
                </div>
                <div class="result-item-content">
                    <table>
                        <tr class="result-item-head">
                            <td width="23%">
                                起投金额
                            </td>
                            <td width="23%">
                                投资周期
                            </td>
                            <td width="23%">
                                预计年华
                            </td>
                            <td rowspan="2" width="31%">
                                已有%num%人参与
                            </td>
                        </tr>
                        <tr class="result-item-body">
                            <td>
                                %minCount%
                            </td>
                            <td>
                                %date%
                            </td>
                            <td class="lhh-c-ori">
                                %a%%
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <input class="result-item-btn" type="button" value="我要投资" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
