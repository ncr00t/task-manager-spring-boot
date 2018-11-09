<!DOCTYPE HTML>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>TaskManager | Home</title>
    <meta charset="utf-8">

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/js/gijgo.min.js" type="text/javascript"></script>
    <script src="../../static/js/draggable.js"></script>

    <link rel="stylesheet" href="../../static/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gijgo@1.9.10/css/gijgo.min.css" type="text/css"/>
    <link rel="stylesheet" href="../../static/web-fonts/css/all.min.css" type="text/css"/>
    <link rel="stylesheet" href="../../static/css/main.css" type="text/css"/>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a href="/" class="navbar-brand">
            <span class="fas fa-tasks"></span> TaskManager
        </a>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav mr-auto mt-2 mt-lg-0">
                <li><a class="nav-link" href="/"><span class="fas fa-home"></span>  Home </a></li>
                <li><a class="nav-link" href="createTask"><span class="fas fa-plus"></span>  Add Task</a></li>
                <li><a class="nav-link" href="tasks"><span class="far fa-list-alt"></span> All Tasks</a> </li>
                <li><a class="nav-link" href="completedTasks"><span class="fas fa-check"></span> Completed Tasks</a> </li>
                <li><a class="nav-link" href="tasksInWork"><span class="fas fa-check"></span> Tasks in work</a> </li>
            </ul>
        </div>
        <div class="navbar-right row">
            <form class="form-inline row align-items-center col-sm-6" method="POST" action="searchTask" style="height: 30px; width: 430px;" >
                <input class="form-control col-sm-8" type="search" width="200" name="name" value="${task.name}" placeholder="Enter task name" aria-label="Search" >
                <button class="btn btn-primary col-sm-4" type="submit">Search</button>
            </form>
            <div class="container col-sm-6" id="formForSearchByDate">
                <form class="form-inline row align-items-center"  method="POST" action="searchTasksByDate">
                    <input id="datepicker" name="dateCreated" placeholder="Search on date( mm/dd/yyyy )" width="276" />
                    <script>
                        $('#datepicker').datepicker({
                            uiLibrary: 'bootstrap4'
                        });
                    </script>
                </form>
            </div>
        </div>
    </nav>
    <c:choose>
        <c:when test="${mode == 'MODE_HOME'}">
            <div class="container" id="homeDiv">
                <div class="jumbotron text-center">
                    <h1>Welcome to Task Manager!!!</h1>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="hero-unit col-md-3">
                    <h1>Add Task </h1>
                    <p>You can add new task.</p>
                    <p>
                        <a href="createTask" class="btn btn-primary btn-large">Add Task <span class="fas fa-plus"></span></a>
                    </p>
                 </div>
                <div class="hero-unit col-md-1">
                    <h1>Show Tasks </h1>
                    <p>You see all tasks.</p>
                    <p>
                        <a href="tasks" class="btn btn-primary btn-large">Show Tasks <span class="far fa-list-alt"></span></a>
                    </p>
                </div>
            </div>
        </c:when>

        <c:when test="${mode == 'MODE_SHOW_ALL_TASKS'}">
            <div class="container" id="tasksDiv">
                <h3>All Tasks (${tasks.size()})</h3>
                <hr>
                <div>
                    <div class="sortable row">
                      <c:forEach var="task" items="${tasks}">
                        <div class="hero-unit col-3" >
                             <div class="taskContainer">
                                <h3>${task.name}</h3>
                                <div class="taskInfo">
                                  <p class="taskDescription">${task.description}</p>
                                </div>
                                  <p class="taskDate"><fmt:formatDate pattern="yyyy/MM/dd" value="${task.dateCreated}"/></p>
                                  <p class="taskStatus">${task.finished == false ? 'Active' : 'Completed'}</p>
                                  <p>
                                      <a href="addInCompletedTasks?id=${task.id}" class="btn btn-success btn-large col col-md"  style="background-color: #71dd8a"> Done
                                          <span class="fas fa-check"></span>
                                      </a>
                                      <a href="updateTask?id=${task.id}" class="btn btn-primary btn-large col col-md"> Update
                                          <span class="fas fa-edit"></span>
                                      </a>
                                  </p>
                              </div>
                         </div>
                      </c:forEach>
                   </div>
                <hr>
            </div>
        </c:when>

        <c:when test="${mode == 'MODE_SHOW_COMPLETED_TASKS'}">
                <div class="container" id="tasksDiv">
                    <h3>All Completed Tasks (${tasks.size()})</h3>
                    <hr>
                    <div>
                        <div class="sortable row">
                            <c:forEach var="task" items="${tasks}">
                                <div class="hero-unit col-3" >
                                    <div class="taskContainer">
                                        <h3>${task.name}</h3>
                                        <div class="taskInfo">
                                            <p class="taskDescription">${task.description}</p>
                                        </div>
                                        <p class="taskDate"><fmt:formatDate pattern="yyyy/MM/dd" value="${task.dateCreated}"/></p>
                                        <p class="taskStatus">${task.finished == false ? 'Active' : 'Completed'}</p>
                                        <p>
                                            <a href="deleteCompletedTask?id=${task.id}" class="completedBtn btn btn-info btn-large col col-md"  > Delete
                                                <span class="fas fa-trash-alt"></span>
                                            </a>
                                            <a href="updateTask?id=${task.id}" class="btn btn-primary btn-large col col-md"> Update
                                                <span class="fas fa-edit"></span>
                                            </a>
                                        </p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                    </div>
        </c:when>

         <c:when test="${mode == 'MODE_SHOW_TASKS_WORK'}">
               <div class="container" id="tasksDiv">
                   <h3>All Completed Tasks (${tasks.size()})</h3>
                   <hr>
                   <div>
                      <div class="sortable row">
                         <c:forEach var="task" items="${tasks}">
                              <div class="hero-unit col-3" >
                              <div class="taskContainer">
                                   <h3>${task.name}</h3>
                                     <div class="taskInfo">
                                          <p class="taskDescription">${task.description}</p>
                                          </div>
                                          <p class="taskDate"><fmt:formatDate pattern="yyyy/MM/dd" value="${task.dateCreated}"/></p>
                                          <p class="taskStatus">${task.finished == false ? 'Active' : 'Completed'}</p>
                                          <p>
                                             <a href="deleteCompletedTask?id=${task.id}" class="completedBtn btn btn-info btn-large col col-md"  > Delete
                                                 <span class="fas fa-trash-alt"></span>
                                              </a>
                                              <a href="updateTask?id=${task.id}" class="btn btn-primary btn-large col col-md"> Update
                                                  <span class="fas fa-edit"></span>
                                              </a>
                                            </p>
                                   </div>
                                  </div>
                              </c:forEach>
                       </div>
                         <hr>
                </div>
           </c:when>


                    <c:when test="${mode == 'MODE_CREATE_TASK' || mode == 'MODE_UPDATE_TASK'}">
            <div  class="container text-center createTaskContainer">
                <br>
                <h3>${mode == 'MODE_CREATE_TASK' ? 'Create Task' : 'Update Task'}</h3>
                <form class="form-horizontal"  method="POST" action="saveTask">
                    <input type="hidden" name="id" value="${task.id}"/>
                    <div id="nameDiv" class="form-group">
                        <input type="text" class="form-control col-md-10 textField" name="name" placeholder="Enter task name" value="${task.name}"/>
                     </div>

                    <div id="descriptionDiv">
                        <input type="text" class="form-control col-md-10 textField" name="description" placeholder="Enter task description" value="${task.description}"/>
                    </div>

                    <div id="finishedDiv" class="rows">
                        <label class="control-label col-md-2">Completed?</label>
                        <br>
                        <input type="radio" class="col-sm-1" name="finished" value="true"/>
                        <div class="col-sm-1 answerDiv">Yes</div>
                        <input type="radio" class="col-sm-1 answerDiv" name="finished" value="false" checked/>
                        <div class="col-sm-1 answerDiv">No</div>
                    </div>
                    <div class="form-group">
                        <input id="submitBtn" type="submit" class="btn btn-primary" value="${mode == 'MODE_CREATE_TASK' ? 'Create' : 'Update'}"/>
                    </div>
                </form>
            </div>
        </c:when>
    </c:choose>

    <footer class="row-fluid text-center navbar-fixed-bottom navbar-default">
        <div class="copyrights">
            <p class="white-txt">
                <span class="fas fa-tasks fa-lg siteLabel"></span> my-task-manager.com &copy; 2018
            </p>
            <hr class="dark-line">
            <a class="link" href="/">Home</a>&nbsp
            <a class="link" href="createTask">Add task</a>&nbsp
            <a class="link" href="tasks">Show All Tasks </a>
            <br><br>
            <p class="white-txt small">All rights reserved</p>
        </div>
    </footer>


    <script src="../../static/js/jquery-1.11.1.min.js"> </script>
    <script src="../../static/js/bootstrap.min.js"> </script>
</body>
</html>