
fetch("http://localhost:9090/api/auth/check", {
    method: "GET",
    credentials: "same-origin"
})
.then(res => res.json())
.then(isLoggedIn => {

    if (!isLoggedIn) {
        window.location.href = "login.html";
    }

})
.catch(() => {
    window.location.href = "login.html";
});
