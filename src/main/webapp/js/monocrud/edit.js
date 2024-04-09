var gestisciDialogInserimento = function(button, dialogDiv){
	//alert();
	//Il blocco non serve perchè la form di inserimento
	//è modale.
	var buttonName= button.name;
	var buttonValue=button.value;
	var postMethod = button.name.replace('create','insert');

	//Recuperare form e "popolare" il div
	var strMethodToPost = buttonName + "=" + buttonValue + "&ajaxEnabled=true";
	var url = $('form:eq(0)').attr('action');
	//alert("buttonValue : "+buttonValue+" strMethodToPost : "+strMethodToPost+ " url : "+url);
	$.unblockUI();
	dialogDiv.dialog({
		zIndex: 4999,
		modal: true	,
		close: 	pulisciHidden,
		width: '965px',
		position:['middle','top'], 
	//	buttons: {
	//		"Salva": function() {
	//			
	//			$('form:eq(0)').submit();
	//			
	//
	//		},
	//		"Annulla": function() {
	//			$( this ).dialog( "close" );
	//		}
	//	}
		buttons: [
		          {text: "Salva",
		           click: function(){
		        	    var myDialog = this;
		        	  	var ajaxForm = dialogDiv.find('form');
		        	  	var uploads = ajaxForm.find('input[type=file]');
		        	  	if (uploads.length>0){
		        	  		//Ci sono campi di tipo file upload quindi non funziona post ajax
		        	  		// (a meno di usare XHR2 non supportato IE<10 quindi faccio
		        	  		// invoco il submit della form del dialog corredata delle informazioni in hidden
			        	  	//In caso di errori si finirà nella Form NOJS
		        	  		ajaxForm.append('<input type="hidden" name="' + postMethod + '" value="' + buttonValue + '"/>');
			        	  	//Fare submit
			        	  	ajaxForm.submit();		        	  		
		        	  	} else {
		        	  		//Non ci sono campi file upload si può fare tutto con ajax
		        	  		var formToPost = dialogDiv.find('form');
		        			var strDataToPost = formToPost.serialize();
		        			strDataToPost += "&" + postMethod + "=" + buttonValue + "&ajaxEnabled=true";
		        			//alert('url : '+url+' - DataToPost : '+strDataToPost);
		        			$.post(url, strDataToPost, function(data,textStatus, jqXHR){
		        				//alert("data received:" + data);
		        				var esitoInvocazione = jqXHR.getResponseHeader('DtsAcaResult');
		        				if(esitoInvocazione === "validationError") {
		        					//Rivisualizzare form ed errori
		        					dialogDiv.html(data);
		        				} else {
		        					$(myDialog).dialog( "destroy" );
		        					location.reload();								
		        				}
		        			});
		        	  		
		        	  		
		        	  	}
		        	  	
		        	  	}},
		          {text: "Annulla", click: function(){$( this ).dialog( "close" );}}
		]
	
	});
    //alert(url+" - "+strMethodToPost);
	$.post(url, strMethodToPost, function(data,textStatus, jqXHR){
		//alert("data received:" + data);
		dialogDiv.html(data);
		dialogDiv.dialog("option","position",{my: "top", at: "center", collision: 'none'});
		var position = dialogDiv.dialog( "option", "position" );
		dialogDiv.css({height:"600px", overflow:"auto"});
	});
};

