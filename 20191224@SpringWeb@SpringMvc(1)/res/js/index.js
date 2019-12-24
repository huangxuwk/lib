(function () {
	$$.package("com.mec.video");

	var blankModal = $$.model("blankModal");
	var blankModalValue = {
		modal_style : 'modalLoginStyle',
		modalId : 'modalLogin',
		modalTopicStyle : 'modalLoginTopic',
		modalTopic : '登录',
		modal_body_id : 'modalBody',
		btnCloseId : 'btnClose',
		btnSubmit : 'btnLogin',
	};
	var blankModalHtml = $$.model(blankModal, blankModalValue);
	$("#loginDiv").html(blankModalHtml);

	var normalInputGroup = $$.model("normalInputGroup");
	var normalInputGroupValue = {
		normalInputGroup : [{
			inputStyle : 'glyphicon glyphicon-user',
			inputLabel : '账号',
			inputType : 'text',
			inputId : 'txtId',
			inputPlaceholder : '请输入2-16位字母或数字',
		},{
			inputStyle : 'glyphicon glyphicon-lock',
			inputLabel : '密码',
			inputId : 'pswPassword',
			inputType : 'password',
			inputPlaceholder : '请输入1-10位密码',
		},],
	}
	var normalInputGroupHtml = $$.model(normalInputGroup, normalInputGroupValue);
	$("#modalBody").html(normalInputGroupHtml);

	var normalNav = $$.model("normalNav");
	var str = $$.modelStruct(normalNav);
	var navValue = {
		firstHref : '#',
		firstItem : '微易码',
		liList : [{
			itemActive : 'active',
			itemId : 'subject',
			itemHref : '#',
			itemContext : '精品课程',
		},{
			itemActive : '',
			itemId : 'teacher',
			itemHref : '#',
			itemContext : '师资介绍',
		},{
			itemActive : '',
			itemId : 'student',
			itemHref : '#',
			itemContext : '优秀学员',
		},{
			itemActive : '',
			itemId : 'btnModalLogin',
			itemHref : '#',
			itemContext : '登录',
		},],
	}
	var normalNavHtml = $$.model(normalNav, navValue);
	$("#headNav").html(normalNavHtml);

	var normalCarousel = $$.model("normalCarousel");
	var normalCarouselValue = {
		carouselId : 'mecCarousel',
		carouselStyle : 'carouselPic',
		intervalTime : '2000',
		carourselIndex : [{
			carouselId : 'mecCarousel',
			index : '0',
			indexActive : 'active',
		},{
			carouselId : 'mecCarousel',
			index : '1',
			indexActive : '',
		},{
			carouselId : 'mecCarousel',
			index : '2',
			indexActive : '',
		},{
			carouselId : 'mecCarousel',
			index : '3',
			indexActive : '',
		},],
		carouselItem : [{
			itemActive : 'active',
			imgPath : './res/pic/mn1.jpg',
			imgAlt : '第一张图片',
			itemStyle : 'carouselPicWidth',
			itemCaption : '第一张',
		},{
			itemActive : '',
			imgPath : './res/pic/mn2.jpg',
			imgAlt : '第二张图片',
			itemStyle : 'carouselPicWidth',
			itemCaption : '第二张',
		},{
			itemActive : '',
			imgPath : './res/pic/mn3.jpg',
			imgAlt : '第三张图片',
			itemStyle : 'carouselPicWidth',
			itemCaption : '第三张',
		},{
			itemActive : '',
			imgPath : './res/pic/mn4.jpg',
			imgAlt : '第四张图片',
			itemStyle : 'carouselPicWidth',
			itemCaption : '第四张',
		},],
	};
	var normalCarouselHtml = $$.model(normalCarousel, normalCarouselValue);
	// console.log(normalCarouselHtml);
	$("#mecCarouselDiv").html(normalCarouselHtml);

	$("#btnModalLogin").click(function (e) {
		$("#modalLogin").modal("show");
	});
	
	$("#btnLogin").click(function (e) {
		$$.action("doLogin")();
	});
}) ();
