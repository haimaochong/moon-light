<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="commons/resources.jsp"%>
    <link href="${mimeBase}/styles/index.css" rel="stylesheet" type="text/css" />
    <script src="${scripts}/index/index.js?version=${version}"></script>
</head>

<body>
    <jsp:include page="commons/head.jsp"></jsp:include>
    <div class="lhh-search">
        <div class="search-title">
            <div class="f-left">全部分类</div>
            <div class="f-right">
                <input type="button" value="收起筛选" class="search-up-btn">
            </div>
            <div class="lihh-clear"></div>
        </div>
        <div class="search-content">
            <div class="search-item">
                <div class="search-name-div f-left">平台名称 : </div>
                <div class="search-all-div f-left"><div class="search-menu search-selected-menu">不限</div></div>
                <div class="search-content-div f-left">
                    <ul>
                        <li><div class="search-menu">恒利网</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                    </ul>
                    <ul>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                        <li><div class="search-menu">学信贷</div></li>
                    </ul>
                </div>
            </div>
            <div class="search-content-more">
                <div class="f-left">[更多]</div>
            </div>
            <div class="search-item">
                <div class="search-name-div f-left">平台背景 : </div>
                <div class="search-all-div f-left"><div class="search-menu search-selected-menu">不限</div></div>
                <div class="search-content-div f-left">
                    <ul>
                        <li><div class="search-menu">国企背景</div></li>
                        <li><div class="search-menu">民营系</div></li>
                        <li><div class="search-menu">有风投</div></li>
                        <li><div class="search-menu">线下业务</div></li>
                        <li><div class="search-menu">1年以上</div></li>
                        <li><div class="search-menu">上市背景</div></li>
                    </ul>
                </div>
            </div>
            <div class="search-item">
                <div class="search-name-div f-left">投标次数 : </div>
                <div class="search-all-div f-left"><div class="search-menu search-selected-menu">不限</div></div>
                <div class="search-content-div f-left">
                    <ul>
                        <li><div class="search-menu">仅限首投</div></li>
                        <li><div class="search-menu">可复投</div></li>
                    </ul>
                </div>
            </div>
            <div class="lihh-clear"></div>
        </div>
    </div>

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
