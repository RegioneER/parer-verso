//Aggiornando dalla distribuzione di elements ricordarsi di sostituire
//'method:*' con 'method_*'
$(document).ready(function(){
	jQuery.ajaxSetup({
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
	    }
	});
})


function updateSelectOptions(relName, selectionProviderIndex, methodName) {
    var selectFieldId = arguments[3 + selectionProviderIndex];

    var data = {
        relName : relName,
        selectionProviderIndex : selectionProviderIndex
    };
    data[methodName] = '';
    for (var i = 3; i < arguments.length; i++ ) {
        var currentId = arguments[i];
        var current = $(currentId);
        data[current.attr('name')] = current.find(':selected').val() != null ? current.find(':selected').val() : "";
    }

    var postUrl = location.href;        
    jQuery.ajax({
        type: 'POST',
        url: postUrl,
        data: data,
        success: function(responseData) {
            var options = responseData;
            if('string' === typeof(options)) {
                options = jQuery.parseJSON(options);
            }

            var selectField = $(selectFieldId);
            selectField.empty();

            for (var i = 0; i < options.length; i++) {
                var option = options[i];
                $("<option>").attr("value",option['v'])
                			.attr("selected", option['s'])
                			.text(option['l'])
                			.appendTo(selectField);
            
            
            }
            selectField.change();
        }
    });
}

function setupAutocomplete(autocompleteId, relName, selectionProviderIndex, methodName) {
    var setupArguments = arguments;
    var selectFieldId = setupArguments[4 + selectionProviderIndex];
    var autocompleteObj = $(autocompleteId);
    var selectField = $(selectFieldId);
    autocompleteObj.autocomplete({
        source: function( request, response ) {
            var data = {
                relName : relName,
                selectionProviderIndex : selectionProviderIndex,
                labelSearch : request.term
            };
            data[methodName] = '';
            for (var i = 4; i < setupArguments.length; i++ ) {
                var currentId = setupArguments[i];
                var current = $(currentId);
                data[current.attr('name')] = current.attr('value');
            }

            var postUrl = stripQueryString(location.href);            
            $.ajax({
                type: 'POST',
                dataType: 'json',
                url: postUrl,
                data: data,
                success: function( responseData ) {
                    response( $.map( responseData, function( item ) {
                          return {
                                label: item.l,
                                value: item.l,
                                optionValue: item.v
                            };
                        }));
                },
                error: function(request, textStatus) {
                    alert(textStatus);
                }
            });
        },
        minLength: 1,
        select: function( event, ui ) {
            if (ui.item) {
                selectField.val(ui.item.optionValue);
            } else {
                selectField.val("");
            }
        },
        change: function(event, ui) {
            if (!ui.item) {
                selectField.val("");
            }
        },
        open: function() {
            $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        },
        close: function() {
            $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        }
    });
}

function stripQueryString(url) {
    var regexp = new RegExp("\\?.*");
    return url.replace(regexp, "");
}

function setupDatePicker(dateFieldId, dateFormat) {
	$(document).ready(function() {
	    var dateField = $(dateFieldId);
		dateFormat= dateFormat.toLowerCase();
	    dateFormat=dateFormat.replace('yyyy','yy');
	    dateField.datepicker({ dateFormat: dateFormat,
	    					   //appendText: ' (gg/mm/aaaa)',
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

	});

}

function addToOnLoad(handler){
	$(document).ready(handler);
}