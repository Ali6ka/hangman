<%@ page pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cm" tagdir="/WEB-INF/tags"%>

<tiles:insertDefinition name="master">

    <tiles:putAttribute name="title" value="Access Denied" />
    <tiles:putAttribute name="sidebar">
        <cm:sidebar/>
    </tiles:putAttribute>

    <tiles:putAttribute name="page-header">
        <h1 class="page-title">Error</h1>
    </tiles:putAttribute>

    <tiles:putAttribute name="page-content">
        <div class="page-content vertical-align-middle text-center">
            <header>
                <h1 class="animation-slide-top">Access Denied</h1>
            </header>
            <p class="error-advise">You are not authorized to access this page</p>
            <a class="btn btn-primary btn-round" href="<c:url value="/" />">GO TO HOME PAGE</a>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>