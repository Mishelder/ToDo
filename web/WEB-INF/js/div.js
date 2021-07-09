class Div {
    constructor(id, ...style) {
        this.id = id;
        this.style = style === '' ? '' : style;
    }

    init() {
        const div = document.createElement('div');
        if (this.id !== '')
            div.id = this.id;
        if (this.style !== '')
            this.style.forEach(item => div.classList.add(item));
        return div;
    }

    renderAppend(parentElem) {
        let tempDiv = this.init()
        parentElem.append(tempDiv);
        return tempDiv;
    }

    renderPrepend(parentElem) {
        let tempDiv = this.init()
        parentElem.prepend(tempDiv);
        return tempDiv;
    }
}