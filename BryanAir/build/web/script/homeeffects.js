/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setDate(calendar){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd = '0'+dd;
    } 

    if(mm<10) {
        mm = '0'+mm;
    } 

    today = yyyy + '-' + mm + '-' + dd;
    for(var i = 0; i < calendar.length; i++)
        calendar[i].setAttribute("min", today);
    console.log(today);
}

function setOnlyDeparture(){
    var returnCalendar = document.concreteFlightsForm.returndate;
    if(event.target.checked){
        returnCalendar.disabled = true;
    }
}

function setDepartureAndReturn(){
    
    var returnCalendar = document.concreteFlightsForm.returndate;
    if(event.target.checked){
        returnCalendar.disabled = false;
    }
}

function onHomeLoadHandler(){
    
    var calendar = document.querySelectorAll('input[type="date"]');
    setDate(calendar);
  
    calendar[0].onchange = function(){
        calendar[1].setAttribute("min", this.value);
    };
    
    document.concreteFlightsForm.viaggio[0].addEventListener("click", setOnlyDeparture);
    document.concreteFlightsForm.viaggio[1].addEventListener("click", setDepartureAndReturn);
}


