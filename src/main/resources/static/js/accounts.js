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
function onNameEditButtonClick(){
    if (document.getElementById('edit-name-first').className === "form-control-hide"
        || document.getElementById('edit-name-first').className === "form-control-hide success"
        || document.getElementById('edit-name-first').className === "form-control-hide error") {
        document.getElementById('edit-name-label').className = "show";
        document.getElementById('edit-name-first').className = "form-control-show";
        document.getElementById('edit-name-last').className = "form-control-show";
        document.getElementById('edit-name-button').className = "form-control-show";
        document.getElementById('name-edit-btn').innerText = "Cancel";
    } else if (document.getElementById('edit-name-first').className === "form-control-show"
        || document.getElementById('edit-name-first').className === "form-control-show success"
        || document.getElementById('edit-name-first').className === "form-control-show error" ){
        document.getElementById('edit-name-label').className = "hide";
        document.getElementById('edit-name-first').className = "form-control-hide";
        document.getElementById('edit-name-first').value = "";
        document.getElementById('edit-name-last').className = "form-control-hide";
        document.getElementById('edit-name-last').value = "";
        document.getElementById('edit-name-button').className = "form-control-hide";
        document.getElementById('name-edit-btn').innerText = "Edit";
    }
}

function onEmailEditButtonClick(){
    if (document.getElementById('edit-email').className === "form-control-hide"
        || document.getElementById('edit-email').className === "form-control-hide success"
        || document.getElementById('edit-email').className === "form-control-hide error") {
        document.getElementById('edit-email-label').className = "form-control-show";
        document.getElementById('edit-email').className = "form-control-show";
        document.getElementById('edit-email-button').className = "form-control-show";
        document.getElementById('email-edit-btn').innerText = "Cancel";
    } else if (document.getElementById('edit-email').className === "form-control-show"
        || document.getElementById('edit-email').className === "form-control-show success"
        || document.getElementById('edit-email').className === "form-control-show error") {
        document.getElementById('edit-email-label').className = "form-control-hide";
        document.getElementById('edit-email').className = "form-control-hide";
        document.getElementById('edit-email-button').className = "form-control-hide";
        document.getElementById('email-edit-btn').innerText = "Edit";
    }
}

function onPhoneEditButtonClick(){
	if (document.getElementById('edit-phone').className === "form-control-hide"
        || document.getElementById('edit-phone').className === "form-control-hide success"
        || document.getElementById('edit-phone').className === "form-control-hide error") {
		document.getElementById('edit-phone').className = "form-control-show";
  		document.getElementById('edit-phone-button').className = "form-control-show";
        document.getElementById('phone-edit-btn').innerText = "Cancel";
	} else if (document.getElementById('edit-phone').className === "form-control-show"
        || document.getElementById('edit-phone').className === "form-control-show success"
        || document.getElementById('edit-phone').className === "form-control-show error") {
		document.getElementById('edit-phone').className = "form-control-hide";
  		document.getElementById('edit-phone-button').className = "form-control-hide";
        document.getElementById('phone-edit-btn').innerText = "Edit";
	}
}

function onPasswordEditButtonClick(){
	if (document.getElementById('edit-password').className === "form-control-hide" 
		|| document.getElementById('edit-password').className === "form-control-hide success"
		|| document.getElementById('edit-password').className === "form-control-hide error") {
        document.getElementById('edit-password-label').className = "show";
		document.getElementById('edit-password').className = "form-control-show";
		document.getElementById('edit-repeat-password').className = "form-control-show";
  		document.getElementById('edit-password-button').className = "form-control-show";
        document.getElementById('password-edit-btn').innerText = "Cancel";
	} else if (document.getElementById('edit-password').className === "form-control-show"
		|| document.getElementById('edit-password').className === "form-control-show success"
		|| document.getElementById('edit-password').className === "form-control-show error") {
        document.getElementById('edit-password-label').className = "hide";
		document.getElementById('edit-password').className = "form-control-hide";
		document.getElementById('edit-repeat-password').className = "form-control-hide";
  		document.getElementById('edit-password-button').className = "form-control-hide";
        document.getElementById('password-edit-btn').innerText = "Edit";
	}
}

