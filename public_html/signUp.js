/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global google */

const form = document.getElementById('signUpForm');
const username = document.getElementById('userName');
const userid = document.getElementById('userId');
const email = document.getElementById('userEmail');
const password = document.getElementById('userPassword');
const password2 = document.getElementById('userPassword2');
const phone = document.getElementById('userPhone');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    checkInputs();
});

function checkInputs() {
    // get the values from the inputs
    const usernameValue = username.value.trim();
    const useridValue = userid.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();
    const phoneValue = phone.value.trim();
    
    if(usernameValue === '') {
        // show error
        // add error class
        setErrorFor(username, 'Username cannot be blank');
    } else {
        // add success class
        setSuccessFor(username);
    }
    
    if(useridValue === '') {
        setErrorFor(userid, 'Userid cannot be blank');
    } else {
        setSuccessFor(userid);
    }
    
    if(emailValue === '') {
        setErrorFor(email, 'Email cannot be blank');
    } else if(!isEmail(emailValue)) {
        setErrorFor(email, 'Email is not valud');
    } else {
        setSuccessFor(email);
    }
    
    if(passwordValue === '') {
        setErrorFor(password, 'Password cannot be blank');
    } else if(passwordValue.length < 8) {
        setErrorFor(password, 'Password must be at least 8 characters long');
    } else {
        setSuccessFor(password);
    }
    
    if(password2Value === '') {
        setErrorFor(password2, 'Password cannot be blank');
    } else if(passwordValue !== password2Value) {
        setErrorFor(password2, 'Passwords does not match');
    } else {
        setSuccessFor(password2);
    }
    
    if(phoneValue !== '') {
        if(!isPhone(phoneValue)) {
            setErrorFor(phone, 'Phone number must be 10 digits');
        } else {
            setSuccessFor(phone);
        }
    }
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    
    // add error message inside small
    small.innerText = message;
    
    // add error class
    formControl.className = 'signUpform-control error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'signUpform-control success';
}

function isEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function isPhone(phone) {
    var phoneno = /^\d{10}$/;
    return phoneno.test(phone);
}

/**
        <script>
            let autocomplete;
            function initAutocomplete() {
                autocomplete = new google.maps.places.Autocomplete(
                    document.getElementById('userStreet1'), {
                        types: ['establishment'],
                        componentRestrictions: {'country': ['US']},
                        fields: ['place_id', 'geometry', 'name']
                    });
            }
        </script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDHpoQmEdKTAiBN74aDKdku1KeMH13026U&libraries=places&callback=initAutocomplete"></script>
 **/