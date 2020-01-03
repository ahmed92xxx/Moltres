$(function() {

	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':	
		$('#contact').addClass('active');
		break;
	case 'All Products':	
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':	
		$('#manageProducts').addClass('active');
		break;
	case 'Shopping Cart':	
		$('#ShoppingCart').addClass('active');
		break;
		
	default :	
		if(menu =="home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}

	// Jquery data table
		
	
	var $table = $('#productListTable');
	
	if($table.length){
		var jsonUrl = '';
		if(window.categoryId ==''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
		
		$table.DataTable({
			lengthMenu: [[3,5,10,-1],['3 records','5 records','10 records','ALL']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'code',
					bSortable: false, 
					mRender: function(data,type,row)
					{
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg"  class="dataTableImg" />';
					}
				},
				{
					data: 'name',
					bSortable: false
				},
				
				{
					data: 'brand',
					bSortable: false
				},
				
				{
					data: 'unitPrice',
					bSortable: false
					/*
					 * mRender: function(data,type,row){ return '&#8377'+ data }
					 */
				},
				{
					data: 'quantity',
					bSortable: false,
					mRender :function(data ,type ,row){
						if(data <1){
							return '<span style="color:red">Out Of Stock</span>';
						}
						return data;
					}

				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data,type,row)
					{
						var str='';
						str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160';
						
						if(userRole == 'ADMIN'){
							str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a> ';
						}
							else{
							
							if(row.quantity < 1 ){
								str+='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a> ';
							}
							else{
									str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a> ';
								}
						}
						return str;
					}
				}
			]
		})
	}
	
	//dismissing alert
	var $alert=$('.alert');
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000)
	}
	
	$('.switch input[type="checkbox"]').on('change',function(){
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked)? ' activate product ?':
							  ' desactivate product ?';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: 'Product Etat',
			message: dMsg,
			callback : function(confirmed){
				if(confirmed){
					console.log(value);
					bootbox.alert({
						size: 'medium',
						title: 'information',
						message: 'perform operation in product'+value
					})
				}else{
					checkbox.prop('checked',!checked);
				}
			}
		})
	});
	
	
	//csrf token
	
	var token = $('meta[name="_csrf]').attr('content');
	var header = $('meta[name="_csrf_header]').attr('content');
	if(token.length > 0 && header.length > 0)
		{
			$(document).ajaxSend(function(e , xhr , option){
				xhr.setRequestHeader(header,token);
			});
		}
	
	//data table admin********************
	
var $adminProductTable = $('#productsTable');
	
	if($adminProductTable.length){
		
		var jsonUrl = window.contextRoot + '/jason/data/admin/all/products';
		
		$adminProductTable.DataTable({
			lengthMenu: [[10,30,50,-1],['10 records','30 records','50 records','ALL']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id'
				},
				{
					data: 'code',
					bSortable: false, 
					mRender: function(data,type,row)
					{
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg"  class="dataTableImg" />';
					}
				},
				{
					data: 'name',
					bSortable: false
				},
				
				{
					data: 'brand',
					bSortable: false
				},
				
				
				{
					data: 'quantity',
					bSortable: false,
					mRender :function(data ,type ,row){
						if(data <1){
							return '<span style="color:red">Out Of Stock</span>';
						}
						return data;
					}

				},
				{
					data: 'unitPrice',
					bSortable: false
					/*
					 * mRender: function(data,type,row){ return '&#8377'+ data }
					 */
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data, type, row){
						var str = '';
						str +=	'<label class="switch">';
						if(data){
						str +=	'<input type="checkbox" checked="checked" value="'+row.id+'"/>';
						}
						else{
							str +=	'<input type="checkbox" value="'+row.id+'"/>';

						}
						str +=	'<div class="slider"></div>';
						str +=	'</label>' ;
						return str;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data, type, row){
						var str;
						str+='<a href="'+window.contextRoot+'/manage'+data+'/product" class="btn btn-warning">';
						str+='<span class="glyphicon glyphicon-pencil"></span></a>';
						return str;
					}
				}
			],
			initComplet: function(){
				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change',function(){
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)? ' activate product ?':
										  ' desactivate product ?';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size: 'medium',
						title: 'Product Etat',
						message: dMsg,
						callback : function(confirmed){
							if(confirmed){
								console.log(value);
								
								var activationUrl= window.contextRoot+'/manage/product/'+value+'/activation';
								$.post(activationUrl,function(data){
									bootbox.alert({
										size: 'medium',
										title: 'information',
										message: data
								});
								
								
								})
							}else{
								checkbox.prop('checked',!checked);
							}
						}
					})
				});
			}
		})
	}
	
	//***************************************************
	//validation category
	var $categoryForm=$('#categoryForm')
	if($categoryForm.length){
		$categoryForm.validate({
			rules :{
				name :{
					required: true,
					minlength: 2
				},
				description: {
					required: true
				}
			},
			message:{
				name:{
					required:'Please add category name !',
					minlength:'not less than 2 characters'
				},
				description:{
					required:'Please add category description !',
				}
			},
			errorElement: 'em',
			errorPlacement: function(error,element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
		})
	}
	//***************************************************

	//validation login
	var $loginForm=$('#loginForm')
	if($loginForm.length){
		$loginForm.validate({
			rules :{
				username :{
					required: true,
					email: true
				},
				password: {
					required: true
				}
			},
			message:{
				username:{
					required:'Please enter username !',
					email:'enter valid email'
				},
				password:{
					required:'Please enter password !',
				}
			},
			errorElement: 'em',
			errorPlacement: function(error,element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
		})
	}
	
	//********************************************
	//refresh cart
	
	$('button[name="refreshCart"]').click(function(){
		var cartLineId = $(this).attr('value');
		var countElement =$('#count_' + cartLineId);
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		
		
		if(currentCount !== originalCount){
			if(currentCount < 1 || currentCount > 3) {
				countElement.val(originalCount);
				bootbox.alert({
					size : 'medium',
					title :'Error',
					message: 'product count min=1 , max=3'
				});
			}
			else {
				var updateUrl = window.contextRoot + '/cart/' + cartLineId +'/update?count='+ currentCount;
				window.location.href=updateUrl;
				
			}
		}
	})
});