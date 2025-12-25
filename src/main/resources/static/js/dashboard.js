// Load wallet balance (SESSION BASED)
fetch("http://localhost:9090/api/wallet/balance", {
    method: "GET",
    credentials: "same-origin"
})
.then(res => res.text())
.then(data => {
    document.getElementById("balance").innerText = data;
});

// Load live stocks
function loadStocks() {
    fetch("http://localhost:9090/api/stocks")
        .then(res => res.json())
        .then(stocks => {

            let rows = "";

            stocks.forEach(s => {
                rows += `
                    <tr>
                        <td>${s.symbol}</td>
                        <td>${s.name}</td>
                        <td>₹${s.price}</td>
                    </tr>
                `;
            });

            document.getElementById("stockTable").innerHTML = rows;
        });
}

loadStocks();
setInterval(loadStocks, 5000);
