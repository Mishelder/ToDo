Date.prototype.addDays = function(days) {
    this.setDate(this.getDate() + days);
}

Date.prototype.subtractDays = function(days) {
    this.setDate(this.getDate() - days);
}

Date.prototype.formatToDM = function() {
    return this.getDate() + ' ' + monthNames[this.getMonth()];
}

Date.prototype.formatToDMY = function() {
    return this.getDate() + '-' + monthNames[this.getMonth()] + '-' + this.getFullYear();
}

const now = new Date();
const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];
const RANGE_VALUE = 8;
const moveLeft = document.getElementById("move_left");
const moveRight = document.getElementById("move_right");
const toDoListDiv = document.getElementById("to_do_list");
let moveableFrom = new Date();
let moveableTo = new Date();
let currentFrom = new Date();
let currentTo = new Date();
moveableFrom.subtractDays(RANGE_VALUE);
moveableTo.addDays(RANGE_VALUE)
currentTo.addDays(RANGE_VALUE / 2)

const allTasks = {}

function getTasks(from, to) {
    fetch('/fetch', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({'from': from, 'to': to}),
    }).then(response => response.text())
        .then(text => Object.assign(allTasks, JSON.parse(text)));
}

getTasks(moveableFrom.toDateString(), moveableTo.toDateString());

moveLeft.addEventListener("click", () => {
    document.getElementById(currentTo.formatToDMY()).classList.add('hidden');
    currentFrom.subtractDays(1);
    currentTo.subtractDays(1);
    let newDate = document.getElementById(currentFrom.formatToDMY());
    if(newDate === null){
        createToDoDay(currentFrom,false);
    }else{
        newDate.classList.remove('hidden');
    }
    if (moveableFrom.getDate() === currentFrom.getDate()) {
        moveableFrom.subtractDays(1);
        let to = new Date(moveableFrom);
        moveableFrom.subtractDays(RANGE_VALUE/2);
        let from = new Date(moveableFrom);
        getTasks(from.toDateString(), to.toDateString());
    }
});

moveRight.addEventListener("click", () => {
    document.getElementById(currentFrom.formatToDMY()).classList.add('hidden');
    currentFrom.addDays(1);
    currentTo.addDays(1);
    let newDate = document.getElementById(currentTo.formatToDMY());
    if(newDate === null){
        createToDoDay(currentTo,true);
    }else{
        newDate.classList.remove('hidden');
    }
    if (moveableTo.getDate() === currentTo.getDate()) {
        moveableTo.addDays(1);
        let from = new Date(moveableTo);
        moveableTo.addDays(RANGE_VALUE/2);
        let to = new Date(moveableTo);
        getTasks(from.toDateString(), to.toDateString());
    }
});



function dateInRange(startDate, stopDate) {
    let dateArray = new Array();
    let currentDate = new Date(startDate);
    while (currentDate <= stopDate) {
        dateArray.push(new Date (currentDate));
        currentDate.addDays(1);
    }
    return dateArray;
}

function createToDoDay(date,append) {
    let divToDoDay = new Div(date.formatToDMY(), 'to_do_day'),
        divTasks = new Div('', 'tasks'),
        divDate = new Div('', 'date'),
        labelDate = document.createElement("label");
    labelDate.textContent = date.formatToDM();
    let toDoDayElement;
    if(append) {
        toDoDayElement= divToDoDay.renderAppend(toDoListDiv);
    }else{
        toDoDayElement = divToDoDay.renderPrepend(toDoListDiv);
    }
    let dateElement = divDate.renderAppend(toDoDayElement);
    dateElement.append(labelDate);
    divTasks.renderAppend(toDoDayElement);
}

function initDateRange(dateFrom,dateTo) {
    let rangeOfDates = dateInRange(dateFrom,dateTo);
    for(let item of rangeOfDates){
        createToDoDay(item,true);
    }
}

initDateRange(currentFrom,currentTo);