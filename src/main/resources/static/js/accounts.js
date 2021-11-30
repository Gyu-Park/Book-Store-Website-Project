/*Main Tab Pane*/

let allTabs = document.querySelectorAll(".tablinks");
allTabs.forEach(function(tab) {

    tab.addEventListener('click', () => {
        allTabs.forEach(b => b.removeAttribute("id"));
        tab.setAttribute('id', 'active');
    });

});

function openForm(evt, formName) {
    var i, tabContent, tablinks;

    tabContent = document.getElementsByClassName("tabContent");
    for (i = 0; i < tabContent.length; i++) {
        tabContent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(formName).style.display = "block";
    evt.currentTarget.className += " active";
}

/*Payment Tab Pane*/
function openPaymentForm(evt, formName) {
    var x, paymentTabContent, paymentTablinks;

    paymentTabContent = document.getElementsByClassName("paymentTabContent");
    for (x = 0; x < paymentTabContent.length; x++) {
        paymentTabContent[x].style.display = "none";
    }

    paymentTablinks = document.getElementsByClassName("paymentTablinks");
    for (x = 0; x < paymentTablinks.length; x++) {
        paymentTablinks[x].className = paymentTablinks[x].className.replace(" active", "");
    }

    document.getElementById(formName).style.display = "block";
    evt.currentTarget.className += " active";
}

/*Card Section*/
document.querySelector('#account-payment-cc-num').oninput = () => {
    document.querySelector('#cardNumber').innerText
        = document.querySelector('#account-payment-cc-num').value;
}

document.querySelector('#account-payment-cc-name').oninput = () => {
    document.querySelector('#holderName').innerText
        = document.querySelector('#account-payment-cc-name').value;
}

document.querySelector('#account-payment-cc-exp-month').oninput = () => {
    document.querySelector('#expMonth').innerText
        = document.querySelector('#account-payment-cc-exp-month').value;
}

document.querySelector('#account-payment-cc-exp-year').oninput = () => {
    document.querySelector('#expYear').innerText
        = document.querySelector('#account-payment-cc-exp-year').value;
}

document.querySelector('#account-payment-cc-cvv').oninput = () => {
    document.querySelector('.cvv-box').innerText
        = document.querySelector('#account-payment-cc-cvv').value;
}

var cvvTextField = document.getElementById("account-payment-cc-cvv");
cvvTextField.addEventListener("focus", focusTrueFunction, true);
cvvTextField.addEventListener("blur", blurFalseFunction, true);

function focusTrueFunction() {
    document.querySelector('#addCardFront').style.transform = 'perspective(1000px) rotateY(-180deg)';
    document.querySelector('#addCardBack').style.transform = 'perspective(1000px) rotateY(0deg)';
}

function blurFalseFunction() {
    document.querySelector('#addCardFront').style.transform = '';
    document.querySelector('#addCardBack').style.transform = '';
}