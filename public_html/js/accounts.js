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

    if (evt.currentTarget.id == "tablinks-LoginSec") {
        if (document.getElementById('tablinks-Orders-Active')) {
            document.getElementById('tablinks-Orders-Active').id = 'tablinks-Orders';
        } else if (document.getElementById('tablinks-PaymentOpt-Active')) {
            document.getElementById('tablinks-PaymentOpt-Active').id = 'tablinks-PaymentOpt';
        } else if (document.getElementById('tablinks-WishList-Active')) {
            document.getElementById('tablinks-WishList-Active').id = 'tablinks-WishList';
        }
        document.getElementById('tablinks-LoginSec').id = 'tablinks-LoginSec-Active';

    } else if (evt.currentTarget.id == "tablinks-Orders") {
        if (document.getElementById('tablinks-LoginSec-Active')) {
            document.getElementById('tablinks-LoginSec-Active').id = 'tablinks-LoginSec';
        } else if (document.getElementById('tablinks-PaymentOpt-Active')) {
            document.getElementById('tablinks-PaymentOpt-Active').id = 'tablinks-PaymentOpt';
        } else if (document.getElementById('tablinks-WishList-Active')) {
            document.getElementById('tablinks-WishList-Active').id = 'tablinks-WishList';
        }
        document.getElementById('tablinks-Orders').id = 'tablinks-Orders-Active';

    } else if (evt.currentTarget.id == "tablinks-PaymentOpt") {
        if (document.getElementById('tablinks-LoginSec-Active')) {
            document.getElementById('tablinks-LoginSec-Active').id = 'tablinks-LoginSec';
        } else if (document.getElementById('tablinks-Orders-Active')) {
            document.getElementById('tablinks-Orders-Active').id = 'tablinks-Orders';
        } else if (document.getElementById('tablinks-WishList-Active')) {
            document.getElementById('tablinks-WishList-Active').id = 'tablinks-WishList';
        }
        document.getElementById('tablinks-PaymentOpt').id = 'tablinks-PaymentOpt-Active';

    } else if (evt.currentTarget.id == "tablinks-WishList") {
        if (document.getElementById('tablinks-LoginSec-Active')) {
            document.getElementById('tablinks-LoginSec-Active').id = 'tablinks-LoginSec';
        } else if (document.getElementById('tablinks-Orders-Active')) {
            document.getElementById('tablinks-Orders-Active').id = 'tablinks-Orders';
        } else if (document.getElementById('tablinks-PaymentOpt-Active')) {
            document.getElementById('tablinks-PaymentOpt-Active').id = 'tablinks-PaymentOpt';
        }
        document.getElementById('tablinks-WishList').id = 'tablinks-WishList-Active';
    }
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