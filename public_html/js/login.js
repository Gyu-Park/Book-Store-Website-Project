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