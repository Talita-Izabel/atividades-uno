function raffleJS(card, urlImage) {
    var img = document.getElementById("image");
    var text = document.getElementById("text");

    img.src = `images/${urlImage}.png`;
    text.textContent = card;
}

