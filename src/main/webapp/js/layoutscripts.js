		$(document).ready(function() {
			
			templateMessaggioEliminaUD = $("div#confermaEliminazioneUD span").text();
			
		    //Impostiamo le opzioni per salvataggio e lettura cookie
			var cookieName = 'statoMenu';

			
			//Duplichiamo i pulsanti per metterli nella
		    //toolbar con una sfondo diverso (icona di azione)
		    //senza value che viene salvato nei dati con chiave
		    //corrispondente al name del pulsante
			//e ci aggiungiamo dopo un <br> per visualizzarli
			//sempre in colonna anche se si allarga la finestra
			/*
			$('div#azioni button').each(

		    function(index, elemento) {
		        var valore = $(elemento).val();
		        $('<br/>').insertAfter($(elemento).clone()
		        		.data($(elemento).attr('name'), {
		            		valueEsteso: valore
		        		})
		        		.addClass('buttonSidebar')
		        		.val("")
		        		.html("<span class='fontIcoMoon'>R</span>")
		        		.appendTo('#toolbarMenu'));
		        
		    });
			
			$('button#createTipiSpecifici').on('click',function () {
				alert($('div#elNewDettaglio2'));
				gestisciDialogInserimento(this,	$('div#elNewDettaglio2'));
				return false;
			});
			*/
			
			
			/**
			* Per un funzionamento completamente prima del submit
			* trasparente bisogna reimpostare il value
			* recuperandolo dai data dove è stato salvato
			* (altrimenti Salva e continua fa salva ma non "continua"
			*/
		    $('#toolbarMenu input').click(
		    function() {
		        $(this).val($(this).data($(this).attr("name")).valueEsteso);
		        return true;
		    });

		    var hideMenu = function(speed) {
		        var animationSpeed;
		        animationSpeed = 1000;
		    	
		        if(speed === 'fast') {
		        	animationSpeed=10;
		        }
		        /*
		        $("#menuEditCurriculum").animate({
		            marginLeft: "-175px"
		        }, animationSpeed).hide();
		        */
		        //$("#menu").hide();
		        
		        $("button.toolbarMenu").show();
		        $("button.menuAzioni").hide();
		        
		        $("#hidePanel").hide();	        
		        
		        $("#menu").animate({
		        	width: "0px",
		        	opacity: 0
		        }, animationSpeed);
		        $("#showPanel").show("normal").animate({
		            width: "5%",
		            opacity: 1
		        }, animationSpeed);
		        $("#wrapper").animate({
		            width: "93%"
		        }, animationSpeed);
		        $("#toolbarMenu").delay(animationSpeed/2).animate({
		            width: "5%",
		            opacity: 1
		        }, animationSpeed/10).show("normal");
		        $.cookie(cookieName,'chiuso',{ path: '/' });
		    };
		    
		    var showMenu = function() {
		    	
		        $("button.toolbarMenu").hide();
		        $("button.menuAzioni").show();

		    	
		        $("#showPanel").animate({
		            width: "0px",
		            opacity: 0
		        }, 300).hide("fast");
		        $("#toolbarMenu").animate({
		            width: "0px",
		            opacity: 0
		        }, 300).hide("fast");
		        $("#wrapper").animate({
		            width: "73%"
		        }, 1000);
		        $("#menu").animate({
		            width: "24%",
		            opacity: 1
		        }, 1000);
		        /*
		        $("#menuEditCurriculum").animate({
		            marginLeft: "0px"
		        }, 1000).show();
		        */
		        //$("#menuEditCurriculum").show();

		        $("#hidePanel").show();	        
	        
		        
		        //Bisogna chiudere tutti jGrowl eventualmente aperti
		        $('#messageAnchor').jGrowl('shutdown');
		        $('#messageAnchor').jGrowl('close');
		        $.cookie(cookieName,'aperto',{ path: '/' });
		    };
		    $("#hidePanel").click(hideMenu);
		    $("#showPanel").click(showMenu);
			
			//Se non è stato incluso il file di jCookie non faccio niente
		    var messagesSize;
		    var poolSize;
		    var closerText;
		    closerText = "chiudi tutti i messaggi";
		    poolSize=5;
		    messagesSize = 0;
		    messagesSize += $('ul.errorMessages li').length;
		    messagesSize += $('ul.warningMessages li').length;
		    messagesSize += $('ul.infoMessages li').length;
		    if(messagesSize>poolSize){
		    	closerText = "vedi altri messaggi";
		    }
		    if($.jGrowl) {
		    	$.jGrowl.defaults.closerTemplate = '<div style="margin-left: -18em; background-color:rgb(100, 100, 100)">'+closerText+'</div>';		    
		    	$.jGrowl.defaults.pool = poolSize;
		    }
		    
		    var beforeOpenFn;
		    var openedCount;
		    openedCount = 0;
		    
		    beforeOpenFn = function(){
		    	messagesSize--;
		    	openedCount++;
		    	if(!(messagesSize > 0)){
		    		$('.jGrowl-closer').text("chiudi tutti");		    		
		    	}
		    };

		    //Controllo che sia stato incluso il plugin
		    if($.cookie) {
		    	if($.cookie(cookieName,{ path: '/' }) === 'chiuso'){
					hideMenu('fast');
					$('ul.errorMessages li').each(
							function(index,elemento){
							    //console.info($(elemento).text());
								$('#messageAnchor').jGrowl($(elemento).text(),
										{sticky:true,theme:'error'});
							}
					);
					$('ul.warningMessages li').each(
						function(index,elemento){
						    //console.info($(elemento).text());
						    $('#messageAnchor').jGrowl($(elemento).text(),
									{sticky:true,
						    		theme:'warning',
						    		beforeOpen: beforeOpenFn
									});
						}
					);
					
					$('ul.infoMessages li').each(
						function(index,elemento){
						    //console.info($(elemento).text());
						    $('#messageAnchor').jGrowl($(elemento).text(),
						             {sticky:true,beforeOpen: beforeOpenFn,theme:'info'});
							}
					);					
					
			    }
		    }
			
			/**
			 * Funzione invocata alla chiusura delle dialog.
			 * Provare/capire se se ne può fare una per tutte le dialog.
			 */
			var sbloccaPagina2 = function(){
				//Elimino l'elemento hidden aggiunta dalla funzione di gestione
				//del blocco della UI altrimenti riesegue l'operazione che
				//vi è stata impostata
				$.unblockUI();
				$('#daEliminareDopoUso').remove();
			};
			//Creazione del plug-in che controlla se ci sono modifiche ai dati
			$('form').eq(0).dirty_form({dynamic: false});
			//A tutti i pulsanti con stile checkChange associo la richiesta conferma
			$('.checkChange').click(function(event){
				if($('.changed').length>0 || ($('.rigaEliminata').length>0)  ){
					var buttonName=this.name;
					var buttonValue=this.value;
					var href = this.href;
					$("div#confermaModifiche" ).dialog({
						resizable: false,
						height:185,
						close: sbloccaPagina2,
						modal: true,
						open: function(event, ui) {
							$('.ui-dialog').css('z-index', 3999);
						},
						buttons: {
							"Continua": function() {
								
								// il checkchange deve essere gestito in modo diverso a seconda della provenienza (bottone o link)   
								if (buttonName != '') {
							 		$('form').eq(0).append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
							 		$('form').eq(0).submit();
									$( this ).dialog( "destroy" );
								} else {
									document.location = href;
								}
							},
							"Annulla": function() {
								$( this ).dialog( "close" );
							}
						}
					});
					return false;	
	
					}
				});
			
			$('input[type=submit]').click(function(event){
			    var buttonName= this.name;
			    var buttonValue= this.value;
			    //Disabilitazione pulsanti
			    $.blockUI({theme: true, title: buttonValue, message: $('#messaggioAttesa')}); 

			    //Aggiunta campo hidden per passare il method_* corretto
			    $('form').eq(0).append('<input type="hidden" id="daEliminareDopoUso" name="' + buttonName + '" value="' + buttonValue + '" />');
			    if(buttonName == 'method_anteprima'){
			    	//Elimino il campo hidden altrimenti qualsiasi pulsante 
			    	//venga premuto viene rieseguita l'anteprima
			    	$('#daEliminareDopoUso').remove();
			    	//La pagina non viene ricaricata quindi riabilito tutto
				    //var t= setTimeout("$('input[type=submit]','#azione125GesDomForm').removeAttr('disabled')",5000);
			    	setTimeout($.unblockUI, 5000); 
			    }
			});
		
			//Richiesta conferma per pulsanti eliminazione righe (UL, spese, ecc.)
			//Richiesta conferma prima di presenta
			$('input.deleteRiga').click(function(event){	
				var buttonName=this.name;
				var buttonValue=this.value;
				var href = this.href;
				var paramTesto = "l'" + buttonValue.substring(9); 
				var templateMessaggio = $("div#confermaEliminazione span").text();
				var testoMessaggio = templateMessaggio.replace('[param]', paramTesto);
				$("div#confermaEliminazione span").text(testoMessaggio);
				
				$("div#confermaEliminazione" ).dialog({
					resizable: false,
					height:185,
					close: sbloccaPagina2,
					modal: true,
					open: function (event, ui) {
						$('.ui-dialog').css('z-index', 3999);
					},
					buttons: {
						"Continua": function() {
							
							// il checkchange deve essere gestito in modo diverso a seconda della provenienza (bottone o link)   
							if (buttonName != '') {
						 		$('form').eq(0).append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
						 		$('form').eq(0).submit();
								$( this ).dialog( "destroy" );
							} else {
								document.location = href;
							}
						},
						"Annulla": function() {
							$( this ).dialog( "close" );
						}
					}
				});
				return false;	
			});

			//Richiesta conferma per pulsanti eliminazione righe (UL, spese, ecc.)
			//Richiesta conferma prima di presenta
			$('input.deleteDettaglio').click(function(event){	
				var buttonName=this.name;
				var buttonValue=this.value;
				var href = this.href;
				var paramTesto = buttonValue.substring(24); 
				var templateMessaggio = $("div#confermaEliminazioneRighe span").text();
				var testoMessaggio = templateMessaggio.replace('[param]', paramTesto);
				$("div#confermaEliminazioneRighe span").text(testoMessaggio);
				
				$("div#confermaEliminazioneRighe").dialog({
					resizable: false,
					height:185,
					close: sbloccaPagina2,
					modal: true,
					open: function (event, ui) {
						$('.ui-dialog').css('z-index', 3999);
					},
					buttons: {
						"Continua": function() {
							
							// il checkchange deve essere gestito in modo diverso a seconda della provenienza (bottone o link)   
							if (buttonName != '') {
						 		$('form').eq(0).append('<input type="hidden" name="' + buttonName + '" value="' + buttonValue + '" />');
						 		$('form').eq(0).submit();
								$( this ).dialog( "destroy" );
							} else {
								document.location = href;
							}
						},
						"Annulla": function() {
							$( this ).dialog( "close" );
						}
					}
				});
				return false;	
			});
			

		});
