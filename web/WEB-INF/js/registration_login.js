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
const login = createInput(
    "text",
    "login",
    "",
    "login",
    "login",
    "fadeIn",
    "second");
const password =createInput(
    "password",
    "password",
    "",
    "password",
    "password",
    "fadeIn",
    "third") ;
const email = createInput(
    "email",
    "email",
    "",
    "email",
    "email",
    "fadeIn",
    "second"
);
const submitInput = createInput(
    "submit",
    "submit",
    "Sign In",
    "",
    "",
    "fadeIn",
    "fourth");

login.renderAppend(form);
password.renderAppend(form);
submitInput.renderAppend(form);

const submitElement = document.getElementById('submit');

function showEmail(){
    form.setAttribute('action','/registration');
    email.renderPrepend(form);
    submitElement.value = 'Sign up';
}

function hideEmail(){
    document.getElementById("email").remove();
    form.setAttribute('action','/login');
    submitElement.value = 'Sign in';
}

function changeActiveStatusClass(listActive,listNonActive){
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
            if(nonActiveElement.id === 'signUp'){
                if(document.getElementById("email") == null)
                    showEmail();
            }else{
                if(!(document.getElementById("email") == null))
                    hideEmail();
            }
        changeActiveStatusClass(activeElement.classList,nonActiveElement.classList);
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

//Server

form.addEventListener('submit',(e)=>{
    e.preventDefault();
   const url = form.getAttribute('action');
   if(url === '/registration'){
       let data = {};
       new FormData(form).forEach((item,key)=>{
          data[key] = item;
       });
       fetch(url,{
           method: 'POST',
           headers:{
             'Content-type' : 'application/json'
           },
           body: JSON.stringify(data)
       }).then(response=>{
           if(response.status===403){
               console.log('all is good');
           }else{
               console.log('all is bad');
           }
       });
   }
});


