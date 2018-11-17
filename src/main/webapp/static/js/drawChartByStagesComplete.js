var ctx = document.getElementById('pieChartStatisticsByStagesComplete').getContext('2d');
var countAllTasks = parseInt($('#countAllTasks').val());
var countCompletedTasks = parseInt($('#countCompletedTasks').val());
var countTasksInWork = parseInt($('#countTasksInWork').val());

var pieChart = new Chart(ctx, {
    type: 'pie',
    animateRotate:true,
    data: {
        labels: ["All tasks", "TasksInWork", "Completed tasks"],
        datasets: [{
            label: "Statistics complete",
            backgroundColor: ["red", "orange", "green"],
            borderColor: "black",
            data: [countAllTasks, countTasksInWork, countCompletedTasks]
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Statistics by stages complete',
            fontColor:"black",
            fontSize: 20
        },
        legend: {
            labels: {
                fontColor: "black",
                fontSize:16
            },
            position: "top"
        }
    }
});
