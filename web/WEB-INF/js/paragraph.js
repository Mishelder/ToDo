class Paragraph {

    #pElement;

    constructor(id, textContent, ...style) {
        this.id = id;
        this.textContent = textContent;
        this.style = style;
        this.init();
    }

    init() {
        const p = document.createElement('p');
        p.id = this.id;
        p.textContent = this.textContent;
        this.style.forEach(item => p.classList.add(item));
        this.#pElement = p;
    }

    renderAppend(parentElem) {
        parentElem.append(this.#pElement);
    }

    renderPrepend(parentElem) {
        parentElem.prepend(this.#pElement);
    }

    get pElement(){
        return this.#pElement;
    }
}