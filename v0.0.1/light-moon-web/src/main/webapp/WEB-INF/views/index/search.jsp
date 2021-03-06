<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="lhh-search" style="margin-top: 35px">
    <div class="search-title">
        <div class="f-left">全部分类</div>
        <div class="f-right">
            <input type="button" value="收起筛选" class="search-up-btn">
        </div>
        <div class="lihh-clear"></div>
    </div>
    <div class="search-content">
        <div class="search-item js-platform">
            <div class="search-name-div f-left">平台名称 : </div>
            <div class="search-all-div f-left"><div class="search-menu search-selected-menu">不限</div></div>
            <div class="search-content-div f-left">
                <ul>
                	<c:forEach items="${platformList}" var="item" varStatus="status">
                		<c:if test="${status.index != 0 && status.index%7==0 && !status.last }">
                			</ul><ul class="allow-hide">
                		</c:if>
	                    <li><div class="search-menu" data="${item.id }">${item.name}</div></li>
                	</c:forEach>
                </ul>
            </div>
        </div>
        <div class="search-content-more">
            <div class="f-left">[收起]</div>
        </div>
        <div class="search-item js-isInvestCycle">
            <div class="search-name-div f-left">投标次数 : </div>
            <div class="search-all-div f-left"><div class="search-menu search-selected-menu">不限</div></div>
            <div class="search-content-div f-left">
                <ul>
                    <li><div class="search-menu" data="0">仅限首投</div></li>
                    <li><div class="search-menu" data="1">可复投</div></li>
                </ul>
            </div>
        </div>
        <div class="lihh-clear"></div>
    </div>
</div>