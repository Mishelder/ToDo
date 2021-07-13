Date.prototype.addDays = function (days) {
    this.setDate(this.getDate() + days);
}

Date.prototype.subtractDays = function (days) {
    this.setDate(this.getDate() - days);
}

Date.prototype.formatToDM = function () {
    return this.getDate() + ' ' + monthNames[this.getMonth()];
}

Date.prototype.formatToDMY = function () {
    return this.getFullYear() + '-' + isNeededZero(this.getMonth() + 1) + '-' + isNeededZero(this.getDate());
}

function isNeededZero(date) {
    if (date > 0 && date < 10) {
        return '0' + date;
    }
    return date;
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

function getTasks(from, to, func = function () {
}) {
    fetch('/fetch', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({'from': from, 'to': to}),
    }).then(response => response.text())
        .then(text => Object.assign(allTasks, JSON.parse(text)))
        .then(() => {
            func(currentFrom, currentTo);
        })
    return true;
}

function saveTask(date, inputElement, taskDiv) {
    fetch('/save', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(
            {
                'task': inputElement.value,
                'date': date,
                'doneTask': inputElement.classList.contains('is_done') ? 'checked' : ''
            }),
    }).then(response => response.text())
        .then(text => {
            taskDiv.id = JSON.parse(text);
        }).then(() => {
        createAlternationDiv(taskDiv);
        createDivForTask(date);
    });
}

function deleteTask(id) {
    fetch('/delete', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({'id': id}),
    }).then();
}

function updateTask(id, value,done) {
    fetch('/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(
            {
                'id': id,
                'task': value,
                'isDone': done
            }),
    }).then();
}

moveLeft.addEventListener("click", () => {
    document.getElementById(currentTo.formatToDMY()).classList.add('hidden');
    currentFrom.subtractDays(1);
    currentTo.subtractDays(1);
    let newDate = document.getElementById(currentFrom.formatToDMY());
    if (newDate === null) {
        createToDoDay(currentFrom, false);
        createTask(currentFrom.formatToDMY());
    } else {
        newDate.classList.remove('hidden');
    }
    if (moveableFrom.getDate() === currentFrom.getDate()) {
        moveableFrom.subtractDays(1);
        let to = new Date(moveableFrom);
        moveableFrom.subtractDays(RANGE_VALUE / 2);
        let from = new Date(moveableFrom);
        getTasks(from.toDateString(), to.toDateString());
    }
});

moveRight.addEventListener("click", () => {
    document.getElementById(currentFrom.formatToDMY()).classList.add('hidden');
    currentFrom.addDays(1);
    currentTo.addDays(1);
    let newDate = document.getElementById(currentTo.formatToDMY());
    if (newDate === null) {
        createToDoDay(currentTo, true);
        createTask(currentTo.formatToDMY());
    } else {
        newDate.classList.remove('hidden');
    }
    if (moveableTo.getDate() === currentTo.getDate()) {
        moveableTo.addDays(1);
        let from = new Date(moveableTo);
        moveableTo.addDays(RANGE_VALUE / 2);
        let to = new Date(moveableTo);
        getTasks(from.toDateString(), to.toDateString());
    }
});


function dateInRange(startDate, stopDate) {
    let dateArray = new Array();
    let currentDate = new Date(startDate);
    while (currentDate <= stopDate) {
        dateArray.push(new Date(currentDate));
        currentDate.addDays(1);
    }
    return dateArray;
}

function createToDoDay(date, append) {
    let divToDoDay = new Div(date.formatToDMY(), 'to_do_day'),
        divTasks = new Div('', 'tasks'),
        divDate = new Div('', 'date'),
        labelDate = document.createElement("label");
    labelDate.textContent = date.formatToDM();
    let toDoDayElement;
    if (append) {
        toDoDayElement = divToDoDay.renderAppend(toDoListDiv);
    } else {
        toDoDayElement = divToDoDay.renderPrepend(toDoListDiv);
    }
    if(date.formatToDMY()===now.formatToDMY())
        toDoDayElement.classList.add('current_day');
    let dateElement = divDate.renderAppend(toDoDayElement);
    dateElement.append(labelDate);
    divTasks.renderAppend(toDoDayElement);
}

