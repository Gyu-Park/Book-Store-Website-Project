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
function on1CardEditButtonClick() {
    if (document.getElementById("card-edit-header1").className === "form-control-hide") {
        document.getElementById("card-edit-header1").className = "form-control-show";
        document.getElementById("edit-card-name1").className = "form-control-show";
        document.getElementById("edit-card-num1").className = "form-control-show";
        document.getElementById("card-edit-expMonth1").className = "form-control-show";
		document.getElementById("card-edit-expYear1").className = "form-control-show";
		document.getElementById("card-edit-Cvv1").className = "form-control-show";
		document.getElementById("edit-card-button1").className = "form-control-show";
    } else if (document.getElementById("card-edit-header1").className === "form-control-show") {
        document.getElementById("card-edit-header1").className = "form-control-hide";
        document.getElementById("edit-card-name1").className = "form-control-hide";
        document.getElementById("edit-card-num1").className = "form-control-hide";
        document.getElementById("card-edit-expMonth1").className = "form-control-hide";
		document.getElementById("card-edit-expYear1").className = "form-control-hide";
		document.getElementById("card-edit-Cvv1").className = "form-control-hide";
		document.getElementById("edit-card-button1").className = "form-control-hide";
    }
}

function on2CardEditButtonClick() {
    if (document.getElementById("card-edit-header2").className === "form-control-hide") {
        document.getElementById("card-edit-header2").className = "form-control-show";
        document.getElementById("edit-card-name2").className = "form-control-show";
        document.getElementById("edit-card-num2").className = "form-control-show";
        document.getElementById("card-edit-expMonth2").className = "form-control-show";
		document.getElementById("card-edit-expYear2").className = "form-control-show";
		document.getElementById("card-edit-Cvv2").className = "form-control-show";
		document.getElementById("edit-card-button2").className = "form-control-show";
    } else if (document.getElementById("card-edit-header2").className === "form-control-show") {
        document.getElementById("card-edit-header2").className = "form-control-hide";
        document.getElementById("edit-card-name2").className = "form-control-hide";
        document.getElementById("edit-card-num2").className = "form-control-hide";
        document.getElementById("card-edit-expMonth2").className = "form-control-hide";
		document.getElementById("card-edit-expYear2").className = "form-control-hide";
		document.getElementById("card-edit-Cvv2").className = "form-control-hide";
		document.getElementById("edit-card-button2").className = "form-control-hide";
    }
}


function on3CardEditButtonClick() {
    if (document.getElementById("card-edit-header3").className === "form-control-hide") {
        document.getElementById("card-edit-header3").className = "form-control-show";
        document.getElementById("edit-card-name3").className = "form-control-show";
        document.getElementById("edit-card-num3").className = "form-control-show";
        document.getElementById("card-edit-expMonth3").className = "form-control-show";
		document.getElementById("card-edit-expYear3").className = "form-control-show";
		document.getElementById("card-edit-Cvv3").className = "form-control-show";
		document.getElementById("edit-card-button3").className = "form-control-show";
    } else if (document.getElementById("card-edit-header3").className === "form-control-show") {
        document.getElementById("card-edit-header3").className = "form-control-hide";
        document.getElementById("edit-card-name3").className = "form-control-hide";
        document.getElementById("edit-card-num3").className = "form-control-hide";
        document.getElementById("card-edit-expMonth3").className = "form-control-hide";
		document.getElementById("card-edit-expYear3").className = "form-control-hide";
		document.getElementById("card-edit-Cvv3").className = "form-control-hide";
		document.getElementById("edit-card-button3").className = "form-control-hide";
    }
}

function on4CardEditButtonClick() {
    if (document.getElementById("card-edit-header4").className === "form-control-hide") {
        document.getElementById("card-edit-header4").className = "form-control-show";
        document.getElementById("edit-card-name4").className = "form-control-show";
        document.getElementById("edit-card-num4").className = "form-control-show";
        document.getElementById("card-edit-expMonth4").className = "form-control-show";
		document.getElementById("card-edit-expYear4").className = "form-control-show";
		document.getElementById("card-edit-Cvv4").className = "form-control-show";
		document.getElementById("edit-card-button4").className = "form-control-show";
    } else if (document.getElementById("card-edit-header4").className === "form-control-show") {
        document.getElementById("card-edit-header4").className = "form-control-hide";
        document.getElementById("edit-card-name4").className = "form-control-hide";
        document.getElementById("edit-card-num4").className = "form-control-hide";
        document.getElementById("card-edit-expMonth4").className = "form-control-hide";
		document.getElementById("card-edit-expYear4").className = "form-control-hide";
		document.getElementById("card-edit-Cvv4").className = "form-control-hide";
		document.getElementById("edit-card-button4").className = "form-control-hide";
    }
}

