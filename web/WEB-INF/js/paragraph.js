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