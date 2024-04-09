

var gestisciDialogInserimento = function(button, dialogDiv) {
	// la form di inserimento
	// e' modale.
	var buttonName = button.name;
	var buttonValue = button.value;
	var postMethod = button.name.replace('create', 'insert');
	var idDocumento = null;
	$(button).closest("fieldset").find("input[type='hidden']").each(function() {
		var nameofHI = $(this).attr('name');
		if (nameofHI.indexOf("iddocumento") > -1) {
			idDocumento = $(this).val();
		}
	});
	var strMethodToPost = buttonName + "=" + buttonValue + "&ajaxEnabled=true";
	if (idDocumento !== null){
		strMethodToPost = strMethodToPost + "&idDocumento=" + idDocumento;
	}
	var url = $('form:eq(0)').attr('action');

	$.unblockUI();
	dialogDiv.dialog({
		zIndex : 4999,
		modal : true,
		close : pulisciHidden,
		width : '965px',
		position : [ 'middle', 'top' ],
		buttons : [
				{
					text : "Salva",
					click : function() {
						var myDialog = this;
						var ajaxForm = dialogDiv.find('form');
						var messages='<div class="alert alert-error">';
					    var isErrorPresent=0;
					    var elNewRigaidTipoCompDoc = $('#elNewRigaidTipoCompDoc').val();
					    var elNewRigaidFormatoFileDoc = $('#elNewRigaidFormatoFileDoc').val();
                                            var elNewRigadsHashFileVers = $('#elNewRigadsHashFileVers').val();
					    var elNewRigaflgFirmaPerRifTemp = $('#elNewRigaflgFirmaPerRifTemp').val();
					    var elNewRigadataRifTemp = $('#elNewRigadataRifTemp').val();
					    var elNewRigacodallegato = $('#elNewRigacodallegato').val();
						$('#elNewDettaglio1 > div > form > fieldset > div:nth-child(3) > div > div > label').css('width','');
						$('.text-error').hide();
						
						if($('#elNewRigaidTipoCompDoc').val() == ''){
						 	isErrorPresent=1;
						 	$('#elNewRigaidTipoCompDoc').after('<span class="text-error">&nbsp;<strong> obbligatorio</strong></span>');
						} 
						if($('#elNewRigaidFormatoFileDoc').val() == ''){
						 	isErrorPresent=1;
						 	$('#elNewRigaidFormatoFileDoc').after('<span class="text-error">&nbsp;<strong> obbligatorio</strong></span>');
						}

                                                var hash_txt = $('#elNewRigadsHashFileVers').val();
                                                if(hash_txt != '' && (typeof hash_txt === 'string' || hash_txt instanceof String) && hash_txt.length != 40 ) {
						 	isErrorPresent=1;
						 	$('#elNewRigadsHashFileVers').after('<span class="text-error">&nbsp;<strong>Errore: un\'impronta SHA-1 possiede 40 caratteri</strong></span>');
						}
						if($('#elNewRigacodallegato').val() == ''){
						 	isErrorPresent=1;
						 	$('#elNewRigacodallegato').after('<span class="text-error">&nbsp;<strong> obbligatorio</strong></span>');
						} 

						if(isErrorPresent==0){
						var uploads = ajaxForm.find('input[type=file]');
						if (uploads.length > 0) {
							// Ci sono campi di tipo file upload quindi non
							// funziona post ajax
							// (a meno di usare XHR2 non supportato IE<10 quindi
							// faccio
							// invoco il submit della form del dialog corredata
							// delle informazioni in hidden
							// In caso di errori si finira' nella Form NOJS
							ajaxForm.append('<input type="hidden" name="'+ postMethod + '" value="' + buttonValue+ '"/>');
							// Fare submit
							ajaxForm.submit();
						} else {
							// Non ci sono campi file upload  fare tutto
							// con ajax
							var formToPost = dialogDiv.find('form');
							var strDataToPost = formToPost.serialize();
							strDataToPost += "&" + postMethod + "="	+ buttonValue + "&ajaxEnabled=true";
							$.post(url, strDataToPost, function(data,
									textStatus, jqXHR) {
								var esitoInvocazione = jqXHR.getResponseHeader('DtsAcaResult');
								if (esitoInvocazione === "validationError") {
									// Rivisualizzare form ed errori
									dialogDiv.html(data);
								} else {
									$(myDialog).dialog("destroy");
									location.reload();
								}
							});

						}
					}//isErrorPresent
					}
				}, {
					text : "Annulla",
					click : function() {
						$(this).dialog("close");
					}
				} ]

	});
	var divContentUrl = url + "&" + encodeURI(strMethodToPost);
	dialogDiv.css({
		height : "600px",
		overflow : "auto"
	});
	dialogDiv.load(divContentUrl, function( response, status, xhr ) {
		  if ( status == "error" ) {
			    var msg = "E' avvenuto un errore nel caricamento della pagina: ";
			    dialogDiv.html( msg + xhr.status + " " + xhr.statusText );
			  } else {

					$('input#elNewRigadataRifTemp').removeClass().datepicker({ dateFormat: 'dd/mm/yy',
									        changeYear: true,
				    						closeText: 'Chiudi',
									        prevText: '&#x3c;Prec',
									        nextText: 'Succ&#x3e;',
									        currentText: 'Oggi',
									        monthNames: ['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno',
									        'Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'],
									        monthNamesShort: ['Gen','Feb','Mar','Apr','Mag','Giu',
									        'Lug','Ago','Set','Ott','Nov','Dic'],
									        dayNames: ['Domenica','Luned&#236','Marted&#236','Mercoled&#236','Gioved&#236','Venerd&#236','Sabato'],
									        dayNamesShort: ['Dom','Lun','Mar','Mer','Gio','Ven','Sab'],
									        dayNamesMin: ['Do','Lu','Ma','Me','Gio','Ve','Sa'],
									         firstDay: 1,
									        isRTL: false
				    					});
					$("#ui-datepicker-div").css("z-index", "9999");	    					


			  }
			});

	$('#elNewRigaidTipoDoc').change(function() {
						var str = "";
						var formToPost = $(this).closest("form");
						var action = url;
						var isallegati = action.indexOf("allega") > -1;
						var isannessi = action.indexOf("annes") > -1;
						var isannotati = action.indexOf("annot") > -1;
						if (isallegati | isannessi | isannotati) {
							$("#elNewRigaidTipoDoc option:selected").each(
									function() {
										str += $(this).text();
									});
							if (str.length > 0) {

								var select = $('#elNewRigacdVersioneXSD');
								select.empty();
								var strDataToPost = formToPost.serialize();
								strDataToPost += "&optionsVersioneXSD=optionsVersioneXSD&ajaxEnabled=true";
								$.post(action,strDataToPost,function(data, textStatus, jqXHR) {
													var obj = jQuery.parseJSON(data);
													if (obj) {
														select.append($("<option />").text('-- Seleziona Versione Dati Specifici --')
																		.attr('selected','selected'));
														$.each(obj.opzioni[0],
																		function(key,value) {
																			select.append($("<option />").val(key).text(value));
																			});
													}

												}).fail(
												function(jqXHR, textStatus,
														errorThrown) {
													console.log(jqXHR);
													console.log(textStatus);
													console.log(errorThrown);
												});

							}

						}

					});

};
function pulisciHidden() {
	$('#daEliminareDopoUso').remove();
};
function sbloccaPagina() {
	$.unblockUI();
};

