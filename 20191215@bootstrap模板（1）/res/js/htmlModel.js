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
			            	+ '<a href="{itemHref=}">{itemContext=Item}</a>'
			            + '</li>liList}'
			        + '</ul>'
			    + '</div>'
		    + '</div>'
		+ '</nav>',
	};
})();