function initDateRange(dateFrom, dateTo) {
    let rangeOfDates = dateInRange(dateFrom, dateTo);
    for (let item of rangeOfDates) {
        createToDoDay(item, true);
        createTask(item.formatToDMY());
    }
}

function createDivForExistTask(date, item) {
    let tasksDiv = document.getElementById(date).getElementsByClassName('tasks')[0],
        taskDiv = new Div(item['id'], 'task').renderAppend(tasksDiv),
        taskValueDiv = new Div('', 'value_task').renderAppend(taskDiv),
        task = new InputElement('text', '', item['taskName'], '', '', '');
    let inputElement = task.renderAppend(taskValueDiv);
    inputElement.disabled = true;
    if (item['done'] === 'checked')
        inputElement.classList.add('is_done');
    changeDoneStatusOnClick(taskValueDiv,inputElement,taskDiv);
    createAlternationDiv(taskDiv);
}

function createAlternationDiv(taskDiv) {
    let alternationDiv = new Div('', 'alternation_task', 'hidden').renderAppend(taskDiv),
        deleteImage = document.createElement('img'),
        alternateImage = document.createElement('img'),
        divForDeleteImage = new Div('', 'delete_image').renderAppend(taskDiv),
        divForAlternateImage = new Div('', 'alternate_image').renderAppend(taskDiv);
    deleteImage.src = '/fileLoader?fileName=recycle&extension=png&folder=img';
    alternateImage.src = '/fileLoader?fileName=pencil&extension=png&folder=img';
    divForDeleteImage.addEventListener('click', () => {
        taskDiv.remove();
        deleteTask(taskDiv.id);
    });
    divForAlternateImage.addEventListener('click',()=>{
       let value_task = taskDiv.getElementsByClassName('value_task')[0],
        input = value_task.getElementsByTagName('input')[0];
       input.disabled = false;
       input.focus();
       input.onblur = () => {
            if (input.value.length === 0) {
                taskDiv.remove();
                deleteTask(taskDiv.id);
            }else{
                input.disabled = true;
                updateTask(taskDiv.id,input.value,input.classList.contains('is_done'));
            }
        };
    });
    divForAlternateImage.append(alternateImage);
    divForDeleteImage.append(deleteImage);
    alternationDiv.append(divForDeleteImage);
    alternationDiv.append(divForAlternateImage);
    taskDiv.append(alternationDiv);
}

function changeDoneStatusOnClick(taskValueDiv,inputElement,taskDiv){
    taskValueDiv.addEventListener('click', () => {
        if (inputElement.value.length !== 0) {
            updateTask(taskDiv.id,inputElement.value,inputElement.classList.toggle('is_done'));
        }
    });
}

function createDivForTask(date) {
    let tasksDiv = document.getElementById(date).getElementsByClassName('tasks')[0],
        taskDiv = new Div('', 'task').renderAppend(tasksDiv),
        taskValueDiv = new Div('', 'value_task').renderAppend(taskDiv),
        task = new InputElement('text', '', '', '', '', '');
    let inputElement = task.renderAppend(taskValueDiv);
    changeDoneStatusOnClick(taskValueDiv,inputElement,taskDiv);
    inputElement.onblur = () => {
        save();
    };
    inputElement.addEventListener('keydown',(event)=>{
       if(event.key === "Enter"){
           inputElement.onblur = ()=>{};
           save();
       }
    });
    inputElement.focus();

    function save(){
        if (inputElement.value.length !== 0) {
            inputElement.disabled = true;
            saveTask(date, inputElement, taskDiv);
        }
    }
}


function createTask(date) {
    let tasksValue = null;
    if (allTasks.hasOwnProperty(date)) {
        tasksValue = allTasks[date];
        for (let index = 0; index < tasksValue.length; index++)
            createDivForExistTask(date, tasksValue[index]);
        createDivForTask(date);
    } else {
        createDivForTask(date);
    }
}

getTasks(moveableFrom.toDateString(), moveableTo.toDateString(), initDateRange);
