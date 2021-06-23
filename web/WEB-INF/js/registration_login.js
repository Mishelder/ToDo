'use strict'

class Form {
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
}

class Input {
    constructor(typeValue, id, value,
                nameValue, placeHolder, ...classList) {
        this.typeValue = typeValue;
        this.id = id;
        this.classList = classList.length !== 0 ? classList : ['fadeIn'];
        this.value = value;
        this.nameValue = nameValue;
        this.placeHolder = placeHolder;
    };

    init(){
        const input = document.createElement('input');
        input.type = this.typeValue;
        input.id = this.id;
        this.classList.forEach((elem) => {
            input.classList.add(elem);
        });
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

function changeActiveHeader() {
    headerSingIn.addEventListener('click', () => {
        changeActiveStatus(headerSignUp, headerSingIn);
    });

    headerSignUp.addEventListener('click', () => {
        changeActiveStatus(headerSingIn, headerSignUp)
    });

    function changeActiveStatus(activeElement, nonActiveElement) {
        const listActive = activeElement.classList,
             listNonActive = nonActiveElement.classList;
        if (!listNonActive.contains('active')) {
            if(nonActiveElement.id === 'signUp'){
                form.setAttribute('action','/registration')
                createInput(
                    "email",
                    "email",
                    "",
                    "email",
                    "email",
                    "fadeIn",
                    "second"
                ).renderPrepend(form);
            }else{
                document.getElementById("email").remove();
                form.setAttribute('action','/login')
            }
            listNonActive.add('active');
            listNonActive.remove('inactive', 'underlineHover');
            listActive.add('inactive', 'underlineHover')
            listActive.remove('active')
        }
    }
}

function createForm(actionUrl, method, id) {
    const form = new Form(actionUrl, method, id);
    form.render(headerSignUp);
}

function createInput(typeValue, id, value, nameValue, placeHolder, ...classList) {
    return  new Input(typeValue, id, value, nameValue, placeHolder, classList);
}

changeActiveHeader();

createInput(
    "text",
    "login",
    "",
    "login",
    "login",
    "fadeIn",
    "second").renderAppend(form);

createInput(
    "password",
    "password",
    "",
    "password",
    "password",
    "fadeIn",
    "third").renderAppend(form);

createInput(
    "submit",
    "",
    "Log In",
    "",
    "",
    "fadeIn",
    "fourth").renderAppend(form);


