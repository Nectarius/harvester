<html ng-app="guestpage">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="assets/bootstrap-3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/bootstrap-3.0.2/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="assets/events.css">
    <script src="assets/jquery-1.10.2.min.js"></script>
    <script src="assets/bootstrap-3.0.2/js/bootstrap.min.js"></script>
    <script src="assets/angular/angular.min.js"></script>
    <script src="assets/angular/angular-resource.js"></script>
</head>

<body>

<nav class="navbar navbar-default ">
    <div class="navbar-header ">
        <a class="navbar-brand">На пейнтбол :-)</a>
    </div>
</nav>

<ng-view/>

<script src="js/harvester.js"></script>

<script src="components/guestlist/guestlist.js"></script>

<script src="components/guest/newguest.js"></script>

<script src="components/guest/editguest.js"></script>

<script src="components/guest/guestwidget.js"></script>

<script src="components/eventlist/eventlist.js"></script>

<script src="components/event/newevent.js"></script>

<script src="components/event/editevent.js"></script>

<script src="components/event/eventwidget.js"></script>

</body>
</html>
