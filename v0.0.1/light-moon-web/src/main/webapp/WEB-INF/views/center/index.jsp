<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/center.css" rel="stylesheet" type="text/css" />
    <script src="${scripts}/center/center.js?version=${version}"></script>
</head>

<body>
    <jsp:include page="../commons/head.jsp"></jsp:include>
    <table class="center-table">
        <tr>
            <td class="center-left person-ico">
                <img class="person-img" src="${mimeBase}/images/test.png" />
                <div>
                    <span class="person-label">昵称:</span>
                    <span class="person-item">海毛虫</span>
                </div>
                <div>
                    <span class="person-label">支付方式：</span>
                    <span class="person-item">支付宝</span>
                </div>
            </td>
            <td class="center-split"></td>
            <td class="center-right" rowspan="3">
                <div class="base-form-div">
                    <div class="base-form-title">基本信息</div>
                    <table class="base-form-table">
                        <tr>
                            <td class="base-form-label">昵称：</td>
                            <td class="base-form-item"><input type="text" /></td>
                            <td class="base-form-label">姓名：</td>
                            <td class="base-form-item"><input type="text" /></td>
                        </tr>
                        <tr>
                            <td class="base-form-label">性别：</td>
                            <td class="base-form-item"><input type="text" /></td>
                            <td class="base-form-label">生日：</td>
                            <td class="base-form-item"><input type="text" /></td>
                        </tr>
                        <tr>
                            <td class="base-form-label">手机号码：</td>
                            <td class="base-form-item"><input type="text" /></td>
                            <td class="base-form-label">邮箱：</td>
                            <td class="base-form-item"><input type="text" /></td>
                        </tr>
                        <tr>
                            <td class="base-form-label">QQ：</td>
                            <td class="base-form-item"><input type="text" /></td>
                        </tr>
                    </table>
                    <div class="base-form-title">收款信息</div>
                    <table class="base-form-table">
                        <tr>
                            <td class="base-form-label">收款方式：</td>
                            <td class="base-form-item">
                                <select>
                                    <option value="1">支付宝</option>
                                    <option value="2">微信</option>
                                </select>
                            </td>
                            <td class="base-form-label"></td>
                            <td class="base-form-item"></td>
                        </tr>
                        <tr>
                            <td class="base-form-label">支付宝账号：</td>
                            <td class="base-form-item"><input type="text" /></td>
                        </tr>
                    </table>
                    <div class="base-form-submit-div">
                        <input class="submit-btn" type="button" value="保存" />
                        <input class="reset-btn" type="button" value="重置" />
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td class="center-split2" colspan="2">
                <div class="blank-div"></div>
            </td>
        </tr>
        <tr>
            <td class="center-left operate-menus">
                <div>
                    <div class="operate-menu choose-menu">基本信息</div>
                    <div class="operate-menu">已投项目</div>
                </div>
            </td>
            <td class="center-split"></td>
        </tr>
    </table>
</body>
</html>