var gestisciDialogInserimentoDatiSpecifici = function(button, dialogDiv) {
	var buttonName = button.name;
	var buttonValue = button.value;
	var postMethod = button.name.replace('create', 'insert');
	var idDocumento = null;
	$(button).closest("fieldset").find("input[type='hidden']").each(function() {
		var nameofHI = $(this).attr('name');
		if (nameofHI.indexOf("iddocumento") > -1) {
			console.log(nameofHI + " = " + $(this).val());
			idDocumento = $(this).val();
		}
	});

	// Recuperare form e "popolare" il div
	var strMethodToPost = buttonName + "=" + buttonValue + "&ajaxEnabled=true";
	if (idDocumento !== null)
		strMethodToPost = strMethodToPost + "&idDocumento=" + idDocumento;
	var url = $('form:eq(0)').attr('action');

	$.unblockUI();
	dialogDiv.dialog({
		zIndex : 4999,
		modal : true,
		close : pulisciHidden,
		width : '965px',
		position : [ 'middle', 'top' ],
		buttons : [
				{
					text : "Salva",
					click : function() {
						var myDialog = this;
						var ajaxForm = dialogDiv.find('form');
						var uploads = ajaxForm.find('input[type=file]');
						if (uploads.length > 0) {
							// Ci sono campi di tipo file upload quindi non
							// funziona post ajax
							// (a meno di usare XHR2 non supportato IE<10 quindi
							// faccio
							// invoco il submit della form del dialog corredata
							// delle informazioni in hidden
							ajaxForm.append('<input type="hidden" name="'
									+ postMethod + '" value="' + buttonValue
									+ '"/>');
							// Fare submit
							ajaxForm.submit();
						} else {
							// Non ci sono campi file upload si puï¿½ fare tutto
							// con ajax
							var formToPost = dialogDiv.find('form');
							var strDataToPost = formToPost.serialize();
							strDataToPost += "&" + postMethod + "="
									+ buttonValue + "&ajaxEnabled=true";
							$.post(url, strDataToPost, function(data,
									textStatus, jqXHR) {
								var esitoInvocazione = jqXHR
										.getResponseHeader('DtsAcaResult');
								if (esitoInvocazione === "validationError") {
									// Rivisualizzare form ed errori
									dialogDiv.html(data);
								} else {
									$(myDialog).dialog("destroy");
									location.reload();
								}
							});

						}

					}
				}, {
					text : "Annulla",
					click : function() {
						$(this).dialog("close");
					}
				} ]

	});
	$.post(url,strMethodToPost,
					function(data, textStatus, jqXHR) {
						var result = data.indexOf("<form");
						var infomessage = "<div class='alert alert-info'> I campi data devono essere inseriti secondo il formato : AAAA-MM-GG</div><form";
						if (result > -1) {
							data = data.replace("<form", infomessage);
						}
						dialogDiv.html(data);
						dialogDiv.dialog("option", "position", [ 'middle',
								'top' ]);
						var position = dialogDiv.dialog("option", "position");
						dialogDiv.css({
							height : "600px",
							overflow : "auto"
						});
						$('#elNewRigaidTipoDoc')
								.change(function() {
											var str = "";
											var formToPost = $(this).closest("form");
											var action = url;
											var isallegati = action.indexOf("allega") > -1;
											var isannessi = action.indexOf("annes") > -1;
											var isannotati = action.indexOf("annot") > -1;
											if (isallegati | isannessi
													| isannotati) {
												$("#elNewRigaidTipoDoc option:selected")
														.each(function() {
																	str += $(this).text();
																});
												if (str.length > 0) {

													var select = $('#elNewRigacdVersioneXSD');
													select.empty();
													var strDataToPost = formToPost.serialize();
													strDataToPost += "&optionsVersioneXSD=optionsVersioneXSD&ajaxEnabled=true";
													console.log(strDataToPost);
													$.post(
																action,
																strDataToPost,
																function(
																		data,
																		textStatus,
																		jqXHR) {

																	var obj = jQuery.parseJSON(data);
																	if (obj) {
																		select
																				.append($("<option />")
																						.text('-- Seleziona Versione Dati Specifici --')
																						.attr('selected',
																								'selected'));
																		$
																				.each(obj.opzioni[0],
																						function(key,
																								value) {
																							select.append($("<option />")
																											.val(key)
																											.text(value));
																						});
																	}

																}
															)
															.fail(
																	function(
																			jqXHR,
																			textStatus,
																			errorThrown) {
																		console
																				.log(jqXHR);
																		console
																				.log(textStatus);
																		console
																				.log(errorThrown);
																	});

												}

											}

										});

					});
};

