Android.showList()

function list(data) {
    var listCardElement = document.getElementById("listCards");

    listCardElement.innerHTML = "";

    for (var i = 0; i < data.length; i++) {
        var carta = data[i];

        var listItem = document.createElement("li");
        listItem.classList.add("collection-item", "avatar")
        listItem.style.paddingLeft = "1.2rem"


        var divItem = document.createElement("div")
        divItem.classList.add("valign-wrapper")

        var imgItem = document.createElement("img")
        imgItem.src = `images/${carta.image}.png`
        imgItem.classList.add("responsive-img")
        imgItem.style.width = "50px"
        imgItem.style.height = "70px"


        var spanItem = document.createElement("span")
        spanItem.classList.add("title")
        spanItem.style.marginLeft = "1.5rem"
        spanItem.textContent = carta.name

        divItem.appendChild(imgItem)
        divItem.appendChild(spanItem)

        listItem.appendChild(divItem)
        listCardElement.appendChild(listItem)
    }
}