var gestisciDialogDatiSpecifici = function(button, dialogDiv, solaVisualizzazione){
	//alert();
	//Il blocco non serve perchè la form di inserimento
	//è modale.
	var buttonName= button.name;
	var buttonValue=button.value;
	var postMethod = button.name.replace('create','insert');

	//Recuperare form e "popolare" il div
	var strMethodToPost = "createDettaglio2=Crea riga dettaglio 1" + "&ajaxEnabled=true";
	var url = $('form:eq(0)').attr('action');
	//alert("buttonValue : "+buttonValue+" strMethodToPost : "+strMethodToPost+ " url : "+url);
	$.unblockUI();
	if(solaVisualizzazione){
		dialogDiv.dialog({
			zIndex: 4999,
			modal: true	,
			close: 	pulisciHidden,
			width: '965px',
            title: "Gestione Dati Specifici",
			position:['middle','top'], 

			buttons: [{text: "Annulla", click: function(){$( this ).dialog( "close" );}}]
		
		});
		
	}else{
	dialogDiv.dialog({
		zIndex: 4999,
		modal: true	,
		close: 	pulisciHidden,
		width: '965px',
		position:['middle','top'], 

		buttons: [
		          {text: "Salva",
		           click: function(){
		        	    var myDialog = this;
		        	  	var ajaxForm = dialogDiv.find('form');
		        	  	var uploads = ajaxForm.find('input[type=file]');
		        	  	if (uploads.length>0){
		        	  		//Ci sono campi di tipo file upload quindi non funziona post ajax
		        	  		// (a meno di usare XHR2 non supportato IE<10 quindi faccio
		        	  		// invoco il submit della form del dialog corredata delle informazioni in hidden
			        	  	//In caso di errori si finirà nella Form NOJS
		        	  		ajaxForm.append('<input type="hidden" name="' + postMethod + '" value="' + buttonValue + '"/>');
			        	  	//Fare submit
			        	  	ajaxForm.submit();		        	  		
		        	  	} else {
		        	  		//Non ci sono campi file upload si può fare tutto con ajax
		        	  		var formToPost = dialogDiv.find('form');
		        			var strDataToPost = formToPost.serialize();
		        			//strDataToPost += "&" + postMethod + "=" + buttonValue + "&ajaxEnabled=true";
		        			strDataToPost += "&insertDettaglio2=Crea riga dettaglio 1" + "&ajaxEnabled=true";
		        			//alert('url : '+url+' - DataToPost : '+strDataToPost);
		        			$.post(url, strDataToPost, function(data,textStatus, jqXHR){
		        				//alert("data received:" + data);
		        				var esitoInvocazione = jqXHR.getResponseHeader('DtsAcaResult');
		        				if(esitoInvocazione === "validationError") {
		        					//Rivisualizzare form ed errori
		        					dialogDiv.html(data);
		        				} else {
		        					$(myDialog).dialog( "destroy" );
		        					location.reload();								
		        				}
		        			});
		        	  		
		        	  		
		        	  	}
		        	  	
		        	  	}},
		          {text: "Annulla", click: function(){$( this ).dialog( "close" );}}/*,
		          {text: "Elimina",
			           click: function(){
			        	    var myDialog = this;
			        	  	var ajaxForm = dialogDiv.find('form');
			        	  	var uploads = ajaxForm.find('input[type=file]');
			        	  	if (uploads.length>0){
			        	  		//Ci sono campi di tipo file upload quindi non funziona post ajax
			        	  		// (a meno di usare XHR2 non supportato IE<10 quindi faccio
			        	  		// invoco il submit della form del dialog corredata delle informazioni in hidden
				        	  	//In caso di errori si finirà nella Form NOJS
			        	  		ajaxForm.append('<input type="hidden" name="' + postMethod + '" value="' + buttonValue + '"/>');
				        	  	//Fare submit
				        	  	ajaxForm.submit();		        	  		
			        	  	} else {
			        	  		//Non ci sono campi file upload si può fare tutto con ajax
			        	  		var formToPost = dialogDiv.find('form');
			        			var strDataToPost = formToPost.serialize();
			        			//strDataToPost += "&" + postMethod + "=" + buttonValue + "&ajaxEnabled=true";
			        			strDataToPost += "&deleteDettaglio2=Crea riga dettaglio 1" + "&ajaxEnabled=true";
			        			//alert('url : '+url+' - DataToPost : '+strDataToPost);
			        			$.post(url, strDataToPost, function(data,textStatus, jqXHR){
			        				//alert("data received:" + data);
			        				var esitoInvocazione = jqXHR.getResponseHeader('DtsAcaResult');
			        				if(esitoInvocazione === "validationError") {
			        					//Rivisualizzare form ed errori
			        					dialogDiv.html(data);
			        				} else {
			        					$(myDialog).dialog( "destroy" );
			        					location.reload();								
			        				}
			        			});
			        	  		
			        	  		
			        	  	}
			        	  	
			        	  	}}*/
		]
	
	});
	}
    //alert(url+" - "+strMethodToPost);
	$.post(url, strMethodToPost, function(data,textStatus, jqXHR){
		//alert("data received:" + data);
		var result = data.indexOf("<form"); 
		var infomessage="<div class='alert alert-info'> I campi data devono essere inseriti secondo il formato : AAAA-MM-GG</div><form";
		if(result>-1){
			data=data.replace("<form",infomessage);
		}
		dialogDiv.html(data);
		dialogDiv.dialog("option","position",['middle','top']);
		dialogDiv.css({height:"600px", overflow:"auto"});
	});
};


