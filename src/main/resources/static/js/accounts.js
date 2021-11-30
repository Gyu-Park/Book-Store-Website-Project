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

/*Login Security Edit*/

function onPhoneEditButtonClick(){
	if (document.getElementById('edit-phone').className === "hide") {
		document.getElementById('edit-phone').className = "show";
  		document.getElementById('edit-phone-button').className = "show";
	} else if (document.getElementById('edit-phone').className === "show") {
		document.getElementById('edit-phone').className = "hide";
  		document.getElementById('edit-phone-button').className = "hide";
	}
}

function onPasswordEditButtonClick(){
	if (document.getElementById('edit-password').className === "form-control-hide" 
		|| document.getElementById('edit-password').className === "form-control-hide success"
		|| document.getElementById('edit-password').className === "form-control-hide error") {
		document.getElementById('edit-password').className = "form-control-show";
		document.getElementById('edit-repeat-password').className = "form-control-show";
  		document.getElementById('edit-password-button').className = "form-control-show";
	} else if (document.getElementById('edit-password').className === "form-control-show"
		|| document.getElementById('edit-password').className === "form-control-show success"
		|| document.getElementById('edit-password').className === "form-control-show error") {
		document.getElementById('edit-password').className = "form-control-hide";
		document.getElementById('edit-repeat-password').className = "form-control-hide";
  		document.getElementById('edit-password-button').className = "form-control-hide";
	}
}

/*Login Secutity tab - Password Validation*/
const password = document.querySelector('.edit-password');
const password2 = document.querySelector('.edit-repeat-password');

function checkInputs() {

	const passwordValue = password.value.trim();
	const password2Value = password2.value.trim();
	
	if(passwordValue === '') {
		setErrorFor(password, 'Password cannot be blank');
        return false;
	} else if(passwordValue.length < 8) {
        setErrorFor(password, 'Password must be at least 8 characters');
        return false;
    } else if(passwordValue.length > 20) {
        setErrorFor(password, 'Password must be less than 20 characters');
        return false;
    } else {
		setSuccessFor(password);
	}
	
	if(password2Value === '') {
		setErrorFor(password2, 'Password2 cannot be blank');
        return false;
	} else if(passwordValue !== password2Value) {
		setErrorFor(password2, 'Passwords does not match');
        return false;
	} else{
		setSuccessFor(password2);
	}
    return true;
}

function setErrorFor(input, message) {
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	formControl.className = 'form-control-show error';
	small.innerText = message;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control-show success';
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