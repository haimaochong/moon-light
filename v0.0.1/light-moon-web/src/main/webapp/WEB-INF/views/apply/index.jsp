<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <%@ include file="../commons/resources.jsp"%>
    <link href="${mimeBase}/styles/apply.css" rel="stylesheet" type="text/css" />
    <script src="${scripts}/apply/apply.js?version=${version}"></script>
</head>

<body>
    <jsp:include page="../commons/head.jsp"></jsp:include>
    <div class="apply-desc">
        <div class="apply-desc-title">
            <div class="f-left">交单说明</div>
            <div class="f-right">
                <input type="button" value="展开说明" class="apply-desc-up-btn">
            </div>
            <div class="lihh-clear"></div>
        </div>
        <div class="apply-desc-content hide"></div>
    </div>
    <div class="apply-detail-div">
        <div class="apply-title">
            <img class="item-img" src="${mimeBase}/images/test.png" />
            <div class="item-name">宜人贷</div>
        </div>
        <div class="apply-detail">
            <pre>
【宜人贷】月标！3月标！网贷之家第一名！广发银行存管！

【平台资质】网贷之家第一名（总共100名）！2015年12月18日在美国纽交所上市，有十一年以上的风控经验！
                百度1000万美元认购宜人贷IPO新股！广发银行存管！
【通用推广链接】http://t.cn/R9OXtcC
【投资收益】
【2W定额档/新手标】利息160+红包70+返现140=370，综合年化22.2%
【5W定额档/3月标】利息625+红包70+返现350=1045，综合年化8.36%
【10W定额档/3月标】利息1250+红包70+返现700=2020，综合年化8.08%
【20W定额档/3月标】利息2500+红包70+返现1400=3970，综合年化7.94%
【50W定额档/3月标】利息6250+红包70+返现3500=9820，综合年化7.85%
【注意事项】
1、仅限首投！定档2/5/10/20/50w！
2、请仔细阅读标的信息！月标为2万定额档，3月标最高50万定额档！
3、标的名称：“宜定盈（新手标）、宜定盈3月标”
【交单格式】手机+真实姓名+金额


【撸毛代理价：110元/万】
            </pre>
        </div>
        <div>
            <input class="apply-btn" type="button" value="交单" />
        </div>
    </div>
</body>
</html>
