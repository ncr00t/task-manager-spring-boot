<!DOCTYPE HTML>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>TaskManager | Home</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css"/>
</head>
<body>
    <nav class="navbar navbar-inverse">
            <a href="/" class="navbar-brand"> <span class="glyphicon glyphicon-list-alt"></span> TaskManager</a>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a class="nav-link" href="/"><span class="glyphicon glyphicon-home"></span> Home </a></li>
                    <li><a class="nav-link" href="createTask"><span class="glyphicon glyphicon-plus"></span> Add Task</a></li>
                    <li><a class="nav-link" href="tasks"><span class="glyphicon glyphicon-list"></span> Show All Tasks</a> </li>
                </ul>
            </div>
    </nav>

    <c:choose>
        <c:when test="${mode == 'MODE_HOME'}">
            <div class="container" id="homeDiv">
                <div class="jumbotron text-center">
                    <h1>Welcome to Task Manager!!!</h1>
                </div>
            </div>
            <div class="top-cover center-block">
                <div class="hero-unit col-md-3">
                </div>
                <div class="hero-unit col-md-3">
                    <h1>Add Task</h1>
                    <p>You can add new task.</p>
                    <p>
                        <a href="createTask" class="btn btn-primary btn-large">Add Task</a>
                    </p>
                 </div>
                <div class="hero-unit col-md-6">
                    <h1>Show Tasks</h1>
                    <p>You see all tasks.</p>
                    <p>
                        <a href="tasks" class="btn btn-primary btn-large">Show Tasks</a>
                    </p>
                </div>
            </div>
        </c:when>

        <c:when test="${mode == 'MODE_SHOW_ALL_TASKS'}">
            <div class="container  text-center" id="tasksDiv">
                <h3>My Tasks</h3>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered text-left">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Date</th>
                            <th>Finished</th>
                            <th></th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${tasks}">
                            <tr>
                                <td>${task.id}</td>
                                <td>${task.name}</td>
                                <td>${task.description}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm-ss" value="${task.dateCreated}"/></td>
                                <td>${task.finished}</td>
                                <td><a href="updateTask?id=${task.id}"> Update <span class="glyphicon glyphicon-edit"></span></a> </td>
                                <td><a href="deleteTask?id=${task.id}"> Delete <span class="glyphicon glyphicon-trash"></span></a> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:when>

        <c:when test="${mode == 'MODE_CREATE_TASK' || mode == 'MODE_UPDATE_TASK'}">
            <div class="container text-center">
                <h3>Manage Tasks</h3>
                <form class="form-horizontal"  method="POST" action="saveTask">
                    <input type="hidden" name="id" value="${task.id}"/>
                    <div class="form-group">
                        `   <label class="control-label col-md-3">Name</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="name" value="${task.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Description</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="description" value="${task.description}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Finished</label>
                        <div class="col-md-7">
                            <input type="radio" class="col-sm-1" name="finished" value="true"/>
                            <div class="col-sm-1">Yes</div>
                            <input type="radio" class="col-sm-1" name="finished" value="false" checked/>
                            <div class="col-sm-1">No</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                    </div>
                </form>
            </div>
        </c:when>
    </c:choose>

    <footer class="container-fluid text-center foot navbar-fixed-bottom navbar-default">

        <div class="copyrights">
            <p class="white-txt"> <span class="glyphicon glyphicon-list-alt"></span> my-task-manager.com &copy; 2018
            </p>
            <hr class="dark-line">
            <a class="link" href="/">Home</a>&nbsp
            <a class="link" href="createTask">Add task</a>&nbsp
            <a class="link" href="tasks">Show All Tasks</a>
            <br><br>
            <p class="white-txt small">
                All rights reserved
            </p>
        </div>
    </footer>


    <script src="../../static/js/jquery-1.11.1.min.js"> </script>
    <script src="../../static/js/bootstrap.min.js"> </script>
</body>
</html>