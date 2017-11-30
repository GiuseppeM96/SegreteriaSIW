/**
 * 
 */
alert("miao page");
numeroComp = prompt("inserisci il numero di componenti del nucleo familiare ");
redditoComponenti=new Array();
var i;
for(i=0;i<numeroComp;i++){
	var x=i+1;
	redditoComponenti[i]=prompt("inserisci il reddito del componente "+x);
}
var element=document.getElementById("topOfPage");
var tmp = element.innerHTML;
element.innerHTML= tmp+"<h1> ciao </h1>";

