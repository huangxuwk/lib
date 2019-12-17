(function () {
	$$.htmlModel = {
		normalNav : 
		'<nav class="navbar navbar-default" role="navigation">'
		    + '<div class="container-fluid">'
			    + '<div class="navbar-header">'
			        + '<a class="navbar-brand" href="{firstHref=#index.html}">{firstItem=First}</a>'
			    + '</div>'
			    + '<div>'
			        + '<ul class="nav navbar-nav">'
			            + 'for(liList){<li class="{itemActive=active}">'
			            	+ '<a id="{itemId=}" href="{itemHref=}">'
			            	+ '{itemContext=Item}</a>'
			            + '</li>}liList'
			        + '</ul>'
			    + '</div>'
		    + '</div>'
		+ '</nav>',
		blankModal :
		'<div class="modal fade" id="{modalId=}" tabindex="-1" role="dialog" aria-labelledby="modalTopic" aria-hidden="true">'
			+ '<div class="modal-dialog {modal_style=}">'
				+ '<div class="modal-content">'
					+ '<div class="modal-header">'
						+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'
						+ '<h4 class="modal-title {modalTopicStyle=}" id="modalTopic">{modalTopic=模态框标题}</h4>'
					+ '</div>'
					+ '<div id="{modal_body_id=}" class="modal-body"></div>'
					+ '<div class="modal-footer">'
					+ '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
						+ '<button type="button" class="btn btn-primary">提交更改</button>'
					+ '</div>'
				+ '</div>'
			+ '</div>'
		+ '</div>',
		normalInputGroup :
		'for(normalInputGroup){<div class="input-group">'
			+ '<span class="input-group-addon {inputStyle=}">{inputLabel=}</span>'
			+ '<input type="{inputType=text}" class="form-control" placeholder="{inputPlaceholder=}">'
		+ '</div>}normalInputGroup',
		normalCarousel :
		'<div id="{carouselId=}" class="carousel slide">'
			+ '<ol class="carousel-indicators">'
				+ 'for(carourselIndex){<li data-target="#{carouselId=}" data-slide-to="{index=0}" class="{indexActive=active}"></li>}carourselIndex'
		    + '</ol>   '
			+ '<div class="carousel-inner">'
				+ 'for(carouselItem){<div class="item {itemActive=active}">'
					+ '<img src="{imgPath=}" alt="{imgAlt=}">'
					+ '<div class="carousel-caption">{itemCaption=}</div>'
				+ '</div>}carouselItem'
			+ '</div>'
			+ '<a class="left carousel-control" href="#{carouselId=}" role="button" data-slide="prev">'
				+ '<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>'
				+ '<span class="sr-only">Previous</span>'
			+ '</a>'
			+ '<a class="right carousel-control" href="#{carouselId=}" role="button" data-slide="next">'
				+ '<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>'
				+ '<span class="sr-only">Next</span>'
			+ '</a>'
		+ '</div>',
	};
})();
