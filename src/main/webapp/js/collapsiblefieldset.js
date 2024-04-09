/**
 * CHANGES
 * v.2.1.4 - Testo alternativo per stato aperto (openText)
 * v.2.1.3 - Made it $.noConflict() compatible
 * v.2.1.2 - Fixed bug in which nested fieldsets do not work correctly.
 * v.2.1.1 - Forgot to put the new filter from v.2.1 into the if (settings.closed)
 * v.2.1 - Changed jQuery(this).parent().children().filter( ELEMENTS HERE) to jQuery(this).parent().children().not('label').  Prevents you from having to guess what elements will be in the fieldset.
 * v.2.0 - Added settings to allow a fieldset to be initiated as closed.
 *
 * This script may be used by anyone, but please link back to me.
 *
 * Copyright 2009-2010.  Michael Irwin (http://michael.theirwinfamily.net)
 */
       
jQuery.fn.collapsefieldset = function(options) {
	var defaults = {
		closed : false
	};
	settings = jQuery.extend({}, defaults, options);

	return this.each(function() {
		var obj = jQuery(this);
		
		//Testo originale 
		
		var originalLegend = obj.find("legend:first").text();
		
		
		obj.find("legend:first").addClass('collapsible').click(function() {
			
			/*
			 * Prima che le classi dell'oggetto vengano manipolate imposto il testo del legend
			 */
			if (obj.hasClass('collapsed')){
				//operazione di apertura
				
				//Se non è stato definito un testo diverso rimane immutato.
				if(options == null || options.openText==null){
					obj.find("legend:first").text(originalLegend);
				}
				else{
					obj.find("legend:first").text(options.openText);
				}
				obj.find("legend:first").css('background-position','0px 0px');
			}
			else{
				obj.find("legend:first").text(originalLegend);	
				obj.find("legend:first").css('background-position','0px -14px');
			
				//Se non è stato definito un testo diverso rimane immutato.
				if(options == null || options.closeText==null){
					obj.find("legend:first").text(originalLegend);
				}
				else{
					obj.find("legend:first").text(options.closeText);
				}

			}
				
			if (obj.hasClass('collapsed')){
				obj.removeClass('collapsed').addClass('collapsible');
			}
			jQuery(this).removeClass('collapsed');
	
			obj.children().not('legend').toggle("fast", function() {
				
				$('fieldset legend').wrapInner('<a href="#" />');
			 
				 if (jQuery(this).is(":visible"))
					obj.find("legend:first").addClass('collapsible');
				 else
					obj.addClass('collapsed').find("legend").addClass('collapsed');
			 });
		});
		
		if (settings.closed) {
			obj.addClass('collapsed').find("legend:first").addClass('collapsed');
			/*aggiungere position*/ 
			obj.find("legend:first").css('background-position', '0px -14px');
			obj.children().not("legend:first").css('display', 'none');

		}
	});
};
