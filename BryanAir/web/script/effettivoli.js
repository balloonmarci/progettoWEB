/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function whichChecked(radioBoxes, elements){
  for (var i = 0, len = radioBoxes.length; i < len; i++)
    if(!radioBoxes[i].checked){
      elements[i].style.backgroundImage = "none";
    }
}

function getCheck(radioBoxes, elements){
  event.target.style.backgroundImage = 'url("images/ok.png")';
  event.target.style.backgroundSize = "cover";
  event.target.firstChild.checked = true;
  whichChecked(radioBoxes, elements);
}

function getDay(data) {
  var daysOfTheWeek = ["Domenica", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"];
  var d = new Date(data);
  var n = d.getDay();

  return daysOfTheWeek[n];
}

function dayMonth(data) {
  var d = new Date(data);
  return d.getDate();
}

function getMonth(data) {
  var daysOfTheMonth = ["Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giunio", "Luglio",
      "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"];
  var d = new Date(data);
  var n = d.getMonth();

  return daysOfTheMonth[n];
}

function getMonthNumber(){
   var month = document.flightDateForm.flightmonth.value;
   var monthNumber;
   switch(month){
        case "Gennaio":
            monthNumber = "01";
            break;
        case "Febbraio":
            monthNumber = "02";
            break;
        case "Marzo":
            monthNumber = "03";
            break;
        case "Aprile":
            monthNumber = "04";
            break;
        case "Maggio":
            monthNumber = "05";
            break;
        case "Giugno":
            monthNumber = "06";
            break;
        case "Luglio":
            monthNumber = "07";
            break;
        case "Agosto":
            monthNumber = "08";
            break;
        case "Settembre":
            monthNumber = "09";
            break;
        case "Ottobre":
            monthNumber = "10";
            break;
        case "Novembre":
            monthNumber = "11";
            break;
        case "Dicembre":
            monthNumber = "12";
            break;
    }

    return monthNumber;
}
            
function setDate(flightday, date){
    flightday.innerHTML = getDay(date) + ", " 
                          + dayMonth(date) + " "
                          + getMonth(date);
}

function onConcreteFlightsLoadHandler(){
  var radiodep = document.getElementsByClassName("radiodeparture");
  var radioret = document.getElementsByClassName("radioreturn");
  var radioBoxesdep = document.getElementsByClassName("radiojsdep");
  var radioBoxesret = document.getElementsByClassName("radiojsret");

  for (var i = 0, len = radiodep.length; i < len; i++)
    radiodep[i].addEventListener("click", function(){ getCheck(radioBoxesdep, radiodep)});
  for (var i = 0, len = radioret.length; i < len; i++)
    radioret[i].addEventListener("click", function(){ getCheck(radioBoxesret, radioret)});

  radiodep[0].style.backgroundImage = 'url("images/ok.png")';
  radiodep[0].style.backgroundSize = "cover";

  radioret[0].style.backgroundImage = 'url("images/ok.png")';
  radioret[0].style.backgroundSize = "cover";
}


