'use strict'

/*class Form {
    constructor(actionUrl, method, id) {
        this.actionUrl = actionUrl;
        this.method = method;
        this.id = id;
    }

    render(parentElem) {
        const form = document.createElement('form');
        form.action = this.actionUrl;
        form.method = this.method;
        form.id = this.id;
        parentElem.parentNode.insertBefore(form, parentElem.nextSibling);
    }
}*/

class Paragraph {
    constructor(id, textContent, ...style) {
        this.id = id;
        this.textContent = textContent;
        this.style = style;
    }

    init() {
        const p = document.createElement('p');
        p.id = this.id;
        p.textContent = this.textContent;
        this.style.forEach(item => p.classList.add(item));
        return p;
    }

    renderAppend(parentElem) {
        parentElem.append(this.init());
    }

    renderPrepend(parentElem) {
        parentElem.prepend(this.init());
    }
}

class Input {
    constructor(typeValue, id, value,
                nameValue, placeHolder, ...style) {
        this.typeValue = typeValue;
        this.id = id;
        this.style = style[0];
        this.value = value;
        this.nameValue = nameValue;
        this.placeHolder = placeHolder;
    }

    init() {
        const input = document.createElement('input');
        input.type = this.typeValue;
        input.id = this.id;
        this.style.forEach(item=>input.classList.add(item));
        input.value = this.value;
        input.name = this.nameValue;
        input.placeholder = this.placeHolder;
        return input;
    }

    renderAppend(parentElem) {
        parentElem.append(this.init());
    }

    renderPrepend(parentElem) {
        parentElem.prepend(this.init());
    }
}

const headerSingIn = document.getElementById("signIn");
const headerSignUp = document.getElementById("signUp");
const form = document.getElementById("form");
const login = createInput("text","login","","login","login","fadeIn", "second");
const password = createInput('password', 'password', "", "password", 'password', 'fadeIn', 'third');
const email = createInput('email', 'email', "", 'email', 'email', 'fadeIn', 'first', 'hidden');
const submitInput = createInput('submit', 'submit', 'Sign In', "", "", 'fadeIn', 'fourth');
const loginError = new Paragraph('loginError', '', 'red', 'hidden');
const emailError = new Paragraph('emailError', '', 'red', 'hidden');
const passwordError = new Paragraph('passwordError', '', 'red', 'hidden');

email.renderAppend(form);
emailError.renderAppend(form);
login.renderAppend(form);
loginError.renderAppend(form);
password.renderAppend(form);
passwordError.renderAppend(form)
submitInput.renderAppend(form);

const submitElement = document.getElementById('submit');

function showEmail() {
    form.setAttribute('action', '/registration');
    document.getElementById('email').classList.remove('hidden');
    submitElement.value = 'Sign up';
}

function hideEmail() {
    form.setAttribute('action', '/login');
    document.getElementById('email').classList.add('hidden');
    submitElement.value = 'Sign in';
}

function changeActiveStatusClass(listActive, listNonActive) {
    listNonActive.add('active');
    listNonActive.remove('inactive', 'underlineHover');
    listActive.add('inactive', 'underlineHover');
    listActive.remove('active');
}

function changeActiveHeader() {
    headerSingIn.addEventListener('click', () => {
        changeActiveStatus(headerSignUp, headerSingIn);
    });

    headerSignUp.addEventListener('click', () => {
        changeActiveStatus(headerSingIn, headerSignUp);
    });

    function changeActiveStatus(activeElement, nonActiveElement) {
        if (nonActiveElement.id === 'signUp') {
                showEmail();
        } else {
                hideEmail();
        }
        changeActiveStatusClass(activeElement.classList, nonActiveElement.classList);
    }
}

function createInput(typeValue, id, value, nameValue, placeHolder, ...classList) {
    return new Input(typeValue, id, value, nameValue, placeHolder, classList);
}

changeActiveHeader();

//Server

function setErrorData(data) {
    for (let key in data) {
        if (key === 'login') {
            show('loginError', data[key]);
        } else if (key === 'email') {
            show('emailError', data[key]);
        } else {
            show('passwordError', data[key]);
        }
    }

    function show(selector, textContent) {
        const login = document.getElementById(selector);
        login.classList.remove('hidden');
        login.textContent = textContent;
    }
}

form.addEventListener('submit', (e) => {
    e.preventDefault();
    const url = form.getAttribute('action');
    if (url === '/registration') {
        let data = {};
        new FormData(form).forEach((item, key) => {
            data[key] = item;
        });
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response =>
            response.text()
        ).then(body => {
            if (body === '') {
                hideEmail();
                changeActiveStatusClass(headerSignUp.classList, headerSingIn.classList);
            } else {
                setErrorData(JSON.parse(body));
            }
        });
    }else{
        let data = {};
        new FormData(form).forEach((item, key) => {
            if(!(key==='email'))
            data[key] = item;
        });
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            redirect: "follow",
            body: JSON.stringify(data)
        }).then(response => {
            if(response.redirected)
                document.location = response.url;
            return response.text();
        }).then(body => setErrorData(JSON.parse(body)));
    }
});

document.addEventListener('click',()=>{
    const emailError = document.getElementById("emailError");
    const loginError = document.getElementById("loginError");
    const passwordError = document.getElementById("passwordError");
    isParagraphHidden(emailError);
    isParagraphHidden(loginError);
    isParagraphHidden(passwordError);

    function isParagraphHidden(element){
        if(!element.classList.contains('hidden')){
            element.classList.add('hidden');
            element.textContent ='';
        }
    }
});


