$(function() {

	// solving active menu problems
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
						
						if(row.quantity < 1 ){
							str+='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a> ';
						}
						else{
							str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a> ';
						}
						return str;
					}
				}
			]
		})
	}
});