// ===============================
// AUTH GUARD - CHECK LOGIN STATUS
// ===============================
fetch("http://localhost:9090/api/auth/check", {
    method: "GET",
    credentials: "same-origin"
})
.then(res => res.json())
.then(isLoggedIn => {

    if (!isLoggedIn) {
        // ❌ Not logged in → redirect
        window.location.href = "login.html";
    }

})
.catch(() => {
    // On error, be safe → redirect
    window.location.href = "login.html";
});
