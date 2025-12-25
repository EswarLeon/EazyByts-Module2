function loadHistory() {

    fetch("http://localhost:9090/api/history", {
        method: "GET",
        credentials: "same-origin"
    })
    .then(res => res.json())
    .then(data => {

        let rows = "";

        data.forEach(h => {

            const color = h.type === "BUY" ? "green" : "red";

            rows += `
                <tr>
                    <td style="color:${color}">${h.type}</td>
                    <td>${h.stockSymbol}</td>
                    <td>${h.quantity}</td>
                    <td>₹${h.price}</td>
                    <td>${h.time.replace("T", " ")}</td>
                </tr>
            `;
        });

        document.getElementById("historyTable").innerHTML = rows;
    })
    .catch(err => {
        console.error("History load failed", err);
    });
}

loadHistory();
setInterval(loadHistory, 5000);
