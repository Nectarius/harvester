<html ng-app="guestpage">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="<c:url value="/resources/assets/bootstrap-3.0.2/css/bootstrap.min.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/assets/bootstrap-3.0.2/css/bootstrap-theme.min.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/assets/events.css" />" rel="stylesheet">

    <script src="<c:url value="/resources/assets/jquery-1.10.2.min.js" />"></script>

    <script src="<c:url value="/resources/assets/bootstrap-3.0.2/js/bootstrap.min.js" />"></script>

    <script src="<c:url value="/resources/assets/angular/angular.min.js" />"></script>

    <script src="<c:url value="/resources/assets/angular/angular-resource.js" />"></script>

</head>

<body>

<nav class="navbar navbar-default ">
    <div class="navbar-header ">
        <a class="navbar-brand" ng:href="#/eventlist">Другие эвенты</a>
        <a class="navbar-brand navbar-right" href="/pages/auth/logout" > Выйти</a>
    </div>

</nav>

<ng-view/>

<script src="<c:url value="/resources/js/harvester.js" />"></script>

<script src="<c:url value="/resources/js/components/guestlist/guestlist.js" />"></script>

<script src="<c:url value="/resources/js/components/guest/newguest.js" />"></script>

<script src="<c:url value="/resources/js/components/guest/editguest.js" />"></script>

<script src="<c:url value="/resources/js/components/guest/guestwidget.js" />"></script>

<script src="<c:url value="/resources/js/components/eventlist/eventlist.js" />"></script>

<script src="<c:url value="/resources/js/components/event/newevent.js" />"></script>

<script src="<c:url value="/resources/js/components/event/editevent.js" />"></script>

<script src="<c:url value="/resources/js/components/event/eventwidget.js" />"></script>

</body>
</html>
