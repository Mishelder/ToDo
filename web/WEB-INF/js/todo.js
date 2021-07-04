const now = new Date();
const RANGE_VALUE = 8;
const moveLeft = document.getElementById("move_left");
const moveRight = document.getElementById("move_right");
let moveableFrom = new Date();
let moveableTo = new Date();
let currentFrom = new Date();
let currentTo = new Date();
moveableFrom.setDate(moveableFrom.getDate() - RANGE_VALUE);
moveableTo.setDate(moveableTo.getDate() + RANGE_VALUE);
currentTo.setDate(currentTo.getDate() + RANGE_VALUE/2);

const allTasks = {}

function getTasks(from,to){
    fetch('/fetch', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({'from' : from, 'to' : to}),
    }).then(response => response.text())
        .then(text => Object.assign(allTasks,JSON.parse(text)));
}

getTasks(moveableFrom.toDateString(),moveableTo.toDateString());

moveLeft.addEventListener("click", () => {
    currentFrom.setDate(currentFrom.getDate()-1);
    currentTo.setDate(currentTo.getDate()-1);
    if(moveableFrom.getDate() === currentFrom.getDate()){
        moveableFrom.setDate(moveableFrom.getDate() - 1)
        let to = new Date(moveableFrom);
        moveableFrom.setDate(moveableFrom.getDate() - RANGE_VALUE/2);
        let from = new Date(moveableFrom);
        getTasks(from.toDateString(),to.toDateString());
    }
});

moveRight.addEventListener("click", () => {
    currentFrom.setDate(currentFrom.getDate()+1);
    currentTo.setDate(currentTo.getDate()+1);
    if(moveableTo.getDate() === currentTo.getDate()){
        moveableTo.setDate(moveableTo.getDate() + 1)
        let from = new Date(moveableTo);
        moveableTo.setDate(moveableTo.getDate() + RANGE_VALUE/2);
        let to = new Date(moveableTo);
        getTasks(from.toDateString(),to.toDateString());
    }
});
