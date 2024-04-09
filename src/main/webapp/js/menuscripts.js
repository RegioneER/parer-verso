	$(document).ready(function() {
		/**
		 * Funzione invocata all'evento close della dialog presenta.
		 */
		var sbloccaPagina = function(){
			//Sblocco la pagina e chiudo la conferma
			//Blockui aggiunge comunque il campo #daEliminareDopoUso che va tolto
			//altrimenti schiacciando qualunque pulsante si rifa presenta.
			$.unblockUI();
			$('#daEliminareDopoUso').remove();
		};
		//Richiesta conferma prima di presenta
		$('input#method_presenta').on('click',function(event){	
			var buttonName= this.name;
			var buttonValue=this.value;
			$("div#messaggioConfermaPresenta" ).dialog({
				//Per far apparire la conferma sopra l'Elaborazione in corso
				zIndex: 3999,
				resizable: false,
				height:250,
				modal: true,
				close: sbloccaPagina,
				buttons: {
					"Presenta": function() {
				 		$('form:eq(0)').append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
				 		$('form:eq(0)').submit();
						$( this ).dialog( "destroy" );
					},
					"Annulla": function() {
						$( this ).dialog( "close" );
					}
				}
			});
			return false;	
		});

		// serve solo per le spese A dei tecnopoli
		$('input[name=method_updateA]').on('click',function(event){
			var buttonName= this.name;
			var buttonValue=this.value;
			if ($('#messaggioConfermaSalvataggioSpeseA').length){
				daConfermare = false;
				
				// eventuali voci di spesa modificate
				for (i = 0; i < document.forms[0].totVociSpesa.value; i++) {
					idNumMesiRiferimento = "riga" + i + "_nummesiriferimento";
					idNumeroOraMese = "riga" + i + "_numerooremese";
					idImpegnoMedio = "riga" + i + "_impegnomedio";
					idCostoOrarioUomo = "riga" + i + "_costoorariouomo";
					numMesiRiferimento = $('input#' + idNumMesiRiferimento).val();
					numeroOraMese = $('input#' + idNumeroOraMese).val();
					impegnoMedio = $('input#' + idImpegnoMedio).val();
					costoOrarioUomo = $('input#' + idCostoOrarioUomo).val();
					idSelectSchedaPresentazione = "riga" + i + "_idschedapresentazione";
					aProgetto = false;
					if (($('#' + idSelectSchedaPresentazione + ' option:selected').html()).indexOf('a progetto') != -1) {
						aProgetto = true;
					}
					if (aProgetto === true) {
						if (numMesiRiferimento !== '' || numeroOraMese !== '' || impegnoMedio !== '' || costoOrarioUomo !== '') {
							daConfermare = true;
						}	
					}
				}
				
				// eventuale voce di spesa inserita
				idNumMesiRiferimento = "newrow_nummesiriferimento";
				idNumeroOraMese = "newrow_numerooremese";
				idImpegnoMedio = "newrow_impegnomedio";
				idCostoOrarioUomo = "newrow_costoorariouomo";
				numMesiRiferimento = $('input#' + idNumMesiRiferimento).val();
				numeroOraMese = $('input#' + idNumeroOraMese).val();
				impegnoMedio = $('input#' + idImpegnoMedio).val();
				costoOrarioUomo = $('input#' + idCostoOrarioUomo).val();
				idSelectSchedaPresentazione = "newrow_idschedapresentazione";
				aProgetto = false;
				if (($('#' + idSelectSchedaPresentazione + ' option:selected').html()).indexOf('a progetto') != -1) {
					aProgetto = true;
				}
				if (aProgetto === true) {
					if (numMesiRiferimento !== '' || numeroOraMese !== '' || impegnoMedio !== '' || costoOrarioUomo !== '') {
						daConfermare = true;
					}	
				}
				
				
				if (daConfermare == true) {
					$('div#messaggioConfermaSalvataggioSpeseA').dialog({
						zIndex: 3999,
						resizable: false,
						height:275,
						width:400,
						modal: true,
						close: sbloccaPagina,
						buttons: {
							"Si": function() {
						 		$('form:eq(0)').append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
						 		$('form:eq(0)').submit();
								$( this ).dialog( "destroy" );
							},
							"No": function() {
								$( this ).dialog( "close" );
							}
						}
					});
					return false;
				} else {
					return true;
				}
			}
		});
		
		//Obsoleto non più usato... da eliminare.
		//Warning e richiesta conferma su tasto modifica registro
		//TODO  trovare modo di inviduare anche campiModificabiliOrganizzazione
		$('input[name*="method_campiModificabili"]').click(function(event){	
			var buttonName= this.name;
			var buttonValue=this.value;
			$("div#messaggioNascosto" ).dialog({
					resizable: false,
					height:240,
					modal: true,
					buttons: {
						"Modifica": function() {
		 					$('form:eq(0)').append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
					 		$('form:eq(0)').submit();
							$( this ).dialog( "close" );
						},
						"Annulla": function() {
							$( this ).dialog( "close" );
						}
					}
				});
				return false;	
			});	

	});	
