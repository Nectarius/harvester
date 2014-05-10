
<html ng-app="chartpage" xmlns:component="http://graphdashboard.ru/components"
      xmlns:portlet="http://graphdashboard.ru/portlet">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="assets/bootstrap-3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/bootstrap-3.0.2/css/bootstrap-theme.min.css">
    <script src="assets/jquery-1.10.2.min.js"></script>
    <script src="assets/bootstrap-3.0.2/js/bootstrap.min.js"></script>
    <script src="assets/knockout-3.0.0.js"></script>


</head>

<body>

<nav class="navbar navbar-default ">
    <div class="navbar-header ">
      <a class="navbar-brand" >Words</a>
    </div>
</nav>

<div class="panel panel-default container ">
    <!-- Default panel contents -->
    <div class="panel-heading ">Word list</div>
   <%-- <div class="panel-body">
        <p>...</p>
    </div>--%>

    <!-- Table -->
    <div class="panel-body">
    <table class="table table-striped" >
        <thead>
        <tr><th>Слово</th><th>Перевод</th><th>Источник</th><th>Приоритет</th></tr>
        </thead>
        <tbody data-bind="foreach: words">
        <tr >
            <td data-bind="text: word"></td>
            <td data-bind="text: translate"></td>
            <td data-bind="text: whence"></td>
            <td data-bind="text: priority"></td>
        </tr>
        </tbody>
    </table>


    <div class="pull-right">
        <button class="btn btn-success" data-bind="click: addWord ">new word</button>
    </div>
    </div>
</div>

<script src="harvester.js"></script>

</body>
</html>
