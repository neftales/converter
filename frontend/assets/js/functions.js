
//Pegando os formatos de origem quando a pagina é carregada.
$(document).ready(function() {
	jQuery.ajax({
		type: 'GET',
		url: 'http://localhost:8080/converter/nodes',
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		headers: {},			
		success: function(response) {
			var $select = $('#arquivos_origem'); 
			$select.find('option').remove();  
			$select.append('<option value=0> Selecione um formato </option>');
			for(var i=0;i<response.length;i++){
				var obj = response[i];
				$select.append('<option value=' + obj.format + '>' + obj.format + '</option>');
			}	
		},
		error: function( xhr, status, errorThrown ) {
			console.log( errorThrown );
		}
	});
});

//Chamado quando o formato de origem é alterado.
$(document).on('change', '#arquivos_origem', function(e) {
	e.preventDefault();
	obter_arquivos_de_destino(this.value);
});

//Chamado quando um novo arquivo é selecionado.
$(document).on('change', '#upload', function(e) {
	transformar_aqruivo_base64(e);
});

//Chamado quando o formato de destino é alterado.
$(document).on('change', '#arquivos_destino', function(e) {
	e.preventDefault();
	$('#botao_enviar_arquivo').css('display', '');
});


//Chamado quando o falso botão de escolha de arquivo é pressionado.
$(document).on('click', '#escolher_arquivo', function(e) {
	e.preventDefault();
	$(this).blur();
	$("#upload").trigger('click');
});

function nova_transacao() {
	return {
	  start: '',
	  end: '',
	  content: ''
	};
}

//Pegando os formatos possiveis de destino.
function obter_arquivos_de_destino(formato) {
	jQuery.ajax({
		type: 'GET',
		url: 'http://localhost:8080/converter/from/' + formato,
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		success: function(response) {			
			var $select = $('#arquivos_destino'); 
			$('#div_formato_destino').css('display', '');
			$select.find('option').remove();  
			$select.append('<option value=0> Selecione um formato </option>');
			for(var i=0;i<response.length;i++){
				var obj = response[i];
				$select.append('<option value=' + obj.format + '>' + obj.format + '</option>');
			}	
		},
		error: function( xhr, status, errorThrown ) {
			console.log( errorThrown );
		}
	});
}

//Enviando arquivo para transformação.
function obter_arquivo_transformado(content, nome_arquivo) {
	var transacao = nova_transacao();
	transacao.start = $("#arquivos_origem").val();
	transacao.end = $("#arquivos_destino").val();
	transacao.content = content;
	jQuery.ajax({
		type: 'POST',
		url: 'http://localhost:8080/converter',
		contentType: 'application/json; charset=utf-8',
		dataType: 'json',
		data: JSON.stringify(transacao),
		success: function(response) {	
			var nome = nome_arquivo.substring(0, nome_arquivo.lastIndexOf('.'));
			$("#div_lista_arquivos").css("display", "")
			$("#lista_arquivos_convertidos").append('<a download="' + nome + "." + transacao.end.toLowerCase() + '" href="data:image;base64,' + response.content + '" class="list-group-item">' + nome + "." + transacao.end.toLowerCase() + '</a>');
		},
		error: function( xhr, status, errorThrown ) {
			console.log( errorThrown );
		}
	});
}

function transformar_aqruivo_base64(evt, nome_arquivo) {
    var file = evt.target.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(readerEvt) {
            var binaryString = readerEvt.target.result;
            obter_arquivo_transformado(btoa(binaryString), file.name);
        };
        reader.readAsBinaryString(file);
    }
};


