/*Main Tab Pane*/
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