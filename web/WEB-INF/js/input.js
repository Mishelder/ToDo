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