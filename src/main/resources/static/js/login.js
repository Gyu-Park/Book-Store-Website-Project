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

    if (evt.currentTarget.id == "tablinks-Login") {
        if (document.getElementById('tablinks-SignUp-Active')) {
            document.getElementById('tablinks-SignUp-Active').id = 'tablinks-SignUp';
        }
        document.getElementById('tablinks-Login').id = 'tablinks-Login-Active';
    } else if (evt.currentTarget.id == "tablinks-SignUp") {
        if (document.getElementById('tablinks-Login-Active')) {
            document.getElementById('tablinks-Login-Active').id = 'tablinks-Login';
        }
        document.getElementById('tablinks-SignUp').id = 'tablinks-SignUp-Active';
    }
}

// form validation
const form = document.getElementById('form');
const firstname = document.getElementById('signup-fname');
const lastname = document.getElementById('signup-lname');
const email = document.getElementById('signup-email');
const password = document.getElementById('signup-password');
const password2 = document.getElementById('signup-repeat-password');

/*
form.addEventListener('submit', (e) => {
	e.preventDefault();
	
	checkInputs();
});
*/

function checkInputs() {
	// remove whitespaces
	const firstnameValue = firstname.value.trim();
    const lastnameValue = lastname.value.trim();
	const emailValue = email.value.trim();
	const passwordValue = password.value.trim();
	const password2Value = password2.value.trim();
	
	if(firstnameValue === '') {
		setErrorFor(firstname, 'First name cannot be blank');
        return false;
	} else if (firstnameValue.length > 20) {
		setErrorFor(firstname, 'Please enter less than 20 characters');
        return false;
	} else {
		setSuccessFor(firstname);
	}

    if(lastnameValue === '') {
		setErrorFor(lastname, 'Last name cannot be blank');
        return false;
	} else if (lastnameValue.length > 20) {
		setErrorFor(lastname, 'Please enter less than 20 characters');
        return false;
	} else {
		setSuccessFor(lastname);
	}
	
	if(emailValue === '') {
		setErrorFor(email, 'Email cannot be blank');
        return false;
	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'Not a valid email');
        return false;
	} else if (emailValue.length > 250) {
		setErrorFor(email, 'Not a valid email');
        return false;
	} else {
		setSuccessFor(email);
	}
	
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
	formControl.className = 'form-control error';
	small.innerText = message;
}

function setSuccessFor(input) {
	const formControl = input.parentElement;
	formControl.className = 'form-control success';
}
	
function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}