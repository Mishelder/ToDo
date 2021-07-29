Date.prototype.addDays = function (days) {
    this.setDate(this.getDate() + days);
}

Date.prototype.subtractDays = function (days) {
    this.setDate(this.getDate() - days);
}

Date.prototype.formatToDM = function () {
    return this.getDate() + ' ' + MONTH_NAMES[this.getMonth()];
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

const NOW = new Date(),
    MONTH_NAMES = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ],
    RANGE_VALUE = 8,
    MIN_LENGTH_FOR_TEXT_AREA = 30;

const moveLeft = document.getElementById("move_left"),
    moveRight = document.getElementById("move_right"),
    toDoListDiv = document.getElementById("to_do_list");

let moveableFrom = new Date(),
    moveableTo = new Date(),
    currentFrom = new Date(),
    currentTo = new Date();
currentTo.addDays(RANGE_VALUE/2);
changeCurrentRange(currentFrom, new Date(currentTo));
changeMoveableRange(currentFrom, currentTo);

const allTasks = {};

async function getTasks(from, to) {
    return await fetch('/fetch', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({'from': from, 'to': to}),
    }).then(response => response.text())
        .then(text => Object.assign(allTasks, JSON.parse(text)));
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
            let parse = JSON.parse(text);
            if (!parse.hasOwnProperty("task")) {
                taskDiv.id = parse;
            } else {
                inputElement.value = parse.task;
            }

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

