
let key = localStorage.getItem('uuid') || null;

function loginCheck() {
    if (key == null) alert("Login first..");
    
    window.location.href = "login.html";
}


function walletService() {
    loginCheck();
}

function fundService() {
    loginCheck();
}

function accountService() {
    loginCheck();
}

function billService() {
    loginCheck();
}

function beneficiaryService() {
    loginCheck();
}

function transactionService() {
    loginCheck();
}
