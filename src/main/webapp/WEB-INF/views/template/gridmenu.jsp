%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page pageEncoding="UTF-8"%>

<div class="site-gridmenu scrollable scrollable-inverse scrollable-vertical is-disabled">
    <div class="scrollable-container" style="">
        <div class="scrollable-content" style="">
            <ul>
                <li>
                    <a href="<c:url value="/"/>">
                        <i class="icon wb-home"></i>
                        <span>Game</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin"/>">
                        <i class="icon wb-calendar"></i>
                        <span>Admin</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="scrollable-bar scrollable-bar-vertical scrollable-bar-hide is-disabled" draggable="false">
        <div class="scrollable-bar-handle"></div>
    </div>
</div>