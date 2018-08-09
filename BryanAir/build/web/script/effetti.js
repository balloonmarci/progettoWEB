function viewLoginForm(){
  var log = document.querySelector('div.m-dropdown-content');
  log.style.width = "1100%";
  if(log.style.visibility === "visible")
    log.style.visibility = "hidden";
  else
    log.style.visibility = "visible";
}

function hide(event){
  var log = document.querySelector('div.m-dropdown-content');
  var logForm = document.querySelector('.loginform-dimensioni');
  var accedi = document.querySelector('.m-dropdown span');
  var tName = event.target;

  if(tName == accedi)
    return;

  if(tName != logForm && tName.parentNode != logForm)
    log.style.visibility = "hidden";
}

function onLoadHandler(){ 
  var b1 = document.querySelector('span');
  var dlog = document.querySelector('.m-dropdown-content form, input');
  b1.addEventListener("click", viewLoginForm); 
}
window.addEventListener("load", onLoadHandler);
