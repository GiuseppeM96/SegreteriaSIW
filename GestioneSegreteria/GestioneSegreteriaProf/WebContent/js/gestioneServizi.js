function Studente(matricola, nome, cognome, dataNascita){
	this.matricola = matricola;
	this.nome = nome;
	this.cognome = cognome;
	this.dataNascita = dataNascita;
	
	this.info = function(){
		return this.matricola + ", " + this.nome + ", " + this.cognome + ", " + this.dataNascita;
	}
	
}

function proceduraCalcoloISEE(studente){
	alert("Inizio calcolo ISEE per lo studente " + studente.info());
	numeroComponenti = prompt("Inserisci il numero dei componenti del nucleo");
	componentiReddito = new Array();
	componentiPatrimonio = new Array();
	var i;
	for (i = 0; i < numeroComponenti; i++){
		componentiReddito[i] = prompt("Inserire il REDDITO del componente " + (i + 1));
		componentiPatrimonio[i] = prompt("Inserire il PATRIMONIO del componente " + (i + 1));
	}
	
	var redditoComplessivo = 0;
	var patrimonioComplessivo = 0;
	
	for (i = 0; i < numeroComponenti; i++){
		redditoComplessivo += componentiReddito[i];
		patrimonioComplessivo += componentiPatrimonio[i];
	}
	ISR = redditoComplessivo;
	ISP = patrimonioComplessivo;
	
	ISE = ISR + ISP * 20/100;
	
	scaleEquivalenza = 
	{
		"1" : 1,
		"2" : 1.57,
		"3" : 2.04,
		"4" : 2.46,
		"5" : 2.85
	};
	
	/*
	var calcolaSE = function(numeroComponenti){
		var SE;
		//numeroComponenti = numeroComponenti * 1;
		switch (parseInt(numeroComponenti)){
		case 1:		
			SE = 1;		
			break;
		case 2:
			SE = 1.57;
			break;
		case 3:
			SE = 2,04;
			break;
		case 4:
			SE = 2.46;
			break;
		case 5:
			SE = 2.85;
			break;
		default:		
			SE = 2.85 + 0.2 * numeroComponenti;
		}
		return SE;
	};*/
	
	var calcolaSE = function(numeroComponenti){
		var SE;
		if (numeroComponenti <= 5){
			SE = scaleEquivalenza[numeroComponenti];
		}else{
			SE = 2.85 + 0.2 * numeroComponenti;
		}
			
		return SE;
	};
	
	
	SE = calcolaSE(numeroComponenti);
	
	ISEE = ISE / SE;
	
	reportISEECalcolato = "Report ISEE calcolato:\n";
	reportISEECalcolato += "Numero componenti nucleo familiare: " + numeroComponenti + "\n";
	reportISEECalcolato += "Reddito complessivo nucleo familiare: " + redditoComplessivo + "\n";
	reportISEECalcolato += "Patrimonio complessivo nucleo familiare: " + patrimonioComplessivo + "\n";
	reportISEECalcolato += "Valore ISE: " + ISE + "\n";
	reportISEECalcolato += "Scala equivalenza applicata: " + SE + "\n";
	reportISEECalcolato += "Valore ISEE: " + ISEE + "\n";
	
	alert(reportISEECalcolato);
}