function updateTask(id, value, done) {
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
    let oldToDoDiv = document.getElementById(currentFrom.formatToDMY());
    currentFrom.subtractDays(1);
    currentTo.subtractDays(1);
    let newDate = document.getElementById(currentFrom.formatToDMY());
    if (newDate === null) {
        oldToDoDiv.before(createToDoDay(currentFrom).divElement);
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
    let oldToDoDiv = document.getElementById(currentTo.formatToDMY());
    currentFrom.addDays(1);
    currentTo.addDays(1);
    let newDate = document.getElementById(currentTo.formatToDMY());
    if (newDate === null) {
        oldToDoDiv.after(createToDoDay(currentTo).divElement);
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

function createToDoDay(date) {
    let divToDoDay = new Div(date.formatToDMY(), 'to_do_day'),
        divTasks = new Div('', 'tasks'),
        divDate = new Div('', 'date'),
        labelDate = document.createElement("label");
    labelDate.textContent = date.formatToDM();
    if (date.formatToDMY() === NOW.formatToDMY())
        divToDoDay.divElement.classList.add('current_day');
    divDate.renderAppend(divToDoDay.divElement);
    divDate.divElement.append(labelDate);
    divTasks.renderAppend(divToDoDay.divElement);
    return divToDoDay;
}

function initDateRange(dateFrom, dateTo) {
    let rangeOfDates = dateInRange(dateFrom, dateTo);
    for (let item of rangeOfDates) {
        toDoListDiv.append(createToDoDay(item).divElement)
        createTask(item.formatToDMY());
    }
}


function createDivForExistTask(date, item) {
    let tasksDiv = document.getElementById(date).getElementsByClassName('tasks')[0],
        taskDiv = new Div(item['id'], 'task'),
        taskValueDiv = new Div('', 'value_task'),
        task = new InputElement('text', '', item['taskName'], '', '', false, '');
    taskDiv.renderAppend(tasksDiv);
    if (item['taskName'].length > MIN_LENGTH_FOR_TEXT_AREA) {
        isMatchedValueForTextArea(task.inputElement.value, taskDiv.divElement);
    }
    taskValueDiv.renderAppend(taskDiv.divElement);
    task.renderAppend(taskValueDiv.divElement);
    task.inputElement.disabled = true;
    if (item['done'] === 'checked')
        task.inputElement.classList.add('is_done');
    changeDoneStatusOnClick(taskValueDiv.divElement, task.inputElement, taskDiv.divElement);
    createAlternationDiv(taskDiv.divElement);
}

function createAlternationDiv(taskDiv) {
    let alternationDiv = new Div('', 'alternation_task', 'hidden'),
        deleteImage = document.createElement('img'),
        alternateImage = document.createElement('img'),
        divForDeleteImage = new Div('', 'delete_image'),
        divForAlternateImage = new Div('', 'alternate_image');
    alternationDiv.renderAppend(taskDiv);
    divForDeleteImage.renderAppend(taskDiv);
    divForAlternateImage.renderAppend(taskDiv);
    deleteImage.src = '/fileLoader?fileName=recycle&extension=png&folder=img';
    alternateImage.src = '/fileLoader?fileName=pencil&extension=png&folder=img';
    divForDeleteImage.divElement.addEventListener('click', () => {
        taskDiv.remove();
        deleteTask(taskDiv.id);
    });
    divForAlternateImage.divElement.addEventListener('click', () => {
        let value_task = taskDiv.getElementsByClassName('value_task')[0],
            input = value_task.getElementsByTagName('input')[0],
            divText = taskDiv.getElementsByClassName("pop_up_task")[0];
        if (divText !== undefined)
            divText.classList.remove("pop_up_task");

        input.disabled = false;
        input.focus();
        input.onblur = () => {
            whenAlternationIsEnd();
        };
        input.addEventListener('keydown', (event) => {
            if (event.key === "Enter") {
                whenAlternationIsEnd();
            }
        });

        function whenAlternationIsEnd() {
            if (input.value.length === 0) {
                taskDiv.remove();
                deleteTask(taskDiv.id);
            } else {
                input.disabled = true;
                if (input.value.length < MIN_LENGTH_FOR_TEXT_AREA) {
                    if (divText !== undefined)
                        divText.remove();
                } else {
                    if (divText !== undefined) {
                        divText.innerText = input.value;
                        divText.classList.add("pop_up_task");
                    } else {
                        isMatchedValueForTextArea(input.value, taskDiv);
                    }
                }
                updateTask(taskDiv.id, input.value, input.classList.contains('is_done'));
            }
        }
    });
    divForAlternateImage.divElement.append(alternateImage);
    divForDeleteImage.divElement.append(deleteImage);
    alternationDiv.divElement.append(divForDeleteImage.divElement);
    alternationDiv.divElement.append(divForAlternateImage.divElement);
    taskDiv.append(alternationDiv.divElement);
}

function changeDoneStatusOnClick(taskValueDiv, inputElement, taskDiv) {
    taskValueDiv.addEventListener('click', () => {
        if (inputElement.value.length !== 0) {
            updateTask(taskDiv.id, inputElement.value, inputElement.classList.toggle('is_done'));
        }
    });
}

function createDivForTask(date) {
    let tasksDiv = document.getElementById(date).getElementsByClassName('tasks')[0],
        taskDiv = new Div('', 'task'),
        taskValueDiv = new Div('', 'value_task'),
        task = new InputElement('text', '', '', '', '', false, '');
    taskDiv.renderAppend(tasksDiv);
    taskValueDiv.renderAppend(taskDiv.divElement);
    task.renderAppend(taskValueDiv.divElement);
    changeDoneStatusOnClick(taskValueDiv.divElement, task.inputElement, taskDiv.divElement);
    task.inputElement.onblur = () => {
        isMatchedValueForTextArea(task.inputElement.value, taskDiv.divElement);
        save();
    };

    task.inputElement.addEventListener('keydown', saveWhenPressEnter);
    task.inputElement.focus();

    function save() {
        if (task.inputElement.value.length !== 0) {
            task.inputElement.disabled = true;
            saveTask(date, task.inputElement, taskDiv.divElement);
            task.inputElement.removeEventListener('keydown', saveWhenPressEnter);
            task.inputElement.onblur = () => {
            };
        }
    }

    function saveWhenPressEnter() {
        return (event) => {
            if (event.key === "Enter") {
                isMatchedValueForTextArea(task.inputElement.value, taskDiv.divElement);
                save();
            }
        };
    }
}

function isMatchedValueForTextArea(value, taskDiv) {
    if (value.length > MIN_LENGTH_FOR_TEXT_AREA && value.length < 512) {
        let divAreaElem = new Div("", "hidden", "pop_up_task");
        divAreaElem.renderAppend(taskDiv);
        divAreaElem.divElement.innerText = value;
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

const calendar = document.getElementById("date");

function changeMoveableRange(from, to) {
    moveableFrom = new Date(from);
    moveableTo = new Date(to);
    moveableFrom.subtractDays(RANGE_VALUE);
    moveableTo.addDays(RANGE_VALUE);
}

function changeCurrentRange(from, to) {
    currentFrom = new Date(from);
    currentTo = new Date(to);
}

function hideAllToDoDays() {
    const allToDoDays = toDoListDiv.getElementsByClassName("to_do_day");
    for (let toDoItem of allToDoDays) {
        toDoItem.classList.add("hidden");
    }
}

calendar.addEventListener("input", (e) => {
    const from = new Date(e.target.value),
        to = new Date(e.target.value);
    to.addDays(RANGE_VALUE / 2);
    let rangeNewVisibleDates = dateInRange(from, to);
    changeMoveableRange(from, to);
    let rangeMoveableDates = dateInRange(moveableFrom, moveableTo);
    hideAllToDoDays();
    let tempCursorDateFrom = new Date(moveableFrom);
    let tempCursorDateTo = new Date(moveableTo);
    let isNeedLoadTask = true;
    for (let date of rangeMoveableDates) {
        if (allTasks.hasOwnProperty(tempCursorDateFrom.formatToDMY()))
            tempCursorDateFrom.addDays(1);
        if (allTasks.hasOwnProperty(tempCursorDateTo.formatToDMY()))
            tempCursorDateTo.subtractDays(1);
        if (tempCursorDateFrom.formatToDMY() === tempCursorDateTo.formatToDMY()) {
            isNeedLoadTask = false;
            break;
        }
    }
    if (isNeedLoadTask) {
        getTasks(tempCursorDateFrom.toDateString(), tempCursorDateTo.toDateString()).then(() => {
            generateToDoDays();
            changeCurrentRange(rangeNewVisibleDates[0], rangeNewVisibleDates[rangeNewVisibleDates.length - 1]);
        });
    } else {
        generateToDoDays();
        changeCurrentRange(rangeNewVisibleDates[0], rangeNewVisibleDates[rangeNewVisibleDates.length - 1]);
    }

    function generateToDoDays() {
        let appendableElem = null;
        for (let date of rangeNewVisibleDates) {
            let existedDate = document.getElementById(date.formatToDMY());
            if (existedDate === null) {
                if (appendableElem === null) {
                    appendableElem = createToDoDay(date).divElement;
                    document.getElementById(currentTo.formatToDMY());
                    if (date > currentTo) {
                        document.getElementById(currentTo.formatToDMY()).after(appendableElem);
                    } else if (date < currentFrom) {
                        document.getElementById(currentFrom.formatToDMY()).before(appendableElem);
                    }
                } else {
                    const newElem = createToDoDay(date).divElement
                    appendableElem.after(newElem);
                    appendableElem = newElem;
                }
                createTask(date.formatToDMY());
            } else {
                appendableElem = existedDate;
                existedDate.classList.remove('hidden');
            }
        }
    }
});

const homeBtn = document.getElementById("home_btn");
homeBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const newFrom = new Date(NOW),
        newTo = new Date(NOW);
    newTo.addDays(RANGE_VALUE/2);
    changeCurrentRange(newFrom,new Date(newTo));
    changeMoveableRange(newFrom,newTo);
    hideAllToDoDays();
    for(let date of dateInRange(newFrom,newTo)){
        document.getElementById(date.formatToDMY()).classList.remove("hidden");
    }
});

getTasks(moveableFrom.toDateString(), moveableTo.toDateString()).then(() => {
    initDateRange(currentFrom, currentTo);
});