function on5CardEditButtonClick() {
    if (document.getElementById("card-edit-header5").className === "form-control-hide") {
        document.getElementById("card-edit-header5").className = "form-control-show";
        document.getElementById("edit-card-name5").className = "form-control-show";
        document.getElementById("edit-card-num5").className = "form-control-show";
        document.getElementById("card-edit-expMonth5").className = "form-control-show";
		document.getElementById("card-edit-expYear5").className = "form-control-show";
		document.getElementById("card-edit-Cvv5").className = "form-control-show";
		document.getElementById("edit-card-button5").className = "form-control-show";
    } else if (document.getElementById("card-edit-header5").className === "form-control-show") {
        document.getElementById("card-edit-header5").className = "form-control-hide";
        document.getElementById("edit-card-name5").className = "form-control-hide";
        document.getElementById("edit-card-num5").className = "form-control-hide";
        document.getElementById("card-edit-expMonth5").className = "form-control-hide";
		document.getElementById("card-edit-expYear5").className = "form-control-hide";
		document.getElementById("card-edit-Cvv5").className = "form-control-hide";
		document.getElementById("edit-card-button5").className = "form-control-hide";
    }
}


const cardName1 = document.querySelector('.edit-card-name1');
const cardNum1 = document.querySelector('.edit-card-num1');
const expMonth1 = document.querySelector('.edit-card-expMonth1');
const expYear1 = document.querySelector('.edit-card-expYear1');
const cvv1 = document.querySelector('.edit-card-cvv1');

function check1EditCardInputs() {

	const cardNameValue = cardName1.value.trim();
	const cardNumValue = cardNum1.value.trim();
	const expMontValue = expMonth1.value.trim();
	const expYearValue = expYear1.value.trim();
	const cvvValue = cvv1.value.trim();

	if(cardNameValue === '') {
		setErrorFor(cardName1, 'Please enter your payment option name');
        return false;
	} else if (cardNameValue.length > 19) {
		setErrorFor(cardName1, 'Please enter sixteen digits');
        return false;
	} else {
		setSuccessFor(cardName1);
	}
	
	if(cardNumValue === '') {
		setErrorFor(cardNum1, 'Please enter your card number');
        return false;
	} else{
		setSuccessFor(cardNum1);
	}
	
	if(expMontValue === '') {
		setErrorFor(expMonth1, 'Please enter exp month');
        return false;
	} else if (expMontValue.length > 2) {
		setErrorFor(expMonth1, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expMonth1);
	}
	
	if(expYearValue === '') {
		setErrorFor(expYear1, 'Please enter exp year');
        return false;
	} else if (expYearValue.length > 2) {
		setErrorFor(expYear1, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expYear1);
	}
	
	if(cvvValue === '') {
		setErrorFor(cvv1, 'Please enter card cvv');
        return false;
	} else if (cvvValue.length > 3) {
		setErrorFor(cvv1, 'Please enter three digits');
        return false;
	} else{
		setSuccessFor(cvv1);
	}
    return true;
}

const cardName2 = document.querySelector('.edit-card-name2');
const cardNum2 = document.querySelector('.edit-card-num2');
const expMonth2 = document.querySelector('.edit-card-expMonth2');
const expYear2 = document.querySelector('.edit-card-expYear2');
const cvv2 = document.querySelector('.edit-card-cvv2');

function check2EditCardInputs() {

	const cardNameValue = cardName2.value.trim();
	const cardNumValue = cardNum2.value.trim();
	const expMontValue = expMonth2.value.trim();
	const expYearValue = expYear2.value.trim();
	const cvvValue = cvv2.value.trim();

	if(cardNameValue === '') {
		setErrorFor(cardName2, 'Please enter your payment option name');
        return false;
	} else if (cardNameValue.length > 19) {
		setErrorFor(cardName2, 'Please enter sixteen digits');
        return false;
	} else {
		setSuccessFor(cardName2);
	}
	
	if(cardNumValue === '') {
		setErrorFor(cardNum2, 'Please enter your card number');
        return false;
	} else{
		setSuccessFor(cardNum2);
	}
	
	if(expMontValue === '') {
		setErrorFor(expMonth2, 'Please enter exp month');
        return false;
	} else if (expMontValue.length > 2) {
		setErrorFor(expMonth2, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expMonth2);
	}
	
	if(expYearValue === '') {
		setErrorFor(expYear2, 'Please enter exp year');
        return false;
	} else if (expYearValue.length > 2) {
		setErrorFor(expYear2, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expYear2);
	}
	
	if(cvvValue === '') {
		setErrorFor(cvv2, 'Please enter card cvv');
        return false;
	} else if (cvvValue.length > 3) {
		setErrorFor(cvv2, 'Please enter three digits');
        return false;
	} else{
		setSuccessFor(cvv2);
	}
    return true;
}

const cardName3 = document.querySelector('.edit-card-name3');
const cardNum3 = document.querySelector('.edit-card-num3');
const expMonth3 = document.querySelector('.edit-card-expMonth3');
const expYear3 = document.querySelector('.edit-card-expYear3');
const cvv3 = document.querySelector('.edit-card-cvv3');