function onAddressEditButtonClick() {
    if (document.getElementById('edit-address-street').className === "form-control-hide"
		|| document.getElementById('edit-address-street').className === "form-control-hide success"
		|| document.getElementById('edit-address-street').className === "form-control-hide error") {
        document.getElementById('edit-address-label').className = "show";
        document.getElementById('edit-address-street').className = "form-control-show";
        document.getElementById('edit-address-apt').className = "form-control-show";
        document.getElementById('edit-address-city').className = "form-control-show";
        document.getElementById('edit-address-state').className = "form-control-show";
        document.getElementById('edit-address-zip').className = "form-control-show";
        document.getElementById('edit-address-btn').className = "show";
        document.getElementById('address-edit-btn').innerText = "Cancel";
    } else if (document.getElementById('edit-address-street').className === "form-control-show"
		|| document.getElementById('edit-address-street').className === "form-control-show success"
		|| document.getElementById('edit-address-street').className === "form-control-show error") {
        document.getElementById('edit-address-label').className = "hide";
        document.getElementById('edit-address-street').className = "form-control-hide";
        document.getElementById('edit-address-apt').className = "form-control-hide";
        document.getElementById('edit-address-city').className = "form-control-hide";
        document.getElementById('edit-address-state').className = "form-control-hide";
        document.getElementById('edit-address-zip').className = "form-control-hide";
        document.getElementById('edit-address-btn').className = "hide";
        document.getElementById('address-edit-btn').innerText = "Edit";
    }
}

/*Login Secutity tab Validation for Edit Textboxes*/
const firstName = document.querySelector('.edit-name-first');
const lastName = document.querySelector('.edit-name-last');

function checkNameInputs() {

	const firstNameValue = firstName.value.trim();
	const lastNameValue = lastName.value.trim();

	if(firstNameValue === '') {
		setErrorFor(firstName, 'Please enter your first name');
        return false;
     } else {
		setSuccessFor(firstName);
	}
	
	if(lastNameValue === '') {
		setErrorFor(lastName, 'Please enter your last name');
        return false;
	} else{
		setSuccessFor(lastName);
	}
    return true;
}

const email = document.querySelector('.edit-email');

function checkEmailInputs() {
    const emailValue = email.value.trim();

    if(emailValue === '') {
		setErrorFor(email, 'Please enter your email');
        return false;
    } else {
		setSuccessFor(email);
	}
    return true;
}

const phone = document.querySelector('.edit-phone');

function checkPhoneInputs() {
    const phoneValue = phone.value.trim();

    if(phoneValue === '') {
		setErrorFor(phone, 'Please enter your phone number');
        return false;
    } else {
		setSuccessFor(phone);
	}
    return true;
}

const password = document.querySelector('.edit-password');
const password2 = document.querySelector('.edit-repeat-password');

function checkPasswordInputs() {

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

const street = document.querySelector('.edit-address-street');
const apt = document.querySelector('.edit-address-apt');
const city = document.querySelector('.edit-address-city');
const state = document.querySelector('.edit-address-state');
const zip = document.querySelector('.edit-address-zip');

function checkAddressInputs() {

	const streetValue = street.value.trim();
	const aptValue = apt.value.trim();
	const cityValue = city.value.trim();
	const stateValue = state.value.trim();
	const zipValue = zip.value.trim();

	if(streetValue === '') {
		setErrorFor(street, 'Please enter your street');
        return false;
	} else {
		setSuccessFor(street);
	}
	
	if(aptValue === '') {
		setErrorFor(apt, 'Please enter your apt');
        return false;
	} else{
		setSuccessFor(apt);
	}
	
	if(cityValue === '') {
		setErrorFor(city, 'Please enter your city');
        return false;
	} else{
		setSuccessFor(city);
	}
	
	if(stateValue === '') {
		setErrorFor(state, 'Please enter your state');
        return false;
	} else{
		setSuccessFor(state);
	}
	
	if(zipValue === '') {
		setErrorFor(zip, 'Please enter your zip');
        return false;
	} else{
		setSuccessFor(zip);
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

/* Card Edit Form Function */
function onCardEditButtonClick() {
    if (document.getElementById("card-edit-header").className === "form-control-hide") {
        document.getElementById("card-edit-header").className = "form-control-show";
        document.getElementById("edit-card-name").className = "form-control-show";
        document.getElementById("edit-card-num").className = "form-control-show";
        document.getElementById("card-edit-expMonth-CVV").className = "form-control-show";
    } else if (document.getElementById("card-edit-header").className === "form-control-show") {
        document.getElementById("card-edit-header").className = "form-control-hide";
        document.getElementById("edit-card-name").className = "form-control-hide";
        document.getElementById("edit-card-num").className = "form-control-hide";
        document.getElementById("card-edit-expMonth-CVV").className = "form-control-hide";
    }
}