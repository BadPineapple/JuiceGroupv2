elementosValidos = "a[name|href|target|title|onclick|class|data-widget-id],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name|usemap|style],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style],";
elementosValidos += "iframe[allowtransparency|name|id|style|marginheight|frameborder|marginwidth|scrolling|src|width|height|allowfullscreen],small[],";
elementosValidos += "object[align|width|height|id|codebase|classid|data|type],param[name|value],embed[align|width|height|flashvars|type|allowfullscreen|pluginspage|wmode|allowscriptaccess|name|bgcolor|quality|src],";
elementosValidos += "map[name],area[shape|coords|href|alt],script[type|src|async],div[data-configid|style|class]";

function editorSimples(id) {

	if (tinyMCE.getInstanceById(id) != null) {
		tinyMCE.execCommand('mceRemoveControl', false, id);
	}
	
	tinyMCE.init({
		mode : "none",
		theme : "advanced",
		language: "pt",
		//plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",
		// Theme options
		theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "",
		theme_advanced_buttons3 : "",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		extended_valid_elements : elementosValidos,

		entity_encoding : "named",
		//language : "pt_br",
		relative_urls : false,
		remove_script_host : false,
		//forced_root_block : 'p',
		force_br_newlines : true,
		force_p_newlines : false

	});
	
	if (tinyMCE.getInstanceById(id) == null) {
		tinyMCE.execCommand('mceAddControl', false, id);
	}

}

function editorAvancado(id) {
	
	if (tinyMCE.getInstanceById(id) != null) {
		tinyMCE.execCommand('mceRemoveControl', false, id);
	}
	
	tinyMCE.init({
		mode : "none",
		theme : "advanced",
		language: "pt",
		plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",
		// Theme options
		theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,code,|preview,|,forecolor,backcolor",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|ltr,rtl,|,fullscreen",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,
		
		extended_valid_elements : elementosValidos,

         entity_encoding : "named",
         //language : "pt_br",
         relative_urls : false,
         remove_script_host : false,
         //forced_root_block : 'p',
         force_br_newlines : true,
         force_p_newlines : false

	});
	
	if (tinyMCE.getInstanceById(id) == null) {
		tinyMCE.execCommand('mceAddControl', false, id);
	}
	
}