function pulisciHidden(){
	$('#daEliminareDopoUso').remove();
};
function sbloccaPagina(){$.unblockUI();};

function empty(data)
{
  if(typeof(data) == 'number' || typeof(data) == 'boolean')
  {
    return false;
  }
  if(typeof(data) == 'undefined' || data === null)
  {
    return true;
  }
  if(typeof(data.length) != 'undefined')
  {
    return data.length == 0;
  }
  var count = 0;
  for(var i in data)
  {
    if(data.hasOwnProperty(i))
    {
      count ++;
    }
  }
  return count == 0;
}

$(document).ready(function() {

	if($('div#domanda_coddomanda').length>0){
		//Richiesta conferma prima di salva o salva e continua se modificato il tipo contributo
		$('input#salva').on('click',gestioneDialog);
		$('input#salvacontinua').on('click',gestioneDialog);
	}//fine if



	//Associazione codice per creazione e inserimento nuova riga dettaglio
	$('button#createRiga').on('click',function () {
		gestisciDialogInserimento(this,	$('div#elNewRiga'));
		return false;
	});		
	$('input.createDettaglio.dettaglio1').on('click',function () {
		gestisciDialogInserimento(this,	$('div#elNewDettaglio1'));
		return false;
	});		
	$('input.createDettaglio.dettaglio2').on('click',function () {
		gestisciDialogDatiSpecifici(this,	$('div#elNewDettaglio2'));
		return false;
	});		
	$('button#createTipiSpecifici').on('click',function () {
		//alert($('div#elNewDettaglio2'));
		gestisciDialogDatiSpecifici(this,	$('div#elNewDettaglio2'));
		return false;
	});	
	$('input.dettaglioDatiSpecifici').prop('disabled', false);
	$('input.dettaglioDatiSpecifici').on('click',function () {
		var selectCdVersioneXSD=$('#datiUnitaDoc_cdVersioneXSD');
		var versioneXSDNodeName=selectCdVersioneXSD.get(0).nodeName.toLowerCase();
		var isCdVersioneXSDaDiv="div"==versioneXSDNodeName;
			//alert($('div#elNewDettaglio2').innerHtml);
		    var leng=selectCdVersioneXSD.length;
		    var sel=(isCdVersioneXSDaDiv?"":selectCdVersioneXSD.val());

		    if(!(leng==0 || ('!'+sel+'!'=='!!'))|| isCdVersioneXSDaDiv){
		    	if(!isCdVersioneXSDaDiv & empty(sel) ){
					$.unblockUI();
					alert('Selezionare la Versione Dati Specifici');

				}else 		    	
					gestisciDialogDatiSpecifici(this,	$('div#elNewDettaglio2'),"div"==versioneXSDNodeName);
		    }
		    if(leng!=0 & empty(sel) & !isCdVersioneXSDaDiv){
				$.unblockUI();
				alert('Selezionare la Versione Dati Specifici');
		    	
		    }
		    $.unblockUI();    
		return false;
	});		
	//#datiUnitaDoc_cdVersioneXSD
	$('#datiUnitaDoc_cdVersioneXSD').on('change', function() {
	    $('input.dettaglioDatiSpecifici').prop('disabled', true);
	});
	//#riga_0idTipoDoc
	$('#riga_0idTipoDoc').on('change', function() {
	    $('#tabellaDatiSpecifici').hide();
	});
	
	//#riga_0idTipoStrutDoc
//	$('#riga_0idTipoStrutDoc').on('change', function() {
//	    $('#tabellaListaFile').hide();
//	});
});	