function upWords(str) {
	return str.replace(/\w\S*/g, function(txt) {
		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
	});
};

function empty(data) {
	if (typeof (data) == 'number' || typeof (data) == 'boolean') {
		return false;
	}
	if (typeof (data) == 'undefined' || data === null) {
		return true;
	}
	if (typeof (data.length) != 'undefined') {
		return data.length == 0;
	}
	var count = 0;
	for ( var i in data) {
		if (data.hasOwnProperty(i)) {
			count++;
		}
	}
	return count == 0;
}

$(document)
		.ready(
				function() {

					var elemCaptions = $('caption');
					elemCaptions.each(function() {
						var htmlValue = $(this).html();
						htmlValue = upWords(htmlValue);
						var id = 'tabella' + htmlValue.replace(/\s+/g, '');
						$(this).parent().attr('id', id);
					});

					if ($('div#domanda_coddomanda').length > 0) {
						// Richiesta conferma prima di salva o salva e continua
						// se modificato il tipo contributo
						$('input#salva').on('click', gestioneDialog);
						$('input#salvacontinua').on('click', gestioneDialog);
					}// fine if

					// Associazione codice per creazione e inserimento nuova
					// riga dettaglio
					$('button#createRiga').on('click', function() {
						gestisciDialogInserimento(this, $('div#elNewRiga'));
						return false;
					});
					$('input.createDettaglio.dettaglio1')
							.on(
									'click',
									function() {
//										selectedValue = $(
//												"#riga_0idTipoStrutDoc").val();

//										if (!empty(selectedValue)) {

											gestisciDialogInserimento(this,
													$('div#elNewDettaglio1'));

//										} else {
//											alert('Selezionare il tipo Struttura Documento');
//											$.unblockUI();

//										}
										return false;
									});
					$('input.createDettaglio.dettaglio2').on(
							'click',
							function() {
								gestisciDialogInserimentoDatiSpecifici(this,
										$('div#elNewDettaglio2'));
								return false;
							});
					$('#createTipiSpecifici').on(
							'click',
							function() {
								// datiUnitaDoc_cdVersioneXSD
								// riga_0cdVersioneXSD
								gestisciDialogInserimentoDatiSpecifici(this,
										$('div#elNewDettaglio2'));
								return false;
							});
					// elNewRigaidTipoDoc
					$().on(
							'click',
							function() {
								gestisciDialogInserimentoDatiSpecifici(this,
										$('div#elNewDettaglio2'));
								return false;
							});// [id^=startsWithPattern][id$=endsWithPattern]
					var selectsIdTipoDoc = $("select[id^=riga_][id$=idTipoDoc]");
					selectsIdTipoDoc
							.each(function(i, idTipoDoc) {
								$(idTipoDoc)
										.change(
												function() {
													var i = $(this).attr("id");
													// alert("changed : "+i);
													var str = "";
													var formToPost = $("form");
													var action = formToPost
															.attr('action');													
														str += $(this).val();
														if (str.length > 0) {

															//

															var numb = i.match(/\d+/g);
															var select = $('#riga_'
																	+ numb[0]
																	+ 'cdVersioneXSD');
															select.empty();
															var strDataToPost = formToPost.serialize();
															strDataToPost += "&optionsVersioneXSD=optionsVersioneXSD&ajaxEnabled=true&idTipoDocSelezionato="
																	+ str;
															$.post(
																			action,
																			strDataToPost,
																			function(
																					data,
																					textStatus,
																					jqXHR) {

																				var obj = jQuery
																						.parseJSON(data);
																				if (obj) {
																					select
																							.append($("<option />")
																									.text('-- Seleziona Versione Dati Specifici --')
																									.attr('selected',
																											'selected'));
																					$
																							.each(obj.opzioni[0],
																									function(key,value) {
																										select.append($("<option />")
																														.val(key)
																														.text(value));
																														});
																				}

																			})
																	.fail(
																			function(
																					jqXHR,
																					textStatus,
																					errorThrown) {
																				console
																						.log(jqXHR);
																				console
																						.log(textStatus);
																				console
																						.log(errorThrown);
																			});

														}

													

												});// fine change

							});// fine selectsIdTipoDoc.each

					// #riga_0idTipoDoc
					$('#riga_0idTipoDoc').on('change', function() {
						$('#tabellaDatiSpecifici').hide();
						$('input[name=createDettaglio2]').hide();
						$('input[name=deleteDettaglio2]').hide();
					});

					// #riga_0idTipoStrutDoc
//					$('#riga_0idTipoStrutDoc').on('change', function() {
//						$('#tabellaListaFile').hide();
//						$('input[name=createDettaglio1]').hide();
//						$('input[name=deleteDettaglio1]').hide();
//					});
					// riga_0cdVersioneXSD
					if($("#riga_0cdVersioneXSD")){
                                            var versioneXSD = $("#riga_0cdVersioneXSD").get(0);

                                            if(versioneXSD != null)
						var versioneXSDNodeName = versioneXSD.nodeName.toLowerCase();
						if ("div" == versioneXSDNodeName) {
							$('#tabellaDatiSpecifici').show();
						} else if ($("#riga_0cdVersioneXSD option").length == 0) {
							$('#tabellaDatiSpecifici').hide();
							$('input[name=createDettaglio2]').hide();
							$('input[name=deleteDettaglio2]').hide();
						}
					}
				});
