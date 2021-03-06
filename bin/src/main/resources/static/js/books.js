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

let allTabs = document.querySelectorAll(".tablinks");

allTabs.forEach(function(tab) {

    tab.addEventListener('click', () => {
        allTabs.forEach(b => b.removeAttribute("id"));
        tab.setAttribute('id', 'active');
    });

});