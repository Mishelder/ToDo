class Div {
    constructor(id, ...style) {
        this.id = id;
        this.style = style;
    }

    init() {
        const div = document.createElement('div');
        div.id = this.id;
        this.style.forEach(item=>div.classList.add(item));
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