function check3EditCardInputs() {

	const cardNameValue = cardName3.value.trim();
	const cardNumValue = cardNum3.value.trim();
	const expMontValue = expMonth3.value.trim();
	const expYearValue = expYear3.value.trim();
	const cvvValue = cvv3.value.trim();

	if(cardNameValue === '') {
		setErrorFor(cardName3, 'Please enter your payment option name');
        return false;
	} else if (cardNameValue.length > 19) {
		setErrorFor(cardName3, 'Please enter sixteen digits');
        return false;
	} else {
		setSuccessFor(cardName3);
	}
	
	if(cardNumValue === '') {
		setErrorFor(cardNum3, 'Please enter your card number');
        return false;
	} else{
		setSuccessFor(cardNum3);
	}
	
	if(expMontValue === '') {
		setErrorFor(expMonth3, 'Please enter exp month');
        return false;
	} else if (expMontValue.length > 2) {
		setErrorFor(expMonth3, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expMonth3);
	}
	
	if(expYearValue === '') {
		setErrorFor(expYear3, 'Please enter exp year');
        return false;
	} else if (expYearValue.length > 2) {
		setErrorFor(expYear3, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expYear3);
	}
	
	if(cvvValue === '') {
		setErrorFor(cvv3, 'Please enter card cvv');
        return false;
	} else if (cvvValue.length > 3) {
		setErrorFor(cvv3, 'Please enter three digits');
        return false;
	} else{
		setSuccessFor(cvv3);
	}
    return true;
}

const cardName4 = document.querySelector('.edit-card-name4');
const cardNum4 = document.querySelector('.edit-card-num4');
const expMonth4 = document.querySelector('.edit-card-expMonth4');
const expYear4 = document.querySelector('.edit-card-expYear4');
const cvv4 = document.querySelector('.edit-card-cvv4');

function check4EditCardInputs() {

	const cardNameValue = cardName4.value.trim();
	const cardNumValue = cardNum4.value.trim();
	const expMontValue = expMonth4.value.trim();
	const expYearValue = expYear4.value.trim();
	const cvvValue = cvv4.value.trim();

	if(cardNameValue === '') {
		setErrorFor(cardName4, 'Please enter your payment option name');
        return false;
	} else if (cardNameValue.length > 19) {
		setErrorFor(cardName4, 'Please enter sixteen digits');
        return false;
	} else {
		setSuccessFor(cardName4);
	}
	
	if(cardNumValue === '') {
		setErrorFor(cardNum4, 'Please enter your card number');
        return false;
	} else{
		setSuccessFor(cardNum4);
	}
	
	if(expMontValue === '') {
		setErrorFor(expMonth4, 'Please enter exp month');
        return false;
	} else if (expMontValue.length > 2) {
		setErrorFor(expMonth4, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expMonth4);
	}
	
	if(expYearValue === '') {
		setErrorFor(expYear4, 'Please enter exp year');
        return false;
	} else if (expYearValue.length > 2) {
		setErrorFor(expYear4, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expYear4);
	}
	
	if(cvvValue === '') {
		setErrorFor(cvv4, 'Please enter card cvv');
        return false;
	} else if (cvvValue.length > 3) {
		setErrorFor(cvv4, 'Please enter three digits');
        return false;
	} else{
		setSuccessFor(cvv4);
	}
    return true;
}

const cardName5 = document.querySelector('.edit-card-name5');
const cardNum5 = document.querySelector('.edit-card-num5');
const expMonth5 = document.querySelector('.edit-card-expMonth5');
const expYear5 = document.querySelector('.edit-card-expYear5');
const cvv5 = document.querySelector('.edit-card-cvv5');

function check5EditCardInputs() {

	const cardNameValue = cardName5.value.trim();
	const cardNumValue = cardNum5.value.trim();
	const expMontValue = expMonth5.value.trim();
	const expYearValue = expYear5.value.trim();
	const cvvValue = cvv5.value.trim();

	if(cardNameValue === '') {
		setErrorFor(cardName5, 'Please enter your payment option name');
        return false;
	} else if (cardNameValue.length > 19) {
		setErrorFor(cardName5, 'Please enter sixteen digits');
        return false;
	} else {
		setSuccessFor(cardName5);
	}
	
	if(cardNumValue === '') {
		setErrorFor(cardNum5, 'Please enter your card number');
        return false;
	} else{
		setSuccessFor(cardNum5);
	}
	
	if(expMontValue === '') {
		setErrorFor(expMonth5, 'Please enter exp month');
        return false;
	} else if (expMontValue.length > 2) {
		setErrorFor(expMonth5, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expMonth5);
	}
	
	if(expYearValue === '') {
		setErrorFor(expYear5, 'Please enter exp year');
        return false;
	} else if (expYearValue.length > 2) {
		setErrorFor(expYear5, 'Please enter two digits');
        return false;
	} else{
		setSuccessFor(expYear5);
	}
	
	if(cvvValue === '') {
		setErrorFor(cvv5, 'Please enter card cvv');
        return false;
	} else if (cvvValue.length > 3) {
		setErrorFor(cvv5, 'Please enter three digits');
        return false;
	} else{
		setSuccessFor(cvv5);
	}
    return true;
}
