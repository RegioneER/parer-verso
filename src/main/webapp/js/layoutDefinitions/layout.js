//TODO riposizionare questo codice in maniera più legata a edit (centra poco con il layout)
$(document).ready(function() {
	if($('div#datiUnitaDoc_idTipoUnitaDoc').length!=0 ){
		$("fieldset:eq(0)").find("legend:first").text($("#datiUnitaDoc_idTipoUnitaDoc").text() + ' - Vedi tutti i dettagli...' );	
		$("fieldset:eq(0)").find("legend:first").css('cursor','pointer');
		$("fieldset:eq(0)").collapsefieldset({closed: true, openText: 'Dettagli unità documentaria'});
	}
	$("fieldset:gt(0)").find("legend:first").css('cursor','pointer');
	$("fieldset:gt(0)").collapsefieldset();


	// ciclo su tutti i div che contengono i testi di help individuando l'eventuale vincolo sul numero minimo di caratteri 
	// ed aggiungo il div con la barra di avanzamento e il contatore dei caratteri
	$('div.inputdescription').each( function(index, element) {
		var textArea = $(element).prev();
		if (! textArea.is("textarea")) {
			return;
		}
		idTextArea = textArea.attr('id');
		var testoHelp = $("#" + idTextArea).next().html();
		var almenoIdx = testoHelp.lastIndexOf('Almeno');

		var caratteriIdx = testoHelp.lastIndexOf('caratteri');
		if (almenoIdx >= 0 && caratteriIdx >= 0) {
			numMinimoCaratteri = testoHelp.substring(almenoIdx + 7, caratteriIdx -1);
		} else {
			numMinimoCaratteri = 0;
		} 
		if (numMinimoCaratteri > 0) {
			idBarBoxName = "barbox_" + idTextArea;
			idProgressBarName = "progressbar_" + idTextArea;
			idCountName = "count_" + idTextArea;
			var elementBarString = "<div style='height: 25px; float: left;'><div id='" + idBarBoxName + "' class='barbox'><div id='" + idProgressBarName + "' class='progressbar'></div></div><div id='" + idCountName + "' class='count'>0</div></div>";
			$("#" + idTextArea).attr('class', 'text_area_input');
			$("#" + idTextArea).parent().append(elementBarString);
			var numMinCaratteri = numMinimoCaratteri;	
			var textAreaBox = $(element).prev().val();
			var main = textAreaBox.length * 100;
			var value = (main / numMinCaratteri);
			var count = textAreaBox.length;
			$('#' + idCountName).html(count);
			if (textAreaBox.length < numMinCaratteri) {
				$("#" + idProgressBarName).css("background-color", "#FFED00");
				$("#" + idProgressBarName).animate({
					"width" : value + '%'
				}, 1);
			} else {
				$("#" + idProgressBarName).css("background-color", "green");
				$("#" + idProgressBarName).css("width", "100%");

				$("#" + idTextArea).val(
						textAreaBox
				);
			}
		}					
	});

	$(".text_area_input").focus(function() {

		// per la textarea con il focus corrente individuo l'eventuale numero minimo di caratteri (occorrenza delle sottostringhe "Almeno" e 'caratteri')
		idTextArea = $(this).attr('id');
		idBarBoxName = "barbox_" + idTextArea;
		idProgressBarName = "progressbar_" + idTextArea;
		idCountName = "count_" + idTextArea;
		var testoHelp = $("#" + idTextArea).next().html();
		var almenoIdx = testoHelp.lastIndexOf('Almeno');
		var caratteriIdx = testoHelp.lastIndexOf('caratteri');
		if (almenoIdx >= 0 && caratteriIdx >= 0) {
			numMinimoCaratteri = testoHelp.substring(almenoIdx + 7, caratteriIdx -1);
		}

		// per ogni carattere digitato incremento il contatore dei caratteri, e gestisco barra di avanzamento e contatore 
		$("#" + idTextArea).keyup(
				function(element) {
					var textAreaBox = $(this).val();
					var count = textAreaBox.length;
					var numMinCaratteri = numMinimoCaratteri;
					var main = textAreaBox.length * 100;
					var value = (main / numMinCaratteri);
					$('#' + idCountName).html(count);
					if (textAreaBox.length < numMinCaratteri) {
						$("#" + idProgressBarName).css("background-color", "#FFED00");
						$("#" + idProgressBarName).animate({
							"width" : value + '%'
						}, 1);
					} else {
						$("#" + idProgressBarName).css("background-color", "green");
						$("#" + idProgressBarName).css("width", "100%");
						//La seguente istruzione è stata aggiunta perchè con Firefox e
						//Chrome il passaggio da un campo all'altro tramite tab fa sì
						//che il cursore si riposizioni nell'ultima posizione occupata
						//Però con Explorer riposiziona sempre il cursore in fondo al testo
						//rendendo impossibile fare modifiche all'interno del testo.
						//$("#" + idTextArea).val(textAreaBox);
					}
					return false;
				}
		);

	});

});

// quando la textarea perde il focus occorre effettuare il trim del testo inserito ed eventualmente correggere barra e contatore 
$('div.inputdescription').each( function(index, element) {
	idTextArea = $(element).prev().attr('id');
	// occorre valutare soltanto il contenuto delle textarea (nell'id contengono sempre la sottostringa "_strappo")
	if (idTextArea.indexOf("_strappo") != -1) {
		$("#" + idTextArea).focusout(function(element) {
			var testo = document.getElementById(idTextArea).value;
			var testoTrimmed = $.trim(testo);
			document.getElementById(idTextArea).value = testoTrimmed;
			var textAreaBox = $(this).val();
			var count = textAreaBox.length;
			var numMinCaratteri = numMinimoCaratteri;
			var main = textAreaBox.length * 100;
			var value = (main / numMinCaratteri);
			$('#' + idCountName).html(count);
			if (textAreaBox.length < numMinCaratteri) {
				$("#" + idProgressBarName).css("background-color", "#FFED00");
				$("#" + idProgressBarName).animate({
					"width" : value + '%'
				}, 1);
			} else {
				$("#" + idProgressBarName).css("background-color", "green");
				$("#" + idProgressBarName).css("width", "100%");
				//La seguente istruzione è stata aggiunta perchè con Firefox e
				//Chrome il passaggio da un campo all'altro tramite tab fa sì
				//che il cursore si riposizioni nell'ultima posizione occupata
				//Però con Explorer riposiziona sempre il cursore in fondo al testo
				//rendendo impossibile fare modifiche all'interno del testo.
				//$("#" + idTextArea).val(
				//	textAreaBox
				//);
			}
			return false;

		});
	}
});
