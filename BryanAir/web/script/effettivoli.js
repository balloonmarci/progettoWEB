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

function onLoadHandler(){
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

window.addEventListener("load", onLoadHandler);

