<html ng-app="guestpage">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="${pageContext.request.contextPath}resources/assets/bootstrap-3.0.2/css/bootstrap.min.css"  rel="stylesheet">

    <link href="${pageContext.request.contextPath}resources/assets/bootstrap-3.0.2/css/bootstrap-theme.min.css"  rel="stylesheet">

    <script src="${pageContext.request.contextPath}resources/assets/bootstrap-3.0.2/js/bootstrap.min.js"></script>

    <link href="${pageContext.request.contextPath}resources/assets/events.css"  rel="stylesheet">

    <script src="${pageContext.request.contextPath}resources/assets/jquery-1.10.2.min.js"></script>

    <script src="${pageContext.request.contextPath}resources/assets/angular/angular.min.js"></script>

    <script src="${pageContext.request.contextPath}resources/assets/angular/angular-resource.js"></script>

</head>

<body>

<nav class="navbar navbar-default ">
    <div class="navbar-header ">
        <a class="navbar-brand" ng:href="#/eventlist">Другие эвенты</a>
        <a class="navbar-brand" ng:href="#/notelist">Заметки</a>
        <a class="navbar-brand navbar-right" href="/pages/auth/logout" > Выйти</a>
    </div>

</nav>

<ng-view/>

<script src="${pageContext.request.contextPath}resources/js/harvester.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/guestlist/guestlist.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/guest/newguest.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/guest/editguest.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/guest/guestwidget.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/eventlist/eventlist.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/event/newevent.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/event/editevent.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/event/eventwidget.js"></script>


<script src="${pageContext.request.contextPath}resources/js/components/note/newnote.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/note/editnote.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/note/notewidget.js"></script>

<script src="${pageContext.request.contextPath}resources/js/components/notes/notelist.js"></script>

</body>
</html>
