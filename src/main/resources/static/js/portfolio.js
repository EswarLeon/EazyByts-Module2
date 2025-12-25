const params = new URLSearchParams(window.location.search);
const username = params.get("username") || "raj";

function loadPortfolio() {

    fetch("http://localhost:9090/api/portfolio", {
        method: "GET",
        credentials: "same-origin"
    })
        .then(res => res.json())
        .then(data => {

            let rows = "";

            data.forEach(p => {

                const color = p.profitOrLoss >= 0 ? "green" : "red";

                rows += `
                    <tr>
                        <td>${p.stockSymbol}</td>
                        <td>${p.quantity}</td>
                        <td>₹${p.buyPrice}</td>
                        <td>₹${p.currentPrice}</td>
                        <td style="color:${color}">
                            ₹${p.profitOrLoss.toFixed(2)}
                        </td>
                    </tr>
                `;
            });

            document.getElementById("portfolioTable").innerHTML = rows;
        })
        .catch(err => {
            console.error("Portfolio load failed", err);
        });
}

loadPortfolio();
setInterval(loadPortfolio, 5000);

