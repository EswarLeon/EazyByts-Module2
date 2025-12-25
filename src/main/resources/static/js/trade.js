const params = new URLSearchParams(window.location.search);
const username = params.get("username") || "raj";

const message = document.getElementById("message");

// Load stocks into dropdown
fetch("http://localhost:9090/api/stocks")
    .then(res => res.json())
    .then(stocks => {
        let options = "";
        stocks.forEach(s => {
            options += `<option value="${s.symbol}">
                            ${s.symbol} - ₹${s.price}
                        </option>`;
        });
        document.getElementById("stockSymbol").innerHTML = options;
    });

// BUY STOCK
function buyStock() {
    trade("buy");
}

// SELL STOCK
function sellStock() {
    trade("sell");
}

// COMMON TRADE FUNCTION
function trade(type) {
    const stockSymbol = document.getElementById("stockSymbol").value;
    const quantity = document.getElementById("quantity").value;

    if (!quantity || quantity <= 0) {
        message.innerText = "Enter valid quantity";
        return;
    }

    fetch(`http://localhost:9090/api/trade/${type}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: username,
            stockSymbol: stockSymbol,
            quantity: quantity
        })
    })
    .then(res => res.text())
    .then(data => {
        message.innerText = data;
    });
}
