var ctx = document.getElementById('myChart').getContext('2d');
var countAllTasks = parseInt($('#countAllTasks').val());
var countCompletedTasks = parseInt($('#countCompletedTasks').val());

var chart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["All tasks", "Completed tasks"],
        datasets: [{
            label: "Statistics complete",
            backgroundColor: ["red", "green"],
            borderColor: "black",
            data: [countAllTasks, countCompletedTasks]
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Statistics complete',
            fontColor:"black",
            fontSize: 20
        },
        legend: {
            display: false
        },
        scales: {
            yAxes: [{
                ticks: {
                    fontColor: "black",
                    fontSize: 16,
                    beginAtZero:true
                }
            }],

            xAxes:[{
                ticks:{
                    fontColor: "black",
                    fontSize:16
                }
            }]
        }
    }
